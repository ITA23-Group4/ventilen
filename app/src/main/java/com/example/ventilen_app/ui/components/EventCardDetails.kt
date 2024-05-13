package com.example.ventilen_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EventCardDetails(
    address: String,
    date: String,
    price: Double,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp),
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
                Text(text = "Pris: $price kr")
            }
        )
    }
}