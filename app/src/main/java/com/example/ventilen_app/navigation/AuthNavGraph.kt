package com.example.ventilen_app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.ventilen_app.generalViewModels.AuthViewModel
import com.example.ventilen_app.generalViewModels.CurrentUserViewModel
import com.example.ventilen_app.generalViewModels.LocationViewModel
import com.example.ventilen_app.ui.components.scaffolds.AuthScaffold
import com.example.ventilen_app.ui.screens.Credentials.CredentialsScreen
import com.example.ventilen_app.ui.screens.Location.LocationScreen
import com.example.ventilen_app.ui.screens.Login.LoginScreen
import com.example.ventilen_app.ui.screens.Username.UsernameScreen
import com.example.ventilen_app.ui.screens.Welcome.WelcomeScreen

/**
 * Authentication navigation graph for the authentication flow, which includes screens
 * for user login & registration.
 *
 * @param navController The same navigation controller as used in RootNavigation
 * @param currentUserViewModel The view model for managing current user information.
 * @param authViewModel The view model for authentication logic.
 * @param locationsViewModel The view model for managing location information.
 * @author Marcus, Christian, Nikolaj
 */
fun NavGraphBuilder.AuthNavGraph(
    navController: NavController,
    currentUserViewModel: CurrentUserViewModel,
    authViewModel: AuthViewModel,
    locationsViewModel: LocationViewModel
) {
    composable("auth/welcome") {
        AuthScaffold(
            showBackButton = false
        ) {
            WelcomeScreen(
                onNavigationLogin = { navController.navigate("auth/login") },
                onNavigationRegister = { navController.navigate("auth/register") },
                // TODO: Remove
                whoUser = { currentUserViewModel.getCurrentUser() }
            )
        }
    }
    composable("auth/login") {
        AuthScaffold(
            onNavigateBack = { navController.popBackStack() }
        ) {
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
    }

    // Nested navigation for register flow
    navigation(
        startDestination = "auth/register/credentials",
        route = "auth/register"
    ) {
        composable("auth/register/credentials") {
            AuthScaffold(
                onNavigateBack = {
                    navController.popBackStack(
                        route = "auth/register",
                        inclusive = true
                    )
                }
            ) {
                CredentialsScreen(
                    onNavigateUsername = { navController.navigate("auth/register/username") },
                    textEmail = authViewModel.email,
                    textPassword = authViewModel.password,
                    onValueChangeEmail = { authViewModel.email = it },
                    onValueChangePassword = { authViewModel.password = it },
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
        composable("auth/register/username") {
            AuthScaffold(
                onNavigateBack = { navController.popBackStack() }
            ) {
                UsernameScreen(
                    onNavigateLocation = { navController.navigate("auth/register/location") },
                    onNavigateBack = { navController.popBackStack() },
                    onValueChange = { authViewModel.username = it },
                    textUsername = authViewModel.username,
                )
            }
        }
        composable("auth/register/location") {
            AuthScaffold(
                onNavigateBack = { navController.popBackStack() }
            ) {
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
                    locations = locationsViewModel.locations.map { it.name },
                    selectedLocation = authViewModel.location.name,
                    onLocationValueChanged = { selectedLocationName ->
                        authViewModel.location = locationsViewModel.mapLocationNameToLocation.get(selectedLocationName)!!
                    }
                )
            }
        }
    }
}
