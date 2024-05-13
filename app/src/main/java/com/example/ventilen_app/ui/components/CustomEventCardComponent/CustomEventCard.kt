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
import com.example.ventilen_app.ui.components.EventCardAttendeesRow
import com.example.ventilen_app.ui.components.EventCardDetails
import com.example.ventilen_app.ui.components.EventCardTopRow
import com.example.ventilen_app.ui.theme.VentilenAppTheme

// TODO: Split up into more composable for readability
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomEventCard(
    title: String,
    date: String = "11. Maj", //TODO: Should be date
    description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed rutrum vitae mauris nec pretium. Cras rutrum ipsum non massa porttitor efficitur. Aenean id euismod neque, id malesuada eros.",
    address: String = "Guldbergsgade 29N, 2200 København",
    price: Double = 20.0,
    attendeesAmount: Int,
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            EventCardTopRow(
                title = title,
                isExpanded = isExpanded,
                date = date
            )

            // TODO: Should probably be hoisted :)
            // EventCard description
            if (isExpanded) {
                Text(text = description)
            } else {
                Text(text = description.take(90) + "...")
            }

            if (isExpanded) {
                EventCardDetails(
                    address = address,
                    date = date,
                    price = price,
                    modifier = Modifier.padding(0.dp, 8.dp)
                )
            }
        }

        EventCardAttendeesRow(
            attendeesAmount = attendeesAmount,
            onAttend = onAttend,
            onNotAttend = onNotAttend,
            modifierRow = Modifier.padding(16.dp, 4.dp)
        )

    }
}

@Preview
@Composable
fun CustomEventCardPreview() {
    VentilenAppTheme {
        CustomEventCard(
            title = "Event Title",
            address = "123 Main St",
            date = "11. May",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed rutrum vitae mauris nec pretium. Cras rutrum ipsum non massa porttitor efficitur. Aenean id euismod neque, id malesuada eros.",
            price = 20.0,
            attendeesAmount = 10,
            onAttend = {},
            onNotAttend = {},
            onCardClick = {},
            isExpanded = true
        )
    }
}
