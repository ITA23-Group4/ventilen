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

class CreateEventViewModel : ViewModel() {
    private val eventRepository: EventRepository = EventRepository()

    var eventTitle: String by mutableStateOf("")
    var eventDescription: String by mutableStateOf("")
    var eventAddress: String by mutableStateOf("")
    var eventPrice: String by mutableStateOf("")

    var context: Context? by mutableStateOf(null)

    var eventStartDateTime: Date? by mutableStateOf(null)
    var eventEndDateTime: Date? by mutableStateOf(null)

    fun showStartDateTimePicker() {
        showDateTimePicker { dateTime ->
            eventStartDateTime = dateTime
        }
    }

    fun showEndDateTimePicker() {
        showDateTimePicker { dateTime ->
            eventEndDateTime = dateTime
        }
    }

    private fun showDateTimePicker(onDateTimeSelected: (Date) -> Unit) {
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
                // To clear the text-fields after the event has been created
                eventTitle = ""
                eventDescription = ""
                eventAddress = ""
                eventPrice = ""
                eventStartDateTime = null
                eventEndDateTime = null
            } catch (error: Exception) {
                // Log error
            }
        }
    }
}
