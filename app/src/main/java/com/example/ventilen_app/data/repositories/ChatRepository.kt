package com.example.ventilen_app.data.repositories

import android.content.ContentValues.TAG
import android.util.Log
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.data.models.Message
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

/**
 * Repository class responsible for handling chat-related data operations.
 *
 * @property db Firebase Firestore instance.
 * @author Marcus, Christian
 */
object ChatRepository {
    private val db = Firebase.firestore

    private val _messagesFlow = MutableStateFlow<List<Message>>(emptyList())
    val messagesFlow: StateFlow<List<Message>> get() = _messagesFlow

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
            val name = document.getString("name") ?: ""
            val latestMessage = document.getString("latestMessage") ?: ""
            val abbreviation = document.getString("abbreviation") ?: ""
            val news = document.getString("news") ?: ""
            val locationID = document.id

            Location(
                locationName = name,
                latestMessage = latestMessage,
                abbreviation = abbreviation,
                locationID = locationID,
                news = news
            )
        }

        return locations
    }

    fun observeMessagesByLocation(locationId: String) {
        db.collection("locations").document(locationId).collection("messages")
            .orderBy("timestamp", Query.Direction.DESCENDING)
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

                _messagesFlow.value = messages
            }
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
            db.collection("locations").document(message.locationID).collection("messages")
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
