package com.example.ventilen_app.data

import android.util.Log
import com.example.ventilen_app.data.models.Event
import com.example.ventilen_app.data.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class Repository {
    private val db = Firebase.firestore;

    suspend fun getUser(uid: String): User? {
        return db.collection("users").document(uid).get().await()
            .toObject(User::class.java)
    }

    fun createUser(
        newUser: User,
        onRegistrationSuccess: () -> Unit,
        onRegistrationFailed: () -> Unit
    ) {
        db.collection("users")
            .document(newUser.uid!!)
            .set(newUser)
            .addOnSuccessListener {
                onRegistrationSuccess()
                Log.d("CREATE_USER", "User created: $newUser")
            }
            .addOnFailureListener {
                onRegistrationFailed()
                Log.d("CREATE_USER", "Failed to create user: $newUser")
            }
    }


    suspend fun getEvents(): MutableList<Event> {
        val querySnapshot = db.collection("events").get().await()
        val events = mutableListOf<Event>()

        for (document in querySnapshot.documents) {
            val title = document.getString("title") ?: ""
            val attendees = document.get("attendees") as? List<DocumentReference> ?: emptyList()
            val attendeeUIDs = attendees.map { it.id }
            val id = document.id
            val event = Event(title, attendeeUIDs.toMutableList(), id)
            events.add(event)
        }

        return events
    }

    fun getEvent(eventID: String, onSuccess:(Event) -> Unit) {
        db.collection("events").document(eventID).get().addOnSuccessListener {
            val title = it.getString("title") ?: ""
            val attendees = it.get("attendees") as? List<DocumentReference> ?: emptyList()
            val attendeeUIDs = attendees.map { it.id }
            val id = it.id
            onSuccess(Event(title, attendeeUIDs.toMutableList(), id))
        }
    }

    fun addUserToEvent(userUID: String, eventID: String, onSuccess: () -> Unit ) {
        val eventRef = db.collection("events").document(eventID)
        val userRef = db.collection("users").document(userUID)
        eventRef.update("attendees", FieldValue.arrayUnion(userRef)).addOnSuccessListener {
            onSuccess()
        }
    }

    suspend fun removeUserFromEvent(userUID: String, eventID: String, onSuccess: () -> Unit ) {
        val eventRef = db.collection("events").document(eventID)
        val userRef = db.collection("users").document(userUID)
        eventRef.update("attendees", FieldValue.arrayRemove(userRef)).addOnSuccessListener {
            onSuccess()
        }.await()
    }


}