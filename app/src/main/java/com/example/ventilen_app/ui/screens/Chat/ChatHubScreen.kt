package com.example.ventilen_app.ui.screens.Chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.ui.components.CustomChatCardComponent.CustomChatCard
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.theme.CustomColorScheme

@Composable
fun ChatHubScreen(
    locationsExcludingCurrentUserPrimaryLocation: List<Location>,
    onChatLocalNavigate: (Location) -> Unit,
    currentUserPrimaryLocation: Location
) {
    CustomColumn(
        modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Primære mødested", style = MaterialTheme.typography.headlineLarge)
        CustomChatCard(
            locationName = currentUserPrimaryLocation.locationName,
            latestMessage = currentUserPrimaryLocation.latestMessage,
            abbreviation = currentUserPrimaryLocation.abbreviation,
            onClick = { onChatLocalNavigate(currentUserPrimaryLocation) }
        )

        Text(text = "Andre mødesteder", style = MaterialTheme.typography.headlineLarge)

        // TODO: Should prob be more white cards and gray lazyColumn background
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(locationsExcludingCurrentUserPrimaryLocation) { Location ->
                CustomChatCard(
                    locationName = Location.locationName,
                    latestMessage = Location.latestMessage,
                    abbreviation = Location.abbreviation,
                    onClick = { onChatLocalNavigate(Location) },
                    hasRoundCorners = false
                )
            }
        }
    }

}
