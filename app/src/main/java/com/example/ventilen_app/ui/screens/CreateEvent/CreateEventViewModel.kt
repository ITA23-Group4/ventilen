package com.example.ventilen_app.ui.screens.CreateEvent

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.models.Event
import com.example.ventilen_app.data.repositories.EventRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class) // OptIn annotation to enable experimental API

class CreateEventViewModel: ViewModel() {
    private val eventRepository: EventRepository = EventRepository

    var eventTitle: String by mutableStateOf("")
    var eventDescription: String by mutableStateOf("")
    var eventAddress: String by mutableStateOf("")
    var eventPrice: String by mutableStateOf("")


    fun createEvent(event: Event) {
        viewModelScope.launch {
            try {
                eventRepository.createEvent(event)
            } catch (error: Exception) {
                Log.e("CreateEvent", "ERROR: ${error.message}")
            }
        }
    }
}