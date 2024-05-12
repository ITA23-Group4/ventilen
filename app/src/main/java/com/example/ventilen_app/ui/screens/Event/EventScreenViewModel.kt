package com.example.ventilen_app.ui.screens.Event

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.models.Event
import com.example.ventilen_app.data.repositories.EventRepository
import kotlinx.coroutines.launch

class EventScreenViewModel: ViewModel() {
    private val eventRepository: EventRepository = EventRepository()
    val events: MutableList<Event> = mutableStateListOf()

    val isExpandedStateMap: MutableMap<String, MutableState<Boolean>> = mutableMapOf()

    init {
        getEvents()
    }

    private fun getEvents(){
        viewModelScope.launch {
            try {
                events.clear()
                events.addAll(eventRepository.getEvents())
                events.forEach { event ->
                    isExpandedStateMap[event.eventID] = mutableStateOf(false)
                }
            } catch (error: Exception) {
                Log.d("GetAllEvents", "ERROR: ${error.message}")
            }
        }
    }

    fun addUserToEvent(currentUserUID: String, eventID: String) {
        viewModelScope.launch {
            try {
                eventRepository.addUserToEvent(currentUserUID, eventID)
                updateEventAttendeesCount(eventID)
            } catch (error: Exception) {
                Log.e("AddUserToEvent", "ERROR: ${error.message}")
            }
        }
    }

    fun removeUserFromEvent(currentUserUID: String, eventID: String) {
        viewModelScope.launch {
            try {
                eventRepository.removeUserFromEvent(currentUserUID, eventID)
                updateEventAttendeesCount(eventID)
            } catch (error: Exception) {
                Log.e("RemoveUserFromEvent", "ERROR: ${error.message}")
            }
        }
    }

    private fun updateEventAttendeesCount(eventID: String) {
        viewModelScope.launch {
            events.indexOfFirst { it.eventID == eventID }.let { eventIndex ->
                // Extract the updated attendees from the updated event
                val updatedEvent = eventRepository.getEvent(eventID = eventID)
                val updatedEventAttendees = updatedEvent.attendeesByUID

                val eventToUpdate = events[eventIndex]

                // Create a copy of the event with updated attendees
                val eventWithUpdatedAttendees = eventToUpdate.withUpdatedAttendees(updatedEventAttendees)

                events[eventIndex] = eventWithUpdatedAttendees
            }
        }
    }

    // Function to toggle expanded state for a specific event
    fun toggleExpandedState(eventID: String) {
        val isExpandedValue = isExpandedStateMap[eventID]!!.value
        isExpandedStateMap[eventID]!!.value = !isExpandedValue
    }

}
