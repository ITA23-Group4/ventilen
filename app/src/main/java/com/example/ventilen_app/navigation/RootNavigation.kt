package com.example.ventilen_app.navigation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.ventilen_app.generalViewModels.AuthViewModel
import com.example.ventilen_app.generalViewModels.ChatViewModel
import com.example.ventilen_app.generalViewModels.LocationViewModel
import com.example.ventilen_app.ui.components.scaffolds.CustomBottomNavigationBar
import com.example.ventilen_app.ui.screens.Chat.ChatHubScreen
import com.example.ventilen_app.ui.screens.Chat.ChatLocalScreen
import com.example.ventilen_app.ui.screens.Event.EventScreen
import com.example.ventilen_app.ui.screens.Event.EventScreenViewModel
import com.example.ventilen_app.ui.screens.Home.HomeScreen

/**
 * Root navigation structure of the application.
 *
 * @author Marcus, Christian, Nikolaj
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootNavigation() {
    // Initialize navigation controller
    val navController = rememberNavController()

    // Initialize view models
    val authViewModel: AuthViewModel = viewModel<AuthViewModel>()
    val eventScreenViewModel: EventScreenViewModel = viewModel<EventScreenViewModel>()
    val locationsViewModel: LocationViewModel = viewModel<LocationViewModel>()
    val chatViewModel: ChatViewModel = viewModel<ChatViewModel>()

    NavHost(navController = navController, startDestination = "auth") {
        navigation(
            startDestination = "auth/welcome",
            route = "auth"
        ) {
            authNavGraph(
                navController = navController,
                authViewModel = authViewModel,
                locationsViewModel = locationsViewModel
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
                 // Get the latest messages from each location in the database, before navigating to the ChatHubScreen TODO: LOOK AT
                ChatHubScreen(
                    locationsExcludingCurrentUserPrimaryLocation = locationsViewModel.getLocationsExcludingPrimaryLocation(),
                    onChatLocalNavigate = {
                        chatViewModel.selectedLocationChatID = it
                        navController.navigate("chat/local")
                    },
                    currentUserPrimaryLocation = locationsViewModel.getPrimaryLocation()
                )
            }
            composable("chat/local") {
                chatViewModel.getLocalMessages(chatViewModel.selectedLocationChatID) // Get the local messages for the selected location TODO: LOOK AT
                Log.d("Chat", chatViewModel.localMessages.toString())
                // It is logging this: androidx.lifecycle.MutableLiveData@********
                // It should log the list of messages -- IDK dude


                ChatLocalScreen(
                    listOfLocationMessages = chatViewModel.messages, // TODO: USE CORRECT LIST (LOCAL MESSAGES)
                    onSendMessage = {
                        chatViewModel.sendMessage()
                    },
                    currentMessage = chatViewModel.currentMessage,
                    onCurrentMessageChange = {
                        chatViewModel.currentMessage = it
                    }
                )
            }
        }
        composable("event") {
            Scaffold(
                topBar = {
                    TopAppBar(
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
                        events = eventScreenViewModel.events,
                        onAttend = {
                            eventScreenViewModel.addUserToEvent(
                                eventID = it
                            )
                        },
                        onNotAttend = {
                            eventScreenViewModel.removeUserFromEvent(
                                eventID = it,
                            )
                        }
                    )
                }
            }
        }
    }
}

