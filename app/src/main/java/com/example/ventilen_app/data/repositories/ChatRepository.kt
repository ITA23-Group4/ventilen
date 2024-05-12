package com.example.ventilen_app.data.repositories

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.data.models.Message
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

/**
 * Repository class responsible for handling chat-related data operations.
 *
 * @property db Firebase Firestore instance.
 * @author Marcus, Christian
 */
class ChatRepository {
    private val db = Firebase.firestore

    /**
     * Observes changes in the Firestore "chats" collection and invokes the provided callback
     * with the updated list of messages whenever there is a change.
     *
     * @param updateMessages Callback function to be invoked with the updated list of messages.
     */
    fun observeMessages(updateMessages: (List<Message>) -> Unit) {
        // Set up a listener to monitor changes in the Firestore "chats" collection
        db.collection("chats")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .limit(3)
            .addSnapshotListener { snapshot, exception ->
                // Log an error if there's an issue retrieving messages
                if (exception != null) {
                    Log.e(TAG, "Error observing messages", exception)
                    return@addSnapshotListener
                }

                // Convert each Firestore document into a Message object using map
                val messages: List<Message> = snapshot?.documents?.map { document ->
                    val senderRef = document.get("senderUID") as? DocumentReference
                    val senderUID = senderRef?.id ?: ""
                    val messageContent = document.getString("message") ?: ""
                    val timestamp = document.getTimestamp("timestamp")!!.toDate()
                    val locationRef = document.get("location") as? DocumentReference
                    val locationID = locationRef?.id ?: ""
                    val username = document.getString("username") ?: ""

                    Message(senderUID, messageContent, timestamp, locationID, username)
                } ?: emptyList()

                // Invoke the callback with the updated list of messages
                updateMessages(messages)
            }
    }


    // Function to get the latest message from each location in the database
    // Then it returns a list of Location objects
    // This function is accessing the locations collection in firebase (not chats)
    // This might not be the best way of doing it since now we both have:
    // - Location
    // - Location
    // Data classes but they do serve different purposes - idk?
    suspend fun chatHubMessagesSnapshot(): List<Location> {
        val querySnapshot = db.collection("locations")
            .get()
            .await()
        val locations = querySnapshot.documents.mapNotNull { document ->
            val locationId = document.getString("name") ?: ""
            val latestMessage = document.getString("latestMessage") ?: ""
            val abbreviation = document.getString("abbreviation") ?: ""
            val locationID = document.id

            Location(locationId, latestMessage, abbreviation, locationID)
        }

        return locations
    }


    // Functions might not be correct can't query message from log: "FAILED_PRECONDITION: The query requires an index" TODO: FIX
    // Usefull links:
    // https://stackoverflow.com/questions/50207339/cloud-firestore-failed-precondition-the-query-requires-an-index
    // https://www.fullstackfirebase.com/cloud-firestore/indexes
    fun observeMessagesByLocation(locationId: String): LiveData<List<Message>> {
        val messagesLiveData = MutableLiveData<List<Message>>()
        db.collection("chats")
            .whereEqualTo("location", locationId)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    Log.e(TAG, "Error observing messages", exception)
                    return@addSnapshotListener
                }

                val messages = snapshot?.documents?.mapNotNull { document ->
                    val senderRef = document.get("senderUID") as? DocumentReference
                    val senderUID = senderRef?.id ?: ""
                    val messageContent = document.getString("message") ?: ""
                    val timestamp = document.getTimestamp("timestamp")!!.toDate()
                    val locationRef = document.get("location") as? DocumentReference
                    val locationID = locationRef?.id ?: ""
                    val username = document.getString("username") ?: ""

                    Message(senderUID, messageContent, timestamp, locationID, username)
                } ?: emptyList()

                messagesLiveData.value = messages
            }

        return messagesLiveData
    }


    suspend fun sendMessage(
        message: Message
    ) {

        val locationRef = db.collection("locations").document(message.locationID)
        val senderUIDRef = db.collection("users").document(message.senderUID)

        val messageHashMap = hashMapOf(
            "location" to locationRef,
            "message" to message.message,
            "senderUID" to senderUIDRef,
            "timestamp" to message.timestamp,
            "username" to message.username
        )

        try {
            db.collection("chats")
                .add(messageHashMap)
                .await()
                .let { documentReference ->
                    Log.d(TAG, "Message sent with ID: ${documentReference.id}")
                }
            db.collection("locations")
                .document(message.locationID)
                .update("latestMessage", message.message)
                .await()
        } catch (e: Exception) {
            Log.e(TAG, "Error sending message", e)
        }
    }

}
