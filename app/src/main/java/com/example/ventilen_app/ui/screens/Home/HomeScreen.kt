package com.example.ventilen_app.ui.screens.Home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    events: List<Event>,
    isEventSelected: (String) -> Boolean,
    isAttending: (Event) -> Boolean,
    onAttend: (String) -> Unit,
    onNotAttend: (String) -> Unit,
    onEventCardClick: (String) -> Unit,

    // Create news
    showDialog: Boolean,
    dialogDescription: String,
    onDialogDescriptionChange: (String) -> Unit,
    onCreateNews: () -> Unit,
    dismissDialog: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { dismissDialog() },
            dismissButton = {
                CustomFilledButton(
                    text = "Opret",
                    onClick = onCreateNews
                )
            },
            confirmButton = {
                CustomOutlinedButton(
                    text = "Afbryd",
                    onClick = dismissDialog
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
            }
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
                isExpanded = isNewsCardExpanded,
                isAdmin = isAdmin,
                onCardClick = onNewsCardClick,
                onDeleteNews = onDeleteNews
            )
        }

        Text(
            text = "Primære mødested", style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        CustomCard(
            title = currentUserPrimaryLocation.locationName,
            bodyText = currentUserPrimaryLocation.latestMessage,
            abbreviation = currentUserPrimaryLocation.abbreviation,
            onClick = { onChatLocalNavigate(currentUserPrimaryLocation) }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Events oversigt", style = MaterialTheme.typography.headlineMedium,
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
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(events) { event ->
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