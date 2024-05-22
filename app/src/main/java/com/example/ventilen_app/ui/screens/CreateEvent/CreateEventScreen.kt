package com.example.ventilen_app.ui.screens.CreateEvent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.CustomOutlinedButton
import com.example.ventilen_app.ui.components.CustomTextField

/**
 * Composable function that represents the Create Event screen in the application.
 * This screen allows admin users to input details for creating a new event, such as the event's title, description, address, price, and start/end dates.
 *
 * @param eventTitle The title of the event.
 * @param eventDescription The description of the event.
 * @param eventAddress The address where the event will be held.
 * @param eventPrice The price of the event.
 * @param showDialog Boolean value representing the visibility of the dialog.
 * @param startDateTimeButtonText The text to display on the start date and time button.
 * @param endDateTimeButtonText The text to display on the end date and time button.
 * @param onCreateEvent Lambda function invoked when the "Opret event" button is clicked.
 * @param showStartDateTimePicker Lambda function to show the start date and time picker dialog.
 * @param showEndDateTimePicker Lambda function to show the end date and time picker dialog.
 * @param onValueChangeTitle Lambda function invoked when the event title changes.
 * @param onValueChangeDescription Lambda function invoked when the event description changes.
 * @param onValueChangeAddress Lambda function invoked when the event address changes.
 * @param onValueChangePrice Lambda function invoked when the event price changes.
 * @param dismissDialog Lambda function to dismiss the dialog.
 * @param areAllFieldsFilled Lambda function that returns a Boolean indicating whether all required fields are filled.
 *
 * @see CustomTextField
 * @see CustomOutlinedButton
 * @see CustomFilledButton
 * @see CustomColumn
 * @see AlertDialog
 *
 * @Author [Your Name] TODO: Add name
 */
@Composable
fun CreateEventScreen(
    eventTitle: String,
    eventDescription: String,
    eventAddress: String,
    eventPrice: String,
    showDialog: Boolean,
    startDateTimeButtonText: String,
    endDateTimeButtonText: String,
    onCreateEvent: () -> Unit,
    showStartDateTimePicker: () -> Unit,
    showEndDateTimePicker: () -> Unit,
    onValueChangeTitle: (String) -> Unit,
    onValueChangeDescription: (String) -> Unit,
    onValueChangeAddress: (String) -> Unit,
    onValueChangePrice: (String) -> Unit,
    dismissDialog: () -> Unit,
    areAllFieldsFilled: () -> Boolean
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { dismissDialog() },
            confirmButton = {
                CustomFilledButton(text = "OK", onClick = { dismissDialog() })
            },
            title = { Text(text = "Event information.") },
            text = { Text("Det er vigtigt når opretter et event at du udfylder alle felter samt husker at vælge et start- og sluttidspunkt for eventet. Hvis der mangler information i felterne oprettes eventet ikke korrekt.") },
            shape = RoundedCornerShape(8.dp)
        )
    }

    CustomColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.size(2.dp))
        CustomTextField(
            text = eventTitle,
            label = "Event navn",
            onValueChange = {
                if (it.length <= 30) onValueChangeTitle(it)
            },
        )
        CustomTextField(
            text = eventDescription,
            label = "Beskrivelse",
            onValueChange = onValueChangeDescription
        )
        CustomTextField(
            text = eventAddress,
            label = "Adresse",
            onValueChange = onValueChangeAddress
        )

        CustomOutlinedButton(
            text = startDateTimeButtonText,
            onClick = { showStartDateTimePicker() }
        )
        CustomOutlinedButton(
            text = endDateTimeButtonText,
            onClick = { showEndDateTimePicker() }
        )

        CustomTextField(
            text = eventPrice,
            label = "Pris",
            onValueChange = onValueChangePrice,
            isIntegerOnly = true
        )

        CustomFilledButton(
            text = "Opret event",
            onClick = onCreateEvent,
            isEnabled = areAllFieldsFilled()
        )
    }
}
