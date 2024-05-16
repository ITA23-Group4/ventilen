package com.example.ventilen_app.generalViewModels

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.Calendar
import java.util.Date

class HomeViewModel : ViewModel(){
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

}