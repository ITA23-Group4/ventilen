package com.example.ventilen_app.data

import android.util.Log
import com.example.ventilen_app.data.models.Event
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.data.models.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class Repository {
    private val db = Firebase.firestore;

    suspend fun getUser(uid: String): User? {
        return db.collection("users").document(uid).get().await()
            .toObject(User::class.java)
    }

    // Old createUser function that uses callbacks
    /*
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
    */

    // New createUser function that uses coroutines
    suspend fun createUser(newUser: User) {
        db.collection("users")
            .document(newUser.uid!!)
            .set(newUser)
            .await()
    }


    suspend fun getEvents(): MutableList<Event> {
        val querySnapshot: QuerySnapshot = db.collection("events").get().await()

        return querySnapshot.documents.map { eventDocument ->
            convertEventDocumentToEvent(eventDocument)
        }.toMutableList()
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
        val id = document.id
        return Event(title, attendeeUIDs, id)
    }


    suspend fun getLocations(): List<Location> {
        val querySnapshot = db.collection("locations").get().await()
        return querySnapshot.documents.map { locationDocument ->
            convertLocationDocumentToLocation(locationDocument)
        }
    }
    private fun convertLocationDocumentToLocation(document: DocumentSnapshot): Location {
        val name: String = document.getString("name") ?: ""
        val id = document.id
        return Location(name, id)
    }

}