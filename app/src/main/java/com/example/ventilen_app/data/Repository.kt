package com.example.ventilen_app.data

import android.util.Log
import com.example.ventilen_app.data.models.Event
import com.example.ventilen_app.data.models.User
import com.google.firebase.Firebase
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

    suspend fun getEvents():MutableList<Event>{
        return db.collection("events").get().await()
            .toObjects(Event::class.java)
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


}