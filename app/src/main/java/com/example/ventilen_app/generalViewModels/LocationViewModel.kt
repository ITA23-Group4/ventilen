package com.example.ventilen_app.generalViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.data.repositories.LocationRepository
import kotlinx.coroutines.launch

class LocationViewModel: ViewModel() {
    private val locationRepository: LocationRepository = LocationRepository()
    var locations: List<Location> = listOf()
    var mapLocationNameToLocation: Map<String, Location> = emptyMap() //TODO: Locations are saved two places now

    init {
        getLocations()
    }

    private fun getLocations() {
        viewModelScope.launch {
            try {
                locations = locationRepository.getLocations()
                mapLocationNameToLocation = locations.associateBy { location -> location.locationName }
                Log.d("LOCATION_LIST", locations.toString())
            } catch (error: Exception) {
                Log.d("ERROR", error.toString())
            }
        }
    }
}