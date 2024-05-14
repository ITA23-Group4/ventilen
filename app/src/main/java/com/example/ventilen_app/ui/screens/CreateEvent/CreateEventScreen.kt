package com.example.ventilen_app.ui.screens.CreateEvent

import androidx.compose.runtime.Composable
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.CustomTextField

@Composable
fun CreateEventScreen(
    onCreateEvent: () -> Unit
) {
    CustomColumn(

    ) {
        CustomTextField(
            text = "Event navn",
            label = "Event navn"
        ) {

        }
        CustomTextField(
            text = "Beskrivelse",
            label = "Beskrivelse"
        ) {

        }
        CustomTextField(
            text = "Adresse",
            label = "Adresse"
        ) {

        }
        CustomTextField(
            text = "Dato",
            label = "Dato"
        ) {

        }
        CustomTextField(
            text = "Pris",
            label = "Pris"
        ) {

        }
        CustomFilledButton(
            text = "Tilføj event",
            onClick = onCreateEvent
        )
    }
}