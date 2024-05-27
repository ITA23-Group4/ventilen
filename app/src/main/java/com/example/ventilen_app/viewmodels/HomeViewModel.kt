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

/**
 * ViewModel for the Home screen of the app.
 * Manages the state and handles business logic for news related to the user's primary location.
 *
 * @property userRepository Repository for user data.
 * @property locationRepository Repository for location data.
 *
 * @property primaryLocationNews The latest news for the primary location.
 * @property newsDescription The description for the news to be created.
 * @property showCreateNewsDialog Flag to show or hide a dialog.
 * @property isNewsCardExpanded Flag to expand or collapse the news card.
 *
 * @author Marcus, Christian, Nikolaj
 */
class HomeViewModel : ViewModel() {
    private val userRepository: UserRepository = UserRepository
    private val locationRepository: LocationRepository = LocationRepository

    var primaryLocationNews: String by mutableStateOf("")
    var newsDescription: String by mutableStateOf("")
    var showCreateNewsDialog: Boolean by mutableStateOf(false)
    var showConfirmDeleteNewsDialog: Boolean by mutableStateOf(false)
    var isNewsCardExpanded: Boolean by mutableStateOf(false)
    var loadedPrimaryLocationNews: Boolean by mutableStateOf(false)

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

    fun isCurrentUserAdmin(): Boolean {
        return userRepository.currentUser!!.isAdmin
    }

    fun toggleCreateNewsDialog() {
        showCreateNewsDialog = !showCreateNewsDialog
    }

    fun toggleConfirmDeleteNewsDialog() {
        showConfirmDeleteNewsDialog = !showConfirmDeleteNewsDialog
    }

    fun toggleNewsCard() {
        isNewsCardExpanded = !isNewsCardExpanded
    }

}