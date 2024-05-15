package com.example.ventilen_app.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.ventilen_app.generalViewModels.AuthViewModel
import com.example.ventilen_app.generalViewModels.ChatViewModel
import com.example.ventilen_app.ui.components.scaffolds.CustomBottomNavigationBar
import com.example.ventilen_app.ui.components.scaffolds.LocalChatScaffold
import com.example.ventilen_app.ui.screens.Chat.ChatHubScreen
import com.example.ventilen_app.ui.screens.Chat.ChatLocalScreen
import com.example.ventilen_app.ui.screens.Event.EventScreen
import com.example.ventilen_app.ui.screens.Event.EventViewModel
import com.example.ventilen_app.ui.screens.Home.HomeScreen

/**
 * Root navigation structure of the application.
 *
 * @author Marcus, Christian, Nikolaj
 */
@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavigation() {
    // Initialize navigation controller
    val navController = rememberNavController()

    // Initialize view models
    val authViewModel: AuthViewModel = viewModel<AuthViewModel>()
    val eventViewModel: EventViewModel = viewModel<EventViewModel>()
    val chatViewModel: ChatViewModel = viewModel<ChatViewModel>()

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
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text("Home") },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                            titleContentColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                },
                bottomBar = {
                    CustomBottomNavigationBar(
                        currentRoute = navController.currentDestination!!.route!!,
                        onNavigateEvent = { navController.navigate("event") },
                        onNavigateChat = { navController.navigate("chat") }
                    )
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    HomeScreen()
                }
            }
        }
        navigation(
            startDestination = "chat/hub",
            route = "chat"
        ) {
            composable("chat/hub") {
                Log.d("CurrentUserChat", "${chatViewModel.userRepository.currentUser}")
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text("Chat") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                                titleContentColor = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    },
                    bottomBar = {
                        CustomBottomNavigationBar(
                            currentRoute = navController.currentDestination!!.route!!,
                            onNavigateEvent = { navController.navigate("event") },
                            onNavigateHome = { navController.navigate("home") }
                        )
                    }
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        chatViewModel.getLatestMessagesFromEachLocation() // Get the latest messages from each location in the database, before navigating to the ChatHubScreen TODO: LOOK AT
                        ChatHubScreen(
                            locationsExcludingCurrentUserPrimaryLocation = chatViewModel.locationsWithLatestMessages.filter { location ->
                                location.locationID != chatViewModel.userRepository.currentUser?.primaryLocationID //TODO make function and userRepository private
                            },
                            onChatLocalNavigate = {
                                chatViewModel.selectedLocation = it
                                navController.navigate("chat/local")
                            },
                            currentUserPrimaryLocation = chatViewModel.locationsWithLatestMessages.find { location ->
                                location.locationID == chatViewModel.userRepository.currentUser?.primaryLocationID //TODO make function and userRepository private
                            }!!
                        )
                    }
                }
            }
            composable("chat/local") {
                chatViewModel.getLocalMessages(chatViewModel.selectedLocation.locationID!!) // Get the local messages for the selected location
                LocalChatScaffold(
                    locationName = chatViewModel.selectedLocation.locationName,
                    onNavigateBack = { navController.navigate("chat/hub") },
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
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                                titleContentColor = MaterialTheme.colorScheme.onSurface
                            ),
                            title = { Text("Events") }
                        )
                    },
                    bottomBar = {
                        CustomBottomNavigationBar(
                            currentRoute = navController.currentDestination!!.route!!,
                            onNavigateHome = { navController.navigate("home") },
                            onNavigateChat = { navController.navigate("chat") }
                        )
                    }
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
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
                                    event = event,
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
