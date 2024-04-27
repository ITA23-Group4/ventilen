package com.example.ventilen_app

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.ventilen_app.generalViewModels.AuthViewModel
import com.example.ventilen_app.generalViewModels.CurrentUserViewModel
import com.example.ventilen_app.ui.screens.Username.UsernameScreen
import com.example.ventilen_app.ui.screens.Welcome.WelcomeScreen
import com.example.ventilen_app.ui.screens.Credentials.CredentialsScreen
import com.example.ventilen_app.ui.screens.Event.EventScreen
import com.example.ventilen_app.ui.screens.Home.HomeScreen
import com.example.ventilen_app.ui.screens.Location.LocationScreen
import com.example.ventilen_app.ui.screens.Login.LoginScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val currentUserViewModel: CurrentUserViewModel = remember { CurrentUserViewModel() }
    val authViewModel: AuthViewModel = remember { AuthViewModel() }

    NavHost(navController = navController, startDestination = "auth") {
        navigation(
            startDestination = "auth/welcome",
            route = "auth"
        ) {
            composable("auth/welcome") {
                WelcomeScreen(
                    onNavigationLogin = { navController.navigate("auth/login") },
                    onNavigationRegister = { navController.navigate("auth/register") }
                )
            }
            composable("auth/login") {
                LoginScreen(
                    onNavigateHome = {
                        authViewModel.loginUser(
                            navigateOnLoginSuccess = {
                                currentUserViewModel.getCurrentUser()
                                navController.popBackStack(
                                    route = "auth",
                                    inclusive = true
                                )
                                navController.navigate("home")
                            },
                            onLoginFailed = {
                                Log.d("FAILED!", "${authViewModel.email},${authViewModel.password}")
                            }
                        )
                    },
                    textEmail = authViewModel.email,
                    textPassword = authViewModel.password,
                    onValueChangeEmail = { authViewModel.email = it },
                    onValueChangePassword = { authViewModel.password = it },
                    onNavigateBack = { navController.popBackStack() }
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
                    // TODO: Should come from database
                    val locations = listOf("København", "Århus", "Aalborg", "Odense")
                    LocationScreen(
                        onNavigateHome = {
                            authViewModel.registerNewUser(
                                onRegistrationSuccess = {
                                    // Login if registration completed
                                    authViewModel.loginUser(
                                        navigateOnLoginSuccess = {
                                            currentUserViewModel.getCurrentUser()
                                            navController.popBackStack(
                                                route = "auth",
                                                inclusive = true
                                            )
                                            navController.navigate("home")
                                        },
                                        onLoginFailed = {
                                            Log.d("FAILED!", "${authViewModel.email},${authViewModel.password}")
                                        }
                                    )
                                },
                                onRegistrationFailed = {
                                    Log.d("REGISTER_USER", "Failed to register new user")
                                }
                            )
                        },
                        onNavigateBack = { navController.popBackStack() },
                        locations = locations,
                        selectedLocation = authViewModel.location,
                        onLocationValueChanged = { authViewModel.location = it }
                    )
                }
            }
        }

        composable("home"){
            HomeScreen(
                currentUserViewModel.currentUser?.username.toString(),
                currentUserViewModel.currentUser?.uid.toString(),
                onNavigateEvent = {navController.navigate("event")}
            )
        }
        composable("event"){
            EventScreen(currentUserViewModel.currentUser!!)
        }
    }
}
