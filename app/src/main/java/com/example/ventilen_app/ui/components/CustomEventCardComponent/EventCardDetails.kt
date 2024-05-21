package com.example.ventilen_app.ui.components.CustomEventCardComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EventCardDetails(
    address: String,
    dateStart: String,
    dateEnd: String,
    price: Double,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        EventCardDetailRow(
            icon = Icons.Default.LocationOn,
            contentDescription = "Address location",
            content = {
                Text(text = address,
                    style = MaterialTheme.typography.bodyMedium)
            }
        )
        EventCardDetailRow(
            icon = Icons.Default.Info,
            contentDescription = "Date and time",
            content = {
                Text(text = dateStart,
                    style = MaterialTheme.typography.bodyMedium)
                    }
        )
        EventCardDetailRow(
            icon = Icons.Default.ShoppingCart,
            contentDescription = "Price",
            content = {
                Text(text = "Pris: ${price.toInt()} kr.",
                    style = MaterialTheme.typography.bodyMedium)
            }
        )
    }
}