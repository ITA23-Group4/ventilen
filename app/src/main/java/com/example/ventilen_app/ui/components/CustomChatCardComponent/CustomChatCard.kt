package com.example.ventilen_app.ui.components.CustomChatCardComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomChatCard(
    locationName: String = "København",
    latestMessage: String = "Latest",
    abbreviation: String = "K",
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick
    ){
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = abbreviation)
            Spacer(modifier = Modifier.size(20.dp))
            Column {
                Text(text = locationName)
                Text(text = latestMessage)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomChatCardPreview() {
    val locationName = "Location"
    val latestMessage = "Latest Message"
    val abbreviation = "Abbreviation"

    CustomChatCard(locationName, latestMessage, abbreviation)
}