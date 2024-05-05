package com.example.ventilen_app.ui.screens.Location

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.Repository
import kotlinx.coroutines.launch

class LocationsViewModel: ViewModel() {
    private val repository: Repository = Repository()
    var locationNames: List<String> = emptyList()
    init {
        getLocations()
    }
    private fun getLocations() {
        viewModelScope.launch {
            try {
                val locations = repository.getLocations()
                Log.d("LOCATION_LIST", locations.toString())
                locationNames = locations.map { it.name }
            } catch (error: Exception) {
                Log.d("ERROR", error.toString())
            }
        }
    }
}