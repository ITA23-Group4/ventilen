package com.example.ventilen_app.ui.components.CustomChatCardComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomChatCard(
    locationName: String,
    latestMessage: String,
    abbreviation: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { }
    ){
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = abbreviation)
            Column {
                Text(text = locationName)
                Text(text = latestMessage)
            }
        }
    }
}