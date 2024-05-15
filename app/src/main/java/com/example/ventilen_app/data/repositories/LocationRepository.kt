package com.example.ventilen_app.data.repositories

import android.util.Log
import com.example.ventilen_app.data.models.Location
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LocationRepository(viewModelScope: CoroutineScope) {
    private val db = Firebase.firestore

    var locations: List<Location> = listOf()
        get() { return field.sorted() }
    val mapLocationNameToLocation: Map<String, Location>
        get() { return locations.associateBy { location -> location.locationName } }

    init {
        // Launch a coroutine in the viewModelScope
        viewModelScope.launch {
            getLocations()
        }
    }

    private suspend fun getLocations() {
        val querySnapshot = db.collection("locations").get().await()
        locations =  querySnapshot.documents.map { locationDocument ->
            convertLocationDocumentToLocation(locationDocument)
        }
        Log.d("LOCATION_LIST", locations.toString())
    }
    private fun convertLocationDocumentToLocation(document: DocumentSnapshot): Location {
        val name: String = document.getString("name") ?: ""
        val id = document.id
        return Location(
            locationName = name,
            locationID =  id
        )
    }
}