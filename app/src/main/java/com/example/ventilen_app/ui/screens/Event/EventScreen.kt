package com.example.ventilen_app.ui.screens.Event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.data.models.Event
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomEventCardComponent.CustomEventCard
import com.example.ventilen_app.ui.theme.CustomColorScheme
import com.example.ventilen_app.ui.theme.VentilenAppTheme

@Composable
fun EventScreen(
    events: List<Event>,
    isEventSelected: (String) -> Boolean,
    isAttending: (Event) -> Boolean,
    onAttend: (String) -> Unit,
    onNotAttend: (String) -> Unit,
    onEventCardClick: (String) -> Unit
) {
    CustomColumn {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(CustomColorScheme.Mocha),
            contentPadding = PaddingValues(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(events) { event ->
                CustomEventCard(
                    event = event,
                    onAttend = { onAttend(event.eventID) },
                    onNotAttend = { onNotAttend(event.eventID) },
                    isExpanded = isEventSelected(event.eventID),
                    isAttending = isAttending(event),
                    onCardClick = { onEventCardClick(event.eventID) },
                )
            }
        }
    }
}
