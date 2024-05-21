package com.example.ventilen_app.ui.screens.Chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.ui.components.CustomChatCardComponent.CustomCard
import com.example.ventilen_app.ui.components.CustomColumn


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
        Text(text = "Primære mødested", style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start)
        CustomCard(
            title = currentUserPrimaryLocation.locationName,
            bodyText = currentUserPrimaryLocation.latestMessage,
            abbreviation = currentUserPrimaryLocation.abbreviation,
            onClick = { onChatLocalNavigate(currentUserPrimaryLocation) }
        )

        Text(text = "Andre mødesteder", style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start)

        // TODO: Should prob be more white cards and gray lazyColumn background
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(locationsExcludingCurrentUserPrimaryLocation) { Location ->
                CustomCard(
                    title = Location.locationName,
                    bodyText = Location.latestMessage,
                    abbreviation = Location.abbreviation,
                    onClick = { onChatLocalNavigate(Location) },
                    hasRoundCorners = false
                )
            }
        }
    }

}
