package com.example.ventilen_app.ui.screens.Event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.data.models.Event
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomEventCardComponent.CustomEventCard
import com.example.ventilen_app.ui.theme.CustomColorScheme

@Composable
fun EventScreen(
    events: List<Event>,
    onAttend: (String) -> Unit,
    onNotAttend: (String) -> Unit
) {
    CustomColumn {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(CustomColorScheme.Mocha),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            items(events) { event ->
                CustomEventCard(
                    title = event.title,
                    attendeesAmount = event.attendeesByUID.size,
                    onAttend = { onAttend(event.id!!) },
                    onNotAttend = { onNotAttend(event.id!!) }
                )
            }
        }

    }
}
