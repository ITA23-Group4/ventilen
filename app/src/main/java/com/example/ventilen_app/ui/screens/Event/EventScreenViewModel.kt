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
    val events: MutableList<Event> = mutableStateListOf() // Most be val and not var: https://jetc.dev/slack/2021-11-20-why-does-my-list-not-recompose.html

    init {
        getEvents()
    }

    private fun getEvents(){
        viewModelScope.launch {
            try {
                events.clear() // Clear the existing list
                events.addAll(repository.getEvents()) // Add all items from the repository's response
            } catch (error: Exception) {
                Log.d("GetAllEvents", "ERROR: ${error.message}")
            }
        }
    }

    fun addUserToEvent(currentUserUID: String, eventID: String) {
        viewModelScope.launch {
            try {
                repository.addUserToEvent(currentUserUID, eventID)
                updateEventAttendeesCount(eventID)
            } catch (error: Exception) {
                Log.e("AddUserToEvent", "ERROR: ${error.message}")
            }
        }
    }

    fun removeUserFromEvent(currentUserUID: String, eventID: String) {
        viewModelScope.launch {
            try {
                repository.removeUserFromEvent(currentUserUID, eventID)
                updateEventAttendeesCount(eventID)
            } catch (error: Exception) {
                Log.e("RemoveUserFromEvent", "ERROR: ${error.message}")
            }
        }
    }

    /*
    TODO Look up var events: SnapshotStateList<Event> by mutableStateOf(SnapshotStateList())
    private fun updateEventAttendeesCount(eventID: String) {
        viewModelScope.launch {
            try {
                val updatedEvent = repository.getEvent(eventID)
                events = events.map { if (it.id == eventID) updatedEvent else it }.toMutableList()
            } catch (error: Exception) {
                Log.e("ERROR", "Failed to update event attendees: $error")
            }
        }
    }
    */
    private fun updateEventAttendeesCount(eventID: String) {
        viewModelScope.launch {
            events.indexOfFirst { it.id == eventID }.let { eventIndex ->
                // Extract the updated attendees from the updated event
                val updatedEvent = repository.getEvent(eventID = eventID)
                val updatedEventAttendees = updatedEvent.attendeesByUID

                val eventToUpdate = events[eventIndex]

                // Create a copy of the event with updated attendees
                val eventWithUpdatedAttendees = eventToUpdate.withUpdatedAttendees(updatedEventAttendees)

                events[eventIndex] = eventWithUpdatedAttendees
            }
        }
    }

}
