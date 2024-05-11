package com.example.ventilen_app.ui.screens.Chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.ui.components.CustomChatCardComponent.CustomChatCard
import com.example.ventilen_app.ui.components.CustomColumn

@Composable
fun ChatHubScreen(
    locationsExcludingCurrentUserPrimaryLocation: List<Location>,
    onChatLocalNavigate: (String) -> Unit,
    currentUserPrimaryLocation: Location
) {
    CustomColumn {
        Text(text = "Primære mødested", style = MaterialTheme.typography.headlineLarge)
        CustomChatCard(
            locationName = currentUserPrimaryLocation.locationName,
            latestMessage = currentUserPrimaryLocation.latestMessage,
            abbreviation = currentUserPrimaryLocation.abbreviation,
            onClick = { onChatLocalNavigate(currentUserPrimaryLocation.locationID!!) }
        )

        Text(text = "Andre mødesteder", style = MaterialTheme.typography.headlineLarge)
        // CustomChatCards in a LazyColumn

        LazyColumn (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(locationsExcludingCurrentUserPrimaryLocation) { Location ->
                CustomChatCard(
                    locationName = Location.locationName,
                    latestMessage = Location.latestMessage,
                    abbreviation = Location.abbreviation,
                    onClick = { onChatLocalNavigate(Location.locationID!!) }
                )
            }
        }
    }

}
