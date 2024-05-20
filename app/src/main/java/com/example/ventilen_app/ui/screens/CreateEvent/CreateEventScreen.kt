package com.example.ventilen_app.ui.screens.CreateEvent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.CustomOutlinedButton
import com.example.ventilen_app.ui.components.CustomTextField
import java.util.Date

/**
 * Composable function that represents the Create Event screen in the application.
 * This screen allows admin users to input details for creating a new event, such as the event's title, description, address, price, and start/end dates.
 *
 * @param onCreateEvent Lambda function invoked when the "Opret event" button is clicked.
 * @param eventTitle The title of the event.
 * @param eventDescription The description of the event.
 * @param eventAddress The address where the event will be held.
 * @param eventPrice The price of the event.
 * @param selectedStartDateTime The selected start date and time for the event.
 * @param selectedEndDateTime The selected end date and time for the event.
 * @param shotDateTimePicker Lambda function to show the date and time picker dialog.
 * @param onValueChangeTitle Lambda function invoked when the event title changes.
 * @param onValueChangeDescription Lambda function invoked when the event description changes.
 * @param onValueChangeAddress Lambda function invoked when the event address changes.
 * @param onValueChangePrice Lambda function invoked when the event price changes.
 *
 * @author [Your Name] TODO: Add name
 */
@Composable
fun CreateEventScreen(
    onCreateEvent: () -> Unit,
    eventTitle: String,
    eventDescription: String,
    eventAddress: String,
    eventPrice: String,
    selectedStartDateTime: Date?,
    selectedEndDateTime: Date?,
    shotDateTimePicker: () -> Unit,
    onValueChangeTitle: (String) -> Unit,
    onValueChangeDescription: (String) -> Unit,
    onValueChangeAddress: (String) -> Unit,
    onValueChangePrice: (String) -> Unit,
    showDialog: Boolean,
    dismissDialog: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { dismissDialog() },
            confirmButton = {
                TextButton(onClick = { dismissDialog() }) {
                    Text("OK")
                }
            },
            title = { Text(text = "Event information.") },
            text = { Text("Det er vigtigt når opretter et event at du udfylder alle felter samt husker at vælge et start- og sluttidspunkt for eventet. Hvis der mangler information i felterne oprettes eventet ikke korrekt.") }
        )
    }

    CustomColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.size(2.dp))
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

        CustomOutlinedButton(text = "Vælg start tidspunkt", onClick = { shotDateTimePicker() })
        Text("Valgt starttidspunkt: ${selectedStartDateTime?.toString() ?: "Intet valgt"}", color = Color.Black)

        CustomOutlinedButton(text = "Vælg slut tidspunkt", onClick = { shotDateTimePicker() })
        Text("Valgt sluttidspunkt: ${selectedEndDateTime?.toString() ?: "Intet valgt"}", color = Color.Black)

        CustomTextField(
            text = eventPrice,
            label = "Pris"
            // keyboardType = KeyboardType.Number TODO: MAKE!
        ) {
            onValueChangePrice(it)
        }
        CustomFilledButton(
            text = "Opret event",
            onClick = onCreateEvent
        )
    }
}