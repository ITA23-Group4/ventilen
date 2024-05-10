package com.example.ventilen_app.navigation

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.ventilen_app.generalViewModels.AuthViewModel
import com.example.ventilen_app.generalViewModels.ChatViewModel
import com.example.ventilen_app.generalViewModels.CurrentUserViewModel
import com.example.ventilen_app.ui.components.scaffolds.AuthScaffold
import com.example.ventilen_app.ui.components.scaffolds.CustomBottomNavigationBar
import com.example.ventilen_app.ui.screens.Credentials.CredentialsScreen
import com.example.ventilen_app.ui.screens.Event.EventScreen
import com.example.ventilen_app.ui.screens.Event.EventScreenViewModel
import com.example.ventilen_app.ui.screens.Home.HomeScreen
import com.example.ventilen_app.ui.screens.Location.LocationScreen
import com.example.ventilen_app.ui.screens.Location.LocationsViewModel
import com.example.ventilen_app.ui.screens.Login.LoginScreen
import com.example.ventilen_app.ui.screens.Username.UsernameScreen
import com.example.ventilen_app.ui.screens.Welcome.WelcomeScreen

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
    val currentUserViewModel: CurrentUserViewModel = remember { CurrentUserViewModel() }
    val authViewModel: AuthViewModel = remember { AuthViewModel() }
    val eventScreenViewModel: EventScreenViewModel = remember { EventScreenViewModel() }
    val locationsViewModel: LocationsViewModel = remember { LocationsViewModel() }

    // TODO: Remove
    currentUserViewModel.logout()
    currentUserViewModel.getCurrentUser()

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
                    // Initialize ChatViewModel
                    val chatViewModel: ChatViewModel = remember { ChatViewModel() }
                    HomeScreen(
                        textUsername = currentUserViewModel.currentUser?.username.toString(),
                        textUID = currentUserViewModel.currentUser?.uid.toString(),
                        // TODO: Remove
                        logout = {
                            currentUserViewModel.logout()
                            navController.navigate("auth")
                        },
                        getCurrentUser = {
                            currentUserViewModel.getCurrentUser()
                        },
                        onNavigateEvent = { navController.navigate("event") },
                        chatViewModel = chatViewModel
                    )
                }
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

