package com.example.ventilen_app.data.repositories

import android.util.Log
import com.example.ventilen_app.data.models.Location
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
object LocationRepository {
    private val db = Firebase.firestore

    var locations: List<Location> = listOf()
        get() { return field.sorted() }

    val mapLocationNameToLocation: Map<String, Location>
        get() { return locations.associateBy { location -> location.locationName } }

    suspend fun createNewsForLocation(locationID: String, description: String) {
        val locationRef = db.collection("locations").document(locationID)
        locationRef.update("news", description).await()
    }

    suspend fun getLocations() {
        val querySnapshot = db.collection("locations").get().await()
        locations =  querySnapshot.documents.map { locationDocument ->
            convertLocationDocumentToLocation(locationDocument)
        }
        Log.d("LOCATION_LIST", locations.toString())
    }

    private fun convertLocationDocumentToLocation(document: DocumentSnapshot): Location {
        val name: String = document.getString("name") ?: ""
        val news: String = document.getString("news") ?: ""
        val latestMessage: String = document.getString("latestMessage") ?: ""
        val abbreviation: String = document.getString("abbreviation") ?: ""
        val id = document.id

        Log.d("NEWS", news)

        return Location(
            locationName = name,
            latestMessage = latestMessage,
            abbreviation = abbreviation,
            news = news,
            locationID =  id
        )
    }
}