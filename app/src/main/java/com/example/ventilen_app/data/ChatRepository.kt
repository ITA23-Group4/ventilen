package com.example.ventilen_app.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ventilen_app.data.models.Message
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class ChatRepository {
    private val db = Firebase.firestore

    /**
     * Observes messages from our Firestore database collection "chats" and returns them as LiveData.
     * Messages are ordered by timestamp in ascending order.
     *
     * @return LiveData object containing a list of messages.
     */
    fun observeMessages(): LiveData<List<Message>> {
        val messagesLiveData = MutableLiveData<List<Message>>()

        // Set up a listener to monitor changes in the Firestore "chats" collection
        db.collection("chats")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, exception ->
                // Log an error if there's an issue retrieving messages
                if (exception != null) {
                    Log.e(TAG, "Error observing messages", exception)
                    return@addSnapshotListener
                }

                // Convert each Firestore document into a Message object using map
                // TODO: Should maybe be a separate method
                val messages = snapshot?.documents?.map { document ->
                    val senderRef = document.get("senderUID") as? DocumentReference
                    val senderUID = senderRef?.id ?: ""
                    val messageContent = document.getString("message") ?: ""
                    val timestamp = document.getTimestamp("timestamp")?.toDate()?.time ?: 0

                    Message(senderUID, messageContent, timestamp)
                } ?: emptyList()

                // Update LiveData with the list of messages
                messagesLiveData.value = messages
            }

        return messagesLiveData
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
}
