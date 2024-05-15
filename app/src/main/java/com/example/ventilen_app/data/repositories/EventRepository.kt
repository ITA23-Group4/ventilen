package com.example.ventilen_app.data.repositories

import com.example.ventilen_app.data.models.Event
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class EventRepository() {
    private val db = Firebase.firestore;

    suspend fun getEvents(): List<Event> {
        val querySnapshot: QuerySnapshot = db.collection("events").get().await()

        return querySnapshot.documents.map { eventDocument ->
            convertEventDocumentToEvent(eventDocument)
        }
    }

    suspend fun getEvent(eventID: String): Event {
        val document = db.collection("events").document(eventID).get().await()
        return convertEventDocumentToEvent(document)
    }

    suspend fun addUserToEvent(userUID: String, eventID: String) {
        val eventRef = db.collection("events").document(eventID)
        val userRef = db.collection("users").document(userUID)
        eventRef.update("attendees", FieldValue.arrayUnion(userRef)).await()
    }

    suspend fun removeUserFromEvent(userUID: String, eventID: String) {
        val eventRef = db.collection("events").document(eventID)
        val userRef = db.collection("users").document(userUID)
        eventRef.update("attendees", FieldValue.arrayRemove(userRef)).await()
    }

    private fun convertEventDocumentToEvent(document: DocumentSnapshot): Event {
        val title: String = document.getString("title") ?: ""
        val attendees: List<DocumentReference> = document.get("attendees") as? List<DocumentReference> ?: emptyList()
        val attendeeUIDs: MutableList<String> = attendees.map { it.id }.toMutableList()
        val dateTime = document.getTimestamp("eventDateTime")// Retrieve DateTime field
        val description: String = document.getString("description") ?: "" // Retrieve eventDescription field
        val address: String = document.getString("address") ?: "" // Retrieve eventAddress field
        val price: Double = document.getDouble("price") ?: 0.0 // Retrieve eventPrice field
        val id = document.id

        return Event(
            eventName = title,
            attendeesByUID = attendeeUIDs,
            eventID = id,
            eventDateTime = dateTime!!,
            eventDescription = description,
            eventAddress = address,
            eventPrice = price
        )
    }
}