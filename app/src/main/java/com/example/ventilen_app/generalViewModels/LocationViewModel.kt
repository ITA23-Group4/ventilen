package com.example.ventilen_app.generalViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.Repository
import com.example.ventilen_app.data.models.Location
import kotlinx.coroutines.launch

class LocationViewModel: ViewModel() {
    private val repository: Repository = Repository()
    var locations: List<Location> = listOf()
    var mapLocationNameToLocation: Map<String, Location> = emptyMap() //TODO: Locations are saved two places now

    init {
        getLocations()
    }

    private fun getLocations() {
        viewModelScope.launch {
            try {
                locations = repository.getLocations()
                mapLocationNameToLocation = locations.associateBy { location -> location.name }
                Log.d("LOCATION_LIST", locations.toString())
            } catch (error: Exception) {
                Log.d("ERROR", error.toString())
            }
        }
    }
}