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
    var showDialog by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        showDialog = true
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK")
                }
            },
            title = { Text(text = "Important information") },
            text = { Text("This is some important information you need to know before creating an event.") }
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

        CustomOutlinedButton(text = "Vælg start tidspunkt", onClick = { showDatePicker() })
        Text("Valgt starttidspunkt: ${selectedStartDateTime?.toString() ?: "Intet valgt"}", color = Color.Black)

        CustomOutlinedButton(text = "Vælg slut tidspunkt", onClick = { showDatePicker() })
        Text("Valgt sluttidspunkt: ${selectedEndDateTime?.toString() ?: "Intet valgt"}", color = Color.Black)

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