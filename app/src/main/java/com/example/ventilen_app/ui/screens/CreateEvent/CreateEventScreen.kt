package com.example.ventilen_app.ui.screens.CreateEvent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.CustomTextField
import java.util.Date

@Composable
fun CreateEventScreen(
    onCreateEvent: () -> Unit,
    eventTitle: String,
    eventDescription: String,
    eventAddress: String,
    eventPrice: String,
    selectedStartDateTime: Date?,
    selectedEndDateTime: Date?,
    showDatePicker: () -> Unit, // TODO: Dialog should not be made in ViewModel?
    showTimePicker: () -> Unit,  // TODO: Dialog should not be made in ViewModel?
    onValueChangeTitle: (String) -> Unit,
    onValueChangeDescription: (String) -> Unit,
    onValueChangeAddress: (String) -> Unit,
    onValueChangePrice: (String) -> Unit,
) {
    CustomColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        CustomTextField(
            text = eventTitle,
            label = "Event navn"
        ) {
            onValueChangeTitle(it)
        }
        CustomTextField(
            text = eventDescription,
            label = "Beskrivelse"
        ) {
            onValueChangeDescription(it)
        }
        CustomTextField(
            text = eventAddress,
            label = "Adresse"
        ) {
            onValueChangeAddress(it)
        }
        Button(onClick = { showDatePicker() }) {
            Text("Select Date")
        }

        Button(onClick = { showTimePicker() }) {
            Text("Select Time")
        }

        Text("Selected Date: ${selectedStartDateTime?.toString() ?: "None"}", color = Color.Black)
        Text("Selected Time: ${selectedEndDateTime?.toString() ?: "None"}", color = Color.Black)

        CustomTextField(
            text = eventPrice,
            label = "Pris"
            // keyboardType = KeyboardType.Number
        ) {
            onValueChangePrice(it)
        }
        CustomFilledButton(
            text = "Opret event",
            onClick = onCreateEvent
        )
    }
}