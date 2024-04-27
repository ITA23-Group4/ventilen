package com.example.ventilen_app.ui.components.CustomEventCardComponent

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomSwitchComponent.CustomSwitch

@Composable
fun CustomEventCard(
    title: String,
    attendeesAmount: Int,
    onAttend: () -> Unit,
    onNotAttend: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = title,
            style = MaterialTheme.typography.bodyMedium
        )
        // Temp text
        Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed rutrum vitae mauris nec pretium. Cras rutrum ipsum non massa porttitor efficitur. Aenean id euismod neque, id malesuada eros.",
            modifier = Modifier
                .padding(8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primary
                )
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(text = "Deltagere: ${attendeesAmount}",
                style = MaterialTheme.typography.labelLarge
                )
            CustomSwitch(
                onAttend = onAttend,
                onNotAttend = onNotAttend
            )

        }
    }
}