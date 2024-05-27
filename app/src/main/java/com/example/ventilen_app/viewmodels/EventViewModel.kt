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
import com.example.ventilen_app.data.repositories.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

class EventViewModel: ViewModel() {
    private val eventRepository: EventRepository = EventRepository
    private val userRepository: UserRepository = UserRepository

    val events: MutableList<Event> = mutableStateListOf()
    private var selectedEventCardID: String by mutableStateOf("")

    fun isAdmin(): Boolean {
        return userRepository.currentUser!!.isAdmin
    }

    fun getEvents(){
        viewModelScope.launch {
            try {
                events.clear()
                events.addAll(eventRepository.events)
            } catch (error: Exception) {
                Log.d("GetAllEvents", "ERROR: ${error.message}")
            }
        }
    }

    fun getUpdatedEventsFromFirestore() {
        viewModelScope.launch {
            try {
                eventRepository.getEvents()
                events.clear()
                events.addAll(eventRepository.events)
            } catch (error: Exception) {
                Log.d("GetAllEvents", "ERROR: ${error.message}")
            }
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

    /**
     * Filters events that occur within a week from the current date by using
     * binary search to efficiently find the events that start within the next week in O(log n) time complexity
     *
     * @return List of events occurring within the next week.
     * @author Marcus
     */
    fun getEventsWithinNextWeek(): List<Event> {
        // Get the current date and time
        val currentDate = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time

        // Calculate the date and time one week from now
        val oneWeekFromNow = Calendar.getInstance().apply {
            time = currentDate
            add(Calendar.DATE, 7)
        }.time

        // Find the start and end index using binary search
        val startIndex = findStartIndex(events, currentDate)

        // If no events occur within the next week, return an empty list
        if (startIndex == -1)
            return emptyList()

        //Find end index using binary search
        val endIndex = findEndIndex(events, oneWeekFromNow)

        return events.subList(startIndex, endIndex)
    }

    private fun getCurrentUserUIDFromFirebase(): String {
        return FirebaseAuth.getInstance().currentUser?.uid!!
    }

    fun isCurrentUserAttendingEvent(event: Event): Boolean {
        val currentUserUID = getCurrentUserUIDFromFirebase()
        return event.attendeesByUID.any { it == currentUserUID }
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

    /**
     * Finds the start index of events occurring within a week from the current date
     * using a modified binary search algorithm.
     *
     * @param events List of events sorted by eventStartDateTime.
     * @param currentDate The current date and time.
     * @return The start index, or -1 if no valid start index is found.
     * @author Marcus
     */
    private fun findStartIndex(events: List<Event>, currentDate: Date): Int {
        var low = 0
        var high = events.size - 1
        var startIndex = -1

        while (low <= high) {
            val mid = (low + high) / 2
            val midDate = events[mid].eventStartDateTime

            // If the event's start date is before the current date,
            // move the lower bound of the search interval to mid + 1
            if (midDate.before(currentDate)) {
                low = mid + 1
            } else {
                // If the event's start date is equal or after the current date,
                // update the start index and move the upper bound of the search interval to mid - 1
                startIndex = mid
                high = mid - 1
                Log.d("Update StartIndex:", "$startIndex")
            }
        }

        Log.d("findStartIndex", "startIndex: $startIndex")

        return startIndex
    }

    /**
     * Finds the end index of events occurring within a week from one week from now
     * using a modified binary search algorithm.
     *
     * @param events List of events sorted by eventStartDateTime.
     * @param oneWeekFromNow The date and time one week from the current date.
     * @return The end index.
     * @author Marcus
     */
    private fun findEndIndex(events: List<Event>, oneWeekFromNow: Date): Int {
        var low = 0
        var high = events.size - 1

        // Binary search loop to find the end index
        while (low <= high) {
            val mid = (low + high) / 2
            val midDate = events[mid].eventStartDateTime

            // If the event's start date is after one week from now,
            // move the upper bound of the search interval to mid - 1
            if (midDate.after(oneWeekFromNow)) {
                high = mid - 1
            } else {
                // If the event's start date is on or before one week from now,
                // update the end index and move the lower bound of the search interval to mid + 1
                low = mid + 1
            }
        }

        return low
    }

}
