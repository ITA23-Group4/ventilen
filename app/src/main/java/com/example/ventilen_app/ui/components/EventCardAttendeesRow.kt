package com.example.ventilen_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomSwitchComponent.CustomSwitch

@Composable
fun EventCardAttendeesRow(
    attendeesAmount: Int,
    onAttend: () -> Unit,
    onNotAttend: () -> Unit,
    modifierRow: Modifier = Modifier
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.primary
            )
    ){
        Row(
            modifier = modifierRow
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Deltagere: $attendeesAmount",
                style = MaterialTheme.typography.labelLarge
            )
            CustomSwitch(
                onAttend = onAttend,
                onNotAttend = onNotAttend
            )
        }
    }
}