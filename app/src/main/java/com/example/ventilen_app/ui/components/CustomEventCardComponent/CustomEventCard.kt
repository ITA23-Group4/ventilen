package com.example.ventilen_app.ui.components.CustomEventCardComponent

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomSwitchComponent.CustomSwitch
import com.example.ventilen_app.ui.components.EventCardDetailRow
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
            )
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(8.dp),
        onClick = onCardClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )
            if (!isExpanded) {
                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = date,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        // TODO: Should probably be hoisted :)
        if (isExpanded) {
            Text(
                text = description,
                modifier = Modifier
                    .padding(8.dp)
            )
        } else {
            Text(
                text = description.take(90) + "...",
                modifier = Modifier
                    .padding(8.dp)
            )
        }

        if (isExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                EventCardDetailRow(
                    icon = Icons.Default.LocationOn,
                    contentDescription = "Address location",
                    content = {
                        Text(text = address)
                    }
                )
                EventCardDetailRow(
                    icon = Icons.Default.Info,
                    contentDescription = "Date and time",
                    content = {
                        Text(text = date)
                    }
                )
                EventCardDetailRow(
                    icon = Icons.Default.ShoppingCart,
                    contentDescription = "Price",
                    content = {
                        Text(text = "Prris: $price kr")
                    }
                )
            }
        }
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
            Text(
                text = "Deltagere: ${attendeesAmount}",
                style = MaterialTheme.typography.labelLarge
            )
            CustomSwitch(
                onAttend = onAttend,
                onNotAttend = onNotAttend
            )

        }
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
