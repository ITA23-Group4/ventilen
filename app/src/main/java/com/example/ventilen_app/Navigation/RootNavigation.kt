package com.example.ventilen_app.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.ventilen_app.generalViewModels.AuthViewModel
import com.example.ventilen_app.generalViewModels.ChatViewModel
import com.example.ventilen_app.generalViewModels.CurrentUserViewModel
import com.example.ventilen_app.ui.screens.Location.LocationsViewModel
import com.example.ventilen_app.ui.screens.Event.EventScreen
import com.example.ventilen_app.ui.screens.Event.EventScreenViewModel
import com.example.ventilen_app.ui.screens.Home.HomeScreen

/** @author Marcus, Christian, Nikolaj
 * Root navigation structure of the application.
 */
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
            authNavGraph(navController, currentUserViewModel, authViewModel, locationsViewModel)
        }
        composable("home"){
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
                onNavigateEvent = {navController.navigate("event")},
                chatViewModel = chatViewModel
            )
        }
        composable("event"){
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
