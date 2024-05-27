package com.example.ventilen_app.ui.components.CustomEventCardComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.R

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
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
            icon = painterResource(id = R.drawable.location),
            contentDescription = "Address location",
            content = {
                Text(text = address,
                    style = MaterialTheme.typography.bodyMedium)
            }
        )
        EventCardDetailRow(
            icon = painterResource(id = R.drawable.minute),
            contentDescription = "Date and time",
            content = {
                Text(text = dateStart,
                    style = MaterialTheme.typography.bodyMedium)
                    }
        )
        EventCardDetailRow(
            icon = painterResource(id = R.drawable.price),
            contentDescription = "Price",
            content = {
                Text(text = "${price.toInt()} kr.",
                    style = MaterialTheme.typography.bodyMedium)
            }
        )
    }
}