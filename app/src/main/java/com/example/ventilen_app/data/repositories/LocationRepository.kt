package com.example.ventilen_app.data.repositories

import com.example.ventilen_app.data.models.Location
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class LocationRepository {
    private val db = Firebase.firestore

    suspend fun getLocations(): List<Location> {
        val querySnapshot = db.collection("locations").get().await()
        return querySnapshot.documents.map { locationDocument ->
            convertLocationDocumentToLocation(locationDocument)
        }
    }
    private fun convertLocationDocumentToLocation(document: DocumentSnapshot): Location {
        val abbreviation: String = document.getString("abbreviation") ?: ""
        val name: String = document.getString("name") ?: ""
        val latestMessage: String = document.getString("latestMessage") ?: ""
        val id = document.id
        return Location(
            locationName = name,
            locationID =  id,
            abbreviation =  abbreviation,
            latestMessage =  latestMessage
        )
    }
}