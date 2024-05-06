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
    init {
        getLocations()
    }
    private fun getLocations() {
        viewModelScope.launch {
            try {
                locations = repository.getLocations()
                Log.d("LOCATION_LIST", locations.toString())
            } catch (error: Exception) {
                Log.d("ERROR", error.toString())
            }
        }
    }
}