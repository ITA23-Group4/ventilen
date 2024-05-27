package com.example.ventilen_app.ui.screens.Home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.data.models.Event
import com.example.ventilen_app.data.models.Location
import com.example.ventilen_app.ui.components.CustomChatCardComponent.CustomCard
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomEventCardComponent.CustomEventCard
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.CustomNewsCard
import com.example.ventilen_app.ui.components.CustomOutlinedButton
import com.example.ventilen_app.ui.components.CustomTextField
import com.example.ventilen_app.ui.theme.CustomColorScheme

/**
 * A composable function that displays the Home screen. With news displayed,
 * primary chat with latest message and event overview
 *
 * @author Marcus, Christian, Nikolaj
 */
@Composable
fun HomeScreen(
    isAdmin: Boolean,

    // News + Chat
    currentUserPrimaryLocation: Location,
    primaryLocationNews: String,
    isNewsCardExpanded: Boolean,
    onNewsCardClick: () -> Unit,
    onDeleteNews: () -> Unit,
    onChatLocalNavigate: (Location) -> Unit,

    // Event
    eventsWithinAWeek: List<Event>,
    isEventSelected: (String) -> Boolean,
    isAttending: (Event) -> Boolean,
    onAttend: (String) -> Unit,
    onNotAttend: (String) -> Unit,
    onEventCardClick: (String) -> Unit,

    // Create/delete news
    showCreateNewsDialog: Boolean,
    showDeleteNewsDialog: Boolean,
    dialogDescription: String,
    onShowDeleteNewsDialog: () -> Unit,
    onDialogDescriptionChange: (String) -> Unit,
    onCreateNews: () -> Unit,
    dismissCreateDialog: () -> Unit,
    dismissDeleteDialog: () -> Unit
) {
    if (showCreateNewsDialog) {
        AlertDialog(
            onDismissRequest = { dismissCreateDialog() },
            dismissButton = {
                CustomFilledButton(
                    text = "Opret",
                    onClick = onCreateNews
                )
            },
            confirmButton = {
                CustomOutlinedButton(
                    text = "Afbryd",
                    onClick = dismissCreateDialog
                )
            },
            title = {
                Text(
                    text = "Opret Nyhed",
                    style = MaterialTheme.typography.headlineMedium
                )
            },
            text = {
                CustomTextField(
                    text = dialogDescription,
                    label = "Beskrivelse",
                    onValueChange = onDialogDescriptionChange
                )
            },
            shape = RoundedCornerShape(8.dp)
        )
    }

    CustomColumn(
        modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (primaryLocationNews.isNotEmpty()) {
            Text(
                text = "Nyheder", style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            CustomNewsCard(
                title = currentUserPrimaryLocation.locationName,
                bodyText = primaryLocationNews,
                abbreviation = currentUserPrimaryLocation.abbreviation,
                backgroundColor = MaterialTheme.colorScheme.error,
                showDeleteNewsDialog =  showDeleteNewsDialog,
                isExpanded = isNewsCardExpanded,
                isAdmin = isAdmin,
                onCardClick = onNewsCardClick,
                onDeleteNews = onDeleteNews,
                dismissDeleteDialog = dismissDeleteDialog,
                onShowDeleteNewsDialog = onShowDeleteNewsDialog
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Primære mødested chat", style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        CustomCard(
            title = currentUserPrimaryLocation.locationName,
            bodyText = currentUserPrimaryLocation.latestMessage,
            abbreviation = currentUserPrimaryLocation.abbreviation,
            cardModifier = Modifier
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)),
                    onClick = { onChatLocalNavigate(currentUserPrimaryLocation) }
        )

        if (eventsWithinAWeek.isNotEmpty()) {
            Spacer(modifier = Modifier.height(6.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Kommende events", style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(4.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(CustomColorScheme.Mocha),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    items(eventsWithinAWeek) { event ->
                        CustomEventCard(
                            event = event,
                            onAttend = { onAttend(event.eventID) },
                            onNotAttend = { onNotAttend(event.eventID) },
                            isExpanded = isEventSelected(event.eventID),
                            isAttending = isAttending(event),
                            onCardClick = { onEventCardClick(event.eventID) },
                        )
                    }
                }

            }
        }
    }
}