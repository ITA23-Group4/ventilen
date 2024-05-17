package com.example.ventilen_app.viewmodels

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
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
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class) // OptIn annotation to enable experimental API

class CreateEventViewModel: ViewModel() {
    private val eventRepository: EventRepository = EventRepository()

    var eventTitle: String by mutableStateOf("")
    var eventDescription: String by mutableStateOf("")
    var eventAddress: String by mutableStateOf("")
    var eventPrice: String by mutableStateOf("")

    // TODO: USE DATE AND TIME WHEN CREATING EVENT
    var context: Context? by mutableStateOf(null)
    var selectedDate: Date? by mutableStateOf(null)
    var selectedTime: Date? by mutableStateOf(null)

    // TODO: Dialog should not be made in ViewModel?
    fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            context!!,
            { _, selectedYear, selectedMonth, dayOfMonth ->
                calendar.set(selectedYear, selectedMonth, dayOfMonth)
                selectedDate = calendar.time
            },
            year,
            month,
            dayOfMonth
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    // TODO: Dialog should not be made in ViewModel?
    fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            context!!,
            { _, selectedHour, selectedMinute ->
                calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                calendar.set(Calendar.MINUTE, selectedMinute)
                selectedTime = calendar.time
            },
            hour,
            minute,
            true
        )
        timePickerDialog.show()
    }



    fun createEvent() {

        val eventToCreate = Event(
            eventName = eventTitle,
            attendeesByUID = mutableListOf(),
            eventDescription = eventDescription,
            eventAddress = eventAddress,
            eventPrice = eventPrice.toDouble()
        )

        viewModelScope.launch {
            try {
                eventRepository.createEvent(eventToCreate)
                // To clear the text-fields after the event has been created
                eventTitle = ""
                eventDescription = ""
                eventAddress = ""
                eventPrice = ""
            } catch (error: Exception) {
                Log.e("CreateEvent", "ERROR: ${error.message}")
            }
        }

    }

}