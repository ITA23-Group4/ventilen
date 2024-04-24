package com.example.ventilen_app.ui.screens.Event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomEventCardComponent.CustomEventCard

@Composable
fun EventScreen() {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp)) {
        CustomEventCard(title = "Taco Tirsdag")
        CustomEventCard(title = "Pita Night")
    }

}