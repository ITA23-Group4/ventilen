package com.example.ventilen_app.ui.components.CustomEventCardComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ventilen_app.ui.components.CustomSwitchComponent.CustomSwitch

@Composable
fun CustomEventCard(
    title: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Text(text = title)
        CustomSwitch()
    }
}