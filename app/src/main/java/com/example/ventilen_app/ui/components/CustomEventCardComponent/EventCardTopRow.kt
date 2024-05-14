package com.example.ventilen_app.ui.components.CustomEventCardComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.theme.CustomColorScheme

@Composable
fun EventCardTopRow(
    title: String,
    isExpanded: Boolean,
    date: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium
        )
        if (!isExpanded) {
            Text(
                text = date,
                style = MaterialTheme.typography.headlineMedium
            )
        } else {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Close card Icon",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}