package com.example.ventilen_app.ui.screens.Event

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.Repository
import com.example.ventilen_app.data.models.Event
import kotlinx.coroutines.launch

class EventScreenViewModel: ViewModel() {
    private val repository: Repository = Repository()
    var events: List<Event> by mutableStateOf(mutableStateListOf())

    init {
        getEvents()
    }

    private fun getEvents(){
        viewModelScope.launch {
            try {
                events = repository.getEvents()
            } catch (error: Exception) {
                Log.d("ERROR",error.toString())
            }
        }
    }

    fun addUserToEvent(currentUserUID: String, eventID: String) {
        viewModelScope.launch {
            repository.addUserToEvent(currentUserUID, eventID)
            updateEventAttendeesCount(eventID)
        }
    }

    fun removeUserFromEvent(currentUserUID: String, eventID: String) {
        viewModelScope.launch {
            repository.removeUserFromEvent(currentUserUID, eventID)
            updateEventAttendeesCount(eventID)
        }
    }

    // TODO Look up
    // var events: SnapshotStateList<Event> by mutableStateOf(SnapshotStateList())
    private fun updateEventAttendeesCount(eventID: String) {
        viewModelScope.launch {
            try {
                val updatedEvent = repository.getEvent(eventID)
                events = events.map { if (it.id == eventID) updatedEvent else it }
            } catch (error: Exception) {
                Log.e("ERROR", "Failed to update event attendees: $error")
            }
        }
    }

    /*
    TODO Making a copy of the event on index, does not seem to trigger recompose
    private fun updateEventAttendeesCount(eventID: String) {
        viewModelScope.launch {
            events.indexOfFirst { it.id == eventID }.let { eventIndex ->
                val updatedEvent = repository.getEvent(eventID = eventID)
                events[eventIndex] = events[eventIndex].withUpdatedAttendees(updatedEvent.attendeesByUID)
            }
        }
    }
    */



}
