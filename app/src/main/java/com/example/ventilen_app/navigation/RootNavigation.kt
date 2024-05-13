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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.ventilen_app.data.models.Message
import com.example.ventilen_app.generalViewModels.AuthViewModel
import com.example.ventilen_app.generalViewModels.ChatViewModel
import com.example.ventilen_app.generalViewModels.CurrentUserViewModel
import com.example.ventilen_app.generalViewModels.LocationViewModel
import com.example.ventilen_app.ui.components.scaffolds.CustomBottomNavigationBar
import com.example.ventilen_app.ui.screens.Chat.ChatHubScreen
import com.example.ventilen_app.ui.screens.Chat.ChatLocalScreen
import com.example.ventilen_app.ui.screens.Event.EventScreen
import com.example.ventilen_app.ui.screens.Event.EventScreenViewModel
import com.example.ventilen_app.ui.screens.Home.HomeScreen
import java.util.Date

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
    val currentUserViewModel: CurrentUserViewModel = viewModel<CurrentUserViewModel>()
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
                currentUserViewModel = currentUserViewModel,
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
                    HomeScreen(
                        textUsername = currentUserViewModel.currentUser?.username.toString(),
                        textUID = currentUserViewModel.currentUser?.uid.toString(),
                    )
                }
            }
        }
        navigation(
            startDestination = "chat/hub",
            route = "chat"
        ) {
            composable("chat/hub") {
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
                                location.locationID != currentUserViewModel.currentUser?.primaryLocationID },
                            onChatLocalNavigate = {
                                chatViewModel.selectedLocationChatID = it
                                navController.navigate("chat/local")
                            },
                            currentUserPrimaryLocation = chatViewModel.locationsWithLatestMessages.find { location ->
                                location.locationID == currentUserViewModel.currentUser?.primaryLocationID
                            }!!
                        )
                    }
                }
            }
            composable("chat/local") {
                chatViewModel.getLocalMessages(chatViewModel.selectedLocationChatID) // Get the local messages for the selected location
                ChatLocalScreen(
                    listOfLocationMessages = chatViewModel.localMessages.collectAsState(),
                    onSendMessage = {
                        chatViewModel.sendMessage(
                        message = Message(
                            senderUID = currentUserViewModel.currentUser?.uid!!,
                            message = chatViewModel.currentMessage,
                            timestamp = Date(),
                            locationID = chatViewModel.selectedLocationChatID,
                            username = currentUserViewModel.currentUser?.username!!
                        )
                    )
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
                                currentUserUID = currentUserViewModel.currentUser?.uid!!,
                                eventID = it
                            )
                        },
                        onNotAttend = {
                            eventScreenViewModel.removeUserFromEvent(
                                eventID = it,
                                currentUserUID = currentUserViewModel.currentUser?.uid!!
                            )
                        }
                    )
                }
            }
        }
    }
}

