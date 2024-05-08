package com.example.ventilen_app.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ventilen_app.data.models.LocationInfo
import com.example.ventilen_app.data.models.Message
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class ChatRepository {
    private val db = Firebase.firestore

    fun observeMessages(locationId: String): LiveData<List<Message>> {
        val messagesLiveData = MutableLiveData<List<Message>>()

        db.collection("chats")
            // .whereEqualTo("location", locationId)
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    Log.e(TAG, "Error observing messages", exception)
                    return@addSnapshotListener
                }

                val messages = snapshot?.documents?.mapNotNull { document ->
                    val senderRef = document.get("senderUID") as? DocumentReference
                    val senderUID = senderRef?.id ?: ""
                    val messageContent = document.getString("message") ?: ""
                    val timestamp = document.getTimestamp("timestamp")?.toDate()?.time ?: 0
                    val locationRef = document.get("location") as? DocumentReference
                    val locationID = locationRef?.id ?: ""

                    Message(senderUID, messageContent, timestamp, locationID)
                } ?: emptyList()

                messagesLiveData.value = messages
            }

        return messagesLiveData
    }

    // Function to get the latest message from each location in the database
    // Then it returns a list of LocationInfo objects
    // This function is accessing the locations collection in firebase (not chats)
    // This might not be the best way of doing it since now we both have:
    // - Location
    // - LocationInfo
    // Data classes but they do serve different purposes - idk?
    suspend fun chatHubMessagesSnapshot(): List<LocationInfo> {
        val querySnapshot = db.collection("locations")
            .get()
            .await()
        return querySnapshot.documents.mapNotNull { document ->
            val locationId = document.getString("name") ?: ""
            val latestMessage = document.getString("latestMessage") ?: ""
            val abbreviation = document.getString("abbreviation") ?: ""
            LocationInfo(locationId, latestMessage, abbreviation)
        }
    }


    suspend fun sendMessage(senderUID: String, messageContent: String) {
        val message = hashMapOf(
            "senderUID" to senderUID,
            "message" to messageContent,
            "timestamp" to System.currentTimeMillis()
        )

        try {
            db.collection("chats")
                .add(message)
                .await()
                .let { documentReference ->
                    Log.d(TAG, "Message sent with ID: ${documentReference.id}")
                }
        } catch (e: Exception) {
            Log.e(TAG, "Error sending message", e)
        }
    }


    // Specific location messages function
    /*
    suspend fun getMessagesByLocation(locationId: String): List<Message> {
        val querySnapshot = db.collection("chats")
            .whereEqualTo("location", locationId)
            .get()
            .await()
        return querySnapshot.documents.mapNotNull { it.toObject(Message::class.java) }
    }
    */
}
