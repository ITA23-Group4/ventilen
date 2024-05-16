package com.example.ventilen_app.ui.components.CustomEventCardComponent

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.data.models.Event
import com.example.ventilen_app.ui.theme.CustomColorScheme
import com.example.ventilen_app.ui.theme.VentilenAppTheme
import com.google.firebase.Timestamp
import java.util.Date

/**
 * Composable function to display a custom event card.
 *
 * @param event The event to display.
 * @param isAttending indicating whether the user is attending the event.
 * @param onAttend Callback function when the user decides to attend the event.
 * @param onNotAttend Callback function when the user decides not to attend the event.
 * @param isExpanded indicating whether the event card is expanded.
 * @param onCardClick Callback function when the event card is clicked.
 * @author Marcus, Christian, Nikolaj
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomEventCard(
    event: Event,
    isAttending: Boolean,
    onAttend: () -> Unit,
    onNotAttend: () -> Unit,
    // Expanded functions
    isExpanded: Boolean,
    onCardClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(8.dp),
        onClick = onCardClick
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            EventCardTopRow(
                title = event.eventName,
                isExpanded = isExpanded,
                date = event.getDate(),
            )

            // TODO: Should probably be hoisted :)
            // EventCard description
            if (isExpanded) {
                Text(
                    text = event.eventDescription,
                    style = MaterialTheme.typography.bodySmall
                    )
            } else {
                Text(
                    text = event.eventDescription.take(90) + "...",
                    style = MaterialTheme.typography.bodySmall)
            }

            if (isExpanded) {
                EventCardDetails(
                    address = event.eventAddress,
                    date = event.getDateWithTimeRange(),
                    price = event.eventPrice,
                    modifier = Modifier.padding(0.dp, 8.dp)
                )
            }
        }

        EventCardAttendeesRow(
            attendeesAmount = event.attendeesByUID.size,
            isAttending = isAttending,
            onAttend = onAttend,
            onNotAttend = onNotAttend,
            modifierRow = Modifier.padding(16.dp, 2.dp)
        )

    }
}

@Preview
@Composable
fun CustomEventCardPreview() {
    val event = Event(
        eventName = "Event Title",
        eventAddress = "123 Main St",
        eventDateTime = Timestamp(Date()), // Provide a DateTime value
        eventDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        eventPrice = 20.0,
        attendeesByUID = mutableListOf(), // Provide a mutable list of attendees
        eventID = "12345" // Provide an event ID
    )

    VentilenAppTheme {
        CustomEventCard(
            event = event,
            onAttend = {},
            onNotAttend = {},
            onCardClick = {},
            isExpanded = true,
            isAttending = false
        )
    }
}
