package com.example.ventilen_app.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.repositories.LocationRepository
import com.example.ventilen_app.data.repositories.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val userRepository: UserRepository = UserRepository
    private val locationRepository: LocationRepository = LocationRepository

    var primaryLocationNews: String by mutableStateOf("")
    var newsDescription: String by mutableStateOf("")
    var showDialog: Boolean by mutableStateOf(false)
    var isNewsCardExpanded: Boolean by mutableStateOf(false)

    fun createNewsForPrimaryLocation() {
        viewModelScope.launch {
            try {
                val primaryLocationID: String = userRepository.currentUser!!.primaryLocationID
                locationRepository.createNewsForLocation(primaryLocationID, newsDescription)
                updateLatestNewsFromPrimaryLocation()
            } catch (error: Exception) {
                Log.e("Error", error.toString())
            }
        }
    }

    fun clearNewsForPrimaryLocation() {
        viewModelScope.launch {
            try {
                val primaryLocationID: String = userRepository.currentUser!!.primaryLocationID
                clearLatestNewsFromPrimaryLocation()
                locationRepository.createNewsForLocation(primaryLocationID, newsDescription)
                updateLatestNewsFromPrimaryLocation()
            } catch (error: Exception) {
                Log.e("Error", error.toString())
            }
        }
    }

    private fun updateLatestNewsFromPrimaryLocation() {
        primaryLocationNews = newsDescription
        newsDescription = ""
    }

    private fun clearLatestNewsFromPrimaryLocation() {
        primaryLocationNews = newsDescription
        newsDescription = ""
    }

    fun toggleDialog() {
        showDialog = !showDialog
    }

    fun toggleNewsCard() {
        isNewsCardExpanded = !isNewsCardExpanded
    }

}