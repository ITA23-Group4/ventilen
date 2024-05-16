package com.example.ventilen_app.ui.screens.CreateEvent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.CustomTextField

@Composable
fun CreateEventScreen(
    onCreateEvent: () -> Unit,
    eventTitle: String,
    eventDescription: String,
    eventAddress: String,
    eventPrice: String,
    onValueChangeTitle: (String) -> Unit,
    onValueChangeDescription: (String) -> Unit,
    onValueChangeAddress: (String) -> Unit,
    onValueChangePrice: (String) -> Unit
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
        // DatePicker
        CustomFilledButton(
            text = "Vælg Dato",
            onClick = {

            }
        )
        // TimePicker
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