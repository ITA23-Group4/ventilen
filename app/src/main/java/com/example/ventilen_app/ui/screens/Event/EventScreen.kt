package com.example.ventilen_app.ui.screens.Event

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ventilen_app.data.models.User
import com.example.ventilen_app.generalViewModels.CurrentUserViewModel
import com.example.ventilen_app.ui.components.CustomEventCardComponent.CustomEventCard
import com.example.ventilen_app.ui.components.CustomEventCardComponent.CustomEventCardViewModel
import com.example.ventilen_app.ui.theme.CustomColorScheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EventScreen(
    currentUser: User,
) {
    val eventScreenViewModel: EventScreenViewModel = remember { EventScreenViewModel() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        items(eventScreenViewModel.events) { event ->
            val customEventCardViewModel: CustomEventCardViewModel = remember {
                CustomEventCardViewModel(event.attendeesByUID.size)
            }
            CustomEventCard(
                title = event.title,
                attendeesAmount = customEventCardViewModel.attendeesCount,
                onAttend = {
                           eventScreenViewModel.addUserToEvent(
                               currentUserUID = currentUser.uid!!,
                               eventID = event.id!!,
                               onSuccess = {
                                   eventScreenViewModel.getEventByID(
                                       eventID = event.id!!,
                                       onSuccess = {customEventCardViewModel.attendeesCount = it}
                                   )
                               }
                           )
                },
                onNotAttend = {
                    eventScreenViewModel.removeUserFromEvent(
                        eventID = event.id!!,
                        currentUserUID = currentUser.uid!!,
                        onSuccess = {
                            eventScreenViewModel.getEventByID(
                                eventID = event.id!!,
                                onSuccess = { customEventCardViewModel.attendeesCount = it }
                            )
                        }
                    )
                }
            )
        }
    }
}
