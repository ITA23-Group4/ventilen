package com.example.ventilen_app.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.models.Event
import com.example.ventilen_app.data.repositories.EventRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class EventViewModel: ViewModel() {
    private val eventRepository: EventRepository = EventRepository

    val events: MutableList<Event> = mutableStateListOf()
    private var selectedEventCardID: String by mutableStateOf("")

    init {
        viewModelScope.launch {
            getEvents()
        }
    }

    private suspend fun getEvents(){
        try {
            events.addAll(eventRepository.getEvents())
        } catch (error: Exception) {
            Log.d("GetAllEvents", "ERROR: ${error.message}")
        }
    }

    fun addUserToEvent(eventID: String) {
        viewModelScope.launch {
            try {
                val currentUserUID: String = getCurrentUserUIDFromFirebase()
                eventRepository.addUserToEvent(currentUserUID, eventID)
                updateEventAttendeesCount(eventID)
            } catch (error: Exception) {
                Log.e("AddUserToEvent", "ERROR: ${error.message}")
            }
        }
    }

    fun removeUserFromEvent(eventID: String) {
        viewModelScope.launch {
            try {
                val currentUserUID: String = getCurrentUserUIDFromFirebase()
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
                val updatedEventAttendees = updatedEvent?.attendeesByUID

                val eventToUpdate = events[eventIndex]

                // Create a copy of the event with updated attendees
                val eventWithUpdatedAttendees = updatedEventAttendees?.let {
                    eventToUpdate.withUpdatedAttendees(it)
                }

                if (eventWithUpdatedAttendees != null) {
                    events[eventIndex] = eventWithUpdatedAttendees
                }
            }
        }
    }

    fun getCurrentUserUIDFromFirebase(): String {
        return FirebaseAuth.getInstance().currentUser?.uid!!
    }

    fun isCurrentUserAttendingEvent(event: Event): Boolean {
        val currentUserUID = getCurrentUserUIDFromFirebase()
        return event.attendeesByUID.any { it.id == currentUserUID }
    }

    fun isSelectedEvent(eventID: String): Boolean {
        return eventID == selectedEventCardID
    }

    /**
     * Toggles the selected event card ID.
     *
     * If the provided event ID is already selected, clears the selection;
     * otherwise, sets the provided event ID as the selected ID.
     *
     * @param eventID The ID of the event to toggle.
     */

    fun toggleEventCard(eventID: String) {
        selectedEventCardID = if (isSelectedEvent(eventID)) "" else eventID
    }

    fun clearSelectedEventCard() {
        selectedEventCardID = ""
    }

}
