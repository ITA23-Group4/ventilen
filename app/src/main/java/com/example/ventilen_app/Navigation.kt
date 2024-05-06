package com.example.ventilen_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.ventilen_app.generalViewModels.AuthViewModel
import com.example.ventilen_app.generalViewModels.ChatViewModel
import com.example.ventilen_app.generalViewModels.CurrentUserViewModel
import com.example.ventilen_app.generalViewModels.LocationViewModel
import com.example.ventilen_app.ui.screens.Username.UsernameScreen
import com.example.ventilen_app.ui.screens.Welcome.WelcomeScreen
import com.example.ventilen_app.ui.screens.Credentials.CredentialsScreen
import com.example.ventilen_app.ui.screens.Event.EventScreen
import com.example.ventilen_app.ui.screens.Event.EventScreenViewModel
import com.example.ventilen_app.ui.screens.Home.HomeScreen
import com.example.ventilen_app.ui.screens.Location.LocationScreen
import com.example.ventilen_app.ui.screens.Login.LoginScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val currentUserViewModel: CurrentUserViewModel = remember { CurrentUserViewModel() }
    val authViewModel: AuthViewModel = remember { AuthViewModel() }
    val eventScreenViewModel: EventScreenViewModel = remember { EventScreenViewModel() } // init here to get all events on launch?
    val locationViewModel: LocationViewModel = remember { LocationViewModel() }

    // TODO: Remove
    currentUserViewModel.logout()
    currentUserViewModel.getCurrentUser()

    NavHost(navController = navController, startDestination = "auth") {
        navigation(
            startDestination = "auth/welcome",
            route = "auth"
        ) {
            composable("auth/welcome") {
                WelcomeScreen(
                    onNavigationLogin = { navController.navigate("auth/login") },
                    onNavigationRegister = { navController.navigate("auth/register") },
                    // TODO: Remove
                    whoUser = { currentUserViewModel.getCurrentUser() }
                )
            }
            composable("auth/login") {
                LoginScreen(
                    onNavigateHome = {
                        authViewModel.loginUser(
                            onLoginSuccess = {
                                currentUserViewModel.getCurrentUser()
                                navController.popBackStack(
                                    route = "auth",
                                    inclusive = true
                                )
                                navController.navigate("home")
                            },
                            onLoginFailure = {
                                navController.navigate("auth/welcome")
                            }
                        )
                    },
                    textEmail = authViewModel.email,
                    textPassword = authViewModel.password,
                    onValueChangeEmail = { authViewModel.email = it },
                    onValueChangePassword = { authViewModel.password = it },
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateRegistration = { navController.navigate("auth/register") }
                )
            }

            navigation(
                startDestination = "auth/register/credentials",
                route = "auth/register"
            ) {
                composable("auth/register/credentials") {
                    CredentialsScreen(
                        onNavigateUsername = { navController.navigate("auth/register/username") },
                        textEmail = authViewModel.email,
                        textPassword = authViewModel.password,
                        onValueChangeEmail = { authViewModel.email = it },
                        onValueChangePassword = { authViewModel.password = it },
                        onNavigateBack = {navController.popBackStack()}
                    )
                }
                composable("auth/register/username") {
                    UsernameScreen(
                        onNavigateLocation = { navController.navigate("auth/register/location") },
                        onNavigateBack = { navController.popBackStack() },
                        onValueChange = { authViewModel.username = it },
                        textUsername = authViewModel.username,
                    )
                }
                composable("auth/register/location") {
                    LocationScreen(
                        onNavigateHome = {
                            authViewModel.registerNewUser(
                                onRegistrationSuccess = {
                                    authViewModel.loginUser(
                                        onLoginSuccess = {
                                            currentUserViewModel.getCurrentUser()
                                            navController.popBackStack(
                                                route = "auth",
                                                inclusive = true
                                            )
                                            navController.navigate("home")
                                        },
                                        onLoginFailure = {
                                            navController.navigate("auth/welcome")
                                        }
                                    )
                                },
                                onRegistrationFailed = {
                                    navController.navigate("auth/welcome")
                                }
                            )
                        },
                        onNavigateBack = { navController.popBackStack() },
                        locations = locationViewModel.locations.map { location -> location.name },
                        selectedLocation = authViewModel.location.name,
                        onLocationValueChanged = { selectedLocationName ->
                            authViewModel.location = locationViewModel.locations.first { it.name == selectedLocationName }
                        }

                    )
                }
            }
        }

        composable("home"){
            val chatViewModel: ChatViewModel = remember { ChatViewModel() } // Initialize ChatViewModel
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
