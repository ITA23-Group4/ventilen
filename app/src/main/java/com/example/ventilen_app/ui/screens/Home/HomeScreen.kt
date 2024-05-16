package com.example.ventilen_app.ui.screens.Home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import java.util.Date

@Composable
fun HomeScreen(
    selectedDate: Date?,
    selectedTime: Date?,
    showDatePicker: () -> Unit, // TODO: Dialog should not be made in ViewModel?
    showTimePicker: () -> Unit  // TODO: Dialog should not be made in ViewModel?
) {
    Column {
        Button(onClick = { showDatePicker() }) {
            Text("Select Date")
        }

        Button(onClick = { showTimePicker() }) {
            Text("Select Time")
        }

        Text("Selected Date: ${selectedDate?.toString() ?: "None"}", color = Color.Black)
        Text("Selected Time: ${selectedTime?.toString() ?: "None"}", color = Color.Black)
    }

}