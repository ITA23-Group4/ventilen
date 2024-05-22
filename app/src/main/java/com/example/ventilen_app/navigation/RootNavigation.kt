package com.example.ventilen_app.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.ventilen_app.ui.components.scaffolds.ChatHubScreenScaffold
import com.example.ventilen_app.ui.components.scaffolds.CreateEventScaffold
import com.example.ventilen_app.ui.components.scaffolds.EventScaffold
import com.example.ventilen_app.ui.components.scaffolds.HomeScreenScaffold
import com.example.ventilen_app.viewmodels.AuthViewModel
import com.example.ventilen_app.viewmodels.ChatViewModel
import com.example.ventilen_app.ui.components.scaffolds.LocalChatScaffold
import com.example.ventilen_app.ui.screens.Chat.ChatHubScreen
import com.example.ventilen_app.ui.screens.Chat.ChatLocalScreen
import com.example.ventilen_app.ui.screens.CreateEvent.CreateEventScreen
import com.example.ventilen_app.viewmodels.CreateEventViewModel
import com.example.ventilen_app.ui.screens.Event.EventScreen
import com.example.ventilen_app.viewmodels.EventViewModel
import com.example.ventilen_app.ui.screens.Home.HomeScreen
import com.example.ventilen_app.viewmodels.HomeViewModel

/**
 * Root navigation structure of the application.
 *
 * @author Marcus, Christian, Nikolaj
 */
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RootNavigation() {
    // Initialize navigation controller
    val navController = rememberNavController()

    // Initialize view models
    val authViewModel: AuthViewModel = viewModel<AuthViewModel>()
    val homeViewModel: HomeViewModel = viewModel<HomeViewModel>()
    val eventViewModel: EventViewModel = viewModel<EventViewModel>()
    val chatViewModel: ChatViewModel = viewModel<ChatViewModel>()
    val createEventViewModel: CreateEventViewModel = viewModel<CreateEventViewModel>()

    NavHost(navController = navController, startDestination = "auth") {
        navigation(
            startDestination = "auth/welcome",
            route = "auth"
        ) {
            AuthNavGraph(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable("home") {
            HomeScreenScaffold(
                currentRoute = navController.currentDestination!!.route!!,
                isAdmin = true,
                onNavigateEvent = { navController.navigate("event") },
                onNavigateChat = { navController.navigate("chat") },
                onCreateNews = { homeViewModel.toggleDialog() }
            ) {
                HomeScreen(
                    currentUserPrimaryLocation = chatViewModel.locationsWithLatestMessages[0], // TODO: is it okay to borrow func from other ViewModels?
                    onChatLocalNavigate = {
                        chatViewModel.selectedLocation = it
                        navController.navigate("chat/local")
                    },
                    events = eventViewModel.events.sorted(),
                    onAttend = {
                        eventViewModel.addUserToEvent(
                            eventID = it
                        )
                    },
                    onNotAttend = {
                        eventViewModel.removeUserFromEvent(
                            eventID = it,
                        )
                    },
                    isEventSelected = { eventViewModel.isSelectedEvent(it) },
                    onEventCardClick = { eventViewModel.toggleEventCard(it) },
                    isAttending = { event ->
                        eventViewModel.isCurrentUserAttendingEvent(
                            event = event
                        )
                    },
                    showDialog = homeViewModel.showDialog,
                    dialogDescription = homeViewModel.newsDescription,
                    onDialogDescriptionChange = { homeViewModel.newsDescription = it },
                    onCreateNews = {
                        homeViewModel.createNewsForPrimaryLocation()
                        homeViewModel.toggleDialog()
                    },
                    dismissDialog = { homeViewModel.toggleDialog() }
                )
            }
        }
        navigation(
            startDestination = "chat/hub",
            route = "chat"
        ) {
            composable("chat/hub") {
                ChatHubScreenScaffold(
                    currentRoute = "chat/hub",
                    onNavigateEvent = { navController.navigate("event") },
                    onNavigateHome = { navController.navigate("home") }
                ) {
                    ChatHubScreen(
                        locationsExcludingCurrentUserPrimaryLocation = chatViewModel.locationsWithLatestMessages,
                        onChatLocalNavigate = {
                            chatViewModel.selectedLocation = it
                            navController.navigate("chat/local")
                        },
                        currentUserPrimaryLocation = chatViewModel.locationsWithLatestMessages[0]
                    )
                }
            }
            composable("chat/local") {
                chatViewModel.getLocalMessages(chatViewModel.selectedLocation.locationID!!) // Get the local messages for the selected location
                val lastDestinationRoute: String =
                    navController.previousBackStackEntry!!.destination.route!!
                LocalChatScaffold(
                    locationName = chatViewModel.selectedLocation.locationName,
                    onNavigateBack = { navController.navigate(lastDestinationRoute) },
                    currentMessage = chatViewModel.currentMessage,
                    onCurrentMessageChange = { chatViewModel.currentMessage = it },
                    onSendMessage = {
                        chatViewModel.sendMessage()
                    },
                ) {
                    ChatLocalScreen(
                        listOfLocationMessages = chatViewModel.localMessages.collectAsState(),
                        isCurrentUserSender = {
                            chatViewModel.isCurrentUserSender(it)
                        }
                    )
                }
            }
            composable("event") {
                eventViewModel.clearSelectedEventCard() // TODO: Scuffed transition on navigation
                EventScaffold(
                    currentRoute = "event",
                    isAdmin = eventViewModel.isAdmin(),
                    onNavigateHome = { navController.navigate("home") },
                    onNavigateChat = { navController.navigate("chat") },
                    onNavigateCreateEvent = { navController.navigate("event/create") }
                ) {
                    EventScreen(
                        events = eventViewModel.events.sorted(),
                        onAttend = {
                            eventViewModel.addUserToEvent(
                                eventID = it
                            )
                        },
                        onNotAttend = {
                            eventViewModel.removeUserFromEvent(
                                eventID = it,
                            )
                        },
                        isEventSelected = { eventViewModel.isSelectedEvent(it) },
                        onEventCardClick = { eventViewModel.toggleEventCard(it) },
                        isAttending = { event ->
                            eventViewModel.isCurrentUserAttendingEvent(
                                event = event
                            )
                        }
                    )
                }


            }
        }
        composable("event/create") {
            createEventViewModel.context = LocalContext.current
            CreateEventScaffold(
                onNavigateBack = { navController.navigate("event") },
            ) {
                CreateEventScreen(
                    eventTitle = createEventViewModel.eventTitle,
                    eventDescription = createEventViewModel.eventDescription,
                    eventAddress = createEventViewModel.eventAddress,
                    eventPrice = createEventViewModel.eventPrice,

                    selectedStartDateTime = createEventViewModel.eventStartDateTime,
                    selectedEndDateTime = createEventViewModel.eventEndDateTime,
                    showStartDateTimePicker = { createEventViewModel.showStartDateTimePickerForUser() },
                    showEndDateTimePicker = { createEventViewModel.showEndDateTimePickerForUser() },

                    onValueChangeTitle = { createEventViewModel.eventTitle = it },
                    onValueChangeDescription = { createEventViewModel.eventDescription = it },
                    onValueChangeAddress = { createEventViewModel.eventAddress = it },
                    onValueChangePrice = { createEventViewModel.eventPrice = it },
                    onCreateEvent = { createEventViewModel.createEvent() },
                    showDialog = createEventViewModel.showDialog,
                    dismissDialog = { createEventViewModel.dismissDialog() }
                )
            }
        }
    }
}

