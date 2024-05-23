package com.example.ventilen_app.ui.screens.Chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.ui.components.CustomChatCardComponent.CustomCard
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
        Text(text = "Primære mødested", style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start)
        CustomCard(
            title = currentUserPrimaryLocation.locationName,
            bodyText = currentUserPrimaryLocation.latestMessage,
            abbreviation = currentUserPrimaryLocation.abbreviation,
            onClick = { onChatLocalNavigate(currentUserPrimaryLocation) },
            cardModifier = Modifier
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Andre mødesteder", style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start)

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(CustomColorScheme.LightGray)
                .shadow(elevation = 8.75.dp, shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                ))
                .clip(RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                ))
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
