package com.example.ventilen_app.ui.screens.Event

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
    isExpandedHashMap: MutableMap<String, MutableState<Boolean>>,
    onAttend: (String) -> Unit,
    onNotAttend: (String) -> Unit,
    onCardClick: (String) -> Unit,
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
                    title = event.eventName,
                    attendeesAmount = event.attendeesByUID.size,
                    onAttend = { onAttend(event.eventID) },
                    onNotAttend = { onNotAttend(event.eventID) },
                    isExpanded = isExpandedHashMap[event.eventID]!!,
                    onCardClick = { onCardClick(event.eventID) }
                )
            }
        }
    }
}

@Preview
@Composable
fun EventScreenPreview() {
    val sampleEvents = listOf(
        Event(eventName = "Burrito Ballade", attendeesByUID = mutableListOf("user1", "user3", "user4", "user20", "user3", "user4", "user20"), eventID = "1"),
        Event(eventName = "Sommerfesten 2024", attendeesByUID = mutableListOf("user3", "user4", "user20"), eventID = "2"),
        Event(eventName = "Pita Night", attendeesByUID = mutableListOf(), eventID = "3")
    )
    VentilenAppTheme {
        EventScreen(events = sampleEvents, onAttend = {}, onNotAttend = {}, onCardClick = {}, isExpandedHashMap = hashMapOf())
    }
}
