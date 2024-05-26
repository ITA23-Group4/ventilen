package com.example.ventilen_app.data.repositories

import com.example.ventilen_app.data.models.Event
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.util.Calendar

object EventRepository {
    private val db = Firebase.firestore

    var events: MutableList<Event> = mutableListOf()
    lateinit var currentUserPrimaryLocationID: String

    suspend fun getEvents() {
        // To ensure accurate filtering for events starting today
        // the current date's time is set to 00:00:00.
        val currentDate = Calendar.getInstance().apply {
            // Set the hour, minute, second, and millisecond to 0 to get the start of the day
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time

        // Create a Timestamp object from the current date to use in Firestore query
        val currentTimestamp = Timestamp(currentDate)

        val querySnapshot: QuerySnapshot = db.collection("locations")
            .document(currentUserPrimaryLocationID)
            .collection("events")
            .whereGreaterThanOrEqualTo("eventStartDateTime", currentTimestamp)
            .orderBy("eventStartDateTime")
            .get()
            .await()

        events = querySnapshot.documents.mapNotNull { eventDocument ->
            convertEventDocumentToEvent(eventDocument)
        }.toMutableList()
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

    private fun convertEventDocumentToEvent(document: DocumentSnapshot): Event {
        val title = document.getString("eventName") ?: ""
        val attendeesByUID = document.get("attendeesByUID") as List<DocumentReference>
        val attendeesByUIDList = attendeesByUID.map { it.id }.toMutableList()
        val eventStartDateTime = document.getTimestamp("eventStartDateTime")?.toDate()
        val eventEndDateTime = document.getTimestamp("eventEndDateTime")?.toDate()
        val description = document.getString("eventDescription") ?: ""
        val address = document.getString("eventAddress") ?: ""
        val price = document.getDouble("eventPrice") ?: 0.0
        val id = document.id

        return Event(
            eventName = title,
            attendeesByUID = attendeesByUIDList,
            eventID = id,
            eventStartDateTime = eventStartDateTime!!,
            eventEndDateTime = eventEndDateTime!!,
            eventDescription = description,
            eventAddress = address,
            eventPrice = price,
        )
    }

    suspend fun createEvent(event: Event) {
        val primaryLocationRef = db.collection("locations").document(currentUserPrimaryLocationID)

        val eventHashMap = hashMapOf(
            "eventName" to event.eventName,
            "attendeesByUID" to event.attendeesByUID,
            "eventDescription" to event.eventDescription,
            "eventStartDateTime" to Timestamp(event.eventStartDateTime),
            "eventEndDateTime" to Timestamp(event.eventEndDateTime),
            "eventAddress" to event.eventAddress,
            "eventPrice" to event.eventPrice
        )

        primaryLocationRef.collection("events")
            .document()
            .set(eventHashMap)
            .await()
    }

}
