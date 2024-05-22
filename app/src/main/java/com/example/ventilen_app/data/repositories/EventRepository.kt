package com.example.ventilen_app.data.repositories

import com.example.ventilen_app.data.models.Event
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

object EventRepository {
    private val db = Firebase.firestore;

    suspend fun getEvents(): List<Event> {
        val querySnapshot: QuerySnapshot = db.collection("events")
            .orderBy("eventStartDateTime")
            .get()
            .await()
        return querySnapshot.documents.mapNotNull { eventDocument ->
            convertEventDocumentToEvent(eventDocument)
        }
    }

    suspend fun getEvent(eventID: String): Event? {
        val document = db.collection("events").document(eventID).get().await()
        return document?.let { convertEventDocumentToEvent(it) }
    }

    suspend fun addUserToEvent(userUID: String, eventID: String) {
        val eventRef = db.collection("events").document(eventID)
        val userRef = db.collection("users").document(userUID)
        eventRef.update("attendeesByUID", FieldValue.arrayUnion(userRef)).await()
    }

    suspend fun removeUserFromEvent(userUID: String, eventID: String) {
        val eventRef = db.collection("events").document(eventID)
        val userRef = db.collection("users").document(userUID)
        eventRef.update("attendeesByUID", FieldValue.arrayRemove(userRef)).await()
    }

    private fun convertEventDocumentToEvent(document: DocumentSnapshot): Event? {
        val title = document.getString("eventName") ?: return null
        val attendeesByUID = document.get("attendeesByUID") as? List<DocumentReference> ?: emptyList()
        val eventStartDateTime = document.getTimestamp("eventStartDateTime")?.toDate() ?: return null
        val eventEndDateTime = document.getTimestamp("eventEndDateTime")?.toDate() ?: return null
        val description = document.getString("eventDescription") ?: ""
        val address = document.getString("eventAddress") ?: ""
        val price = document.getDouble("eventPrice") ?: 0.0
        val id = document.id

        return Event(
            eventName = title,
            attendeesByUID = attendeesByUID.toMutableList(),
            eventID = id,
            eventStartDateTime = eventStartDateTime,
            eventEndDateTime = eventEndDateTime,
            eventDescription = description,
            eventAddress = address,
            eventPrice = price
        )
    }

    suspend fun createEvent(event: Event) {

        val eventHashMap = hashMapOf(
            "eventName" to event.eventName,
            "attendeesByUID" to event.attendeesByUID,
            "eventDescription" to event.eventDescription,
            "eventStartDateTime" to com.google.firebase.Timestamp(event.eventStartDateTime),
            "eventEndDateTime" to com.google.firebase.Timestamp(event.eventEndDateTime),
            "eventAddress" to event.eventAddress,
            "eventPrice" to event.eventPrice
        )

        db.collection("events")
            .document()
            .set(eventHashMap)
            .await()
    }
}
