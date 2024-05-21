package com.example.ventilen_app.viewmodels

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ventilen_app.data.models.Event
import com.example.ventilen_app.data.repositories.EventRepository
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

/**
 * ViewModel for managing the state and logic of creating an event in the application.
 * Handles user input, date and time selection, and event creation via the EventRepository.
 *
 * @property eventRepository Repository for event data operations.
 * @property eventTitle The title of the event.
 * @property eventDescription The description of the event.
 * @property eventAddress The address where the event will be held.
 * @property eventPrice The price of the event.
 * @property context The context used to display the date and time pickers.
 * @property eventStartDateTime The selected start date and time for the event.
 * @property eventEndDateTime The selected end date and time for the event.
 * @property showDialog State for dialog visibility.
 *
 * @constructor Creates an instance of CreateEventViewModel.
 *
 * @Author [Your Name] TODO: Add name
 */
class CreateEventViewModel : ViewModel() {
    private val eventRepository: EventRepository = EventRepository

    var eventTitle: String by mutableStateOf("")
    var eventDescription: String by mutableStateOf("")
    var eventAddress: String by mutableStateOf("")
    var eventPrice: String by mutableStateOf("")

    var context: Context? by mutableStateOf(null)

    var eventStartDateTime: Date? by mutableStateOf(null)
    var eventEndDateTime: Date? by mutableStateOf(null)

    var showDialog: Boolean by mutableStateOf(true)


    /**
     * Dismisses the dialog.
     */
    fun dismissDialog() {
        showDialog = false
    }

    /**
     * Displays the date and time picker for selecting the event's start date and time.
     */
    fun showStartDateTimePickerForUser() {
        showStartDateTimePickerForUser { dateTime ->
            eventStartDateTime = dateTime
        }
    }

    fun showEndDateTimePickerForUser() {
        showStartDateTimePickerForUser { dateTime ->
            eventEndDateTime = dateTime
        }
    }

    /**
     * Helper function to display a date and time picker dialog.
     *
     * @param onDateTimeSelected Callback invoked with the selected date and time.
     */
    private fun showStartDateTimePickerForUser(onDateTimeSelected: (Date) -> Unit) {
        val context = context ?: return
        val calendar = Calendar.getInstance()

        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                TimePickerDialog(
                    context,
                    { _, hourOfDay, minute ->
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar.set(Calendar.MINUTE, minute)
                        onDateTimeSelected(calendar.time)
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
                ).show()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        // Set the minimum date to today
        datePickerDialog.datePicker.minDate = calendar.timeInMillis

        datePickerDialog.show()
    }

    /**
     * Creates an event with the entered details and saves it using the EventRepository.
     * Resets the form fields upon successful creation.
     */
    fun createEvent() {
        val eventToCreate = Event(
            eventName = eventTitle,
            attendeesByUID = mutableListOf(),
            eventDescription = eventDescription,
            eventAddress = eventAddress,
            eventPrice = eventPrice.toDouble(),
            eventStartDateTime = eventStartDateTime!!,
            eventEndDateTime = eventEndDateTime!!
        )

        viewModelScope.launch {
            try {
                eventRepository.createEvent(eventToCreate)
                // Clear the text-fields after the event has been created
                eventTitle = ""
                eventDescription = ""
                eventAddress = ""
                eventPrice = ""
                eventStartDateTime = null
                eventEndDateTime = null
            } catch (error: Exception) {
                // Log error TODO: Handle error
            }
        }
    }
}

