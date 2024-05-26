package com.example.ventilen_app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.ventilen_app.viewmodels.AuthViewModel
import com.example.ventilen_app.ui.components.scaffolds.AuthScaffold
import com.example.ventilen_app.ui.screens.Credentials.CredentialsScreen
import com.example.ventilen_app.ui.screens.Location.LocationScreen
import com.example.ventilen_app.ui.screens.Login.LoginScreen
import com.example.ventilen_app.ui.screens.Username.UsernameScreen
import com.example.ventilen_app.ui.screens.Welcome.WelcomeScreen
import com.example.ventilen_app.viewmodels.EventViewModel

/**
 * Authentication navigation graph for the authentication flow, which includes screens
 * for user login & registration.
 *
 * @param navController The same navigation controller as used in RootNavigation
 * @param userViewModel The view model for managing current user information.
 * @param authViewModel The view model for authentication logic.
 * @param locationsViewModel The view model for managing location information.
 * @author Marcus, Christian, Nikolaj
 */
fun NavGraphBuilder.AuthNavGraph(
    navController: NavController,
    authViewModel: AuthViewModel,
    eventViewModel: EventViewModel
) {
    composable("auth/welcome") {
        AuthScaffold(
            showBackButton = false
        ) {
            WelcomeScreen(
                onNavigationLogin = { navController.navigate("auth/login") },
                onNavigationRegister = { navController.navigate("auth/register") },
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
                            eventViewModel.getEvents()
                            navController.popBackStack(
                                route = "auth",
                                inclusive = true
                            )
                            navController.navigate("home")
                        },
                        onLoginFailure = {
                            authViewModel.hasLoginError = true
                        }
                    )
                },
                textEmail = authViewModel.email,
                textPassword = authViewModel.password,
                hasLoginError = authViewModel.hasLoginError,
                onValueChangeEmail = { authViewModel.email = it },
                onValueChangePassword = { authViewModel.password = it },
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
                    hasEmailError = authViewModel.hasEmailError,
                    repeatPassword = authViewModel.passwordRepeat,
                    password = authViewModel.password,
                    hasPasswordError = authViewModel.hasPasswordError,
                    hasRepeatedPasswordError = authViewModel.hasPasswordRepeatedError,
                    credentialsFieldsNotEmpty = { authViewModel.credentialsFieldsNotEmptyAndValid() },
                    onValueChangeEmail = { authViewModel.changeEmail(it) },
                    onValueChangePassword = { authViewModel.changePassword(it) },
                    onValueChangePasswordRepeat = { authViewModel.changeRepeatedPassword(it) },
                )
            }
        }
        composable("auth/register/username") {
            AuthScaffold(
                onNavigateBack = { navController.popBackStack() }
            ) {
                UsernameScreen(
                    onNavigateLocation = { navController.navigate("auth/register/location") },
                    onValueChange = { authViewModel.changeUsername(it) },
                    textUsername = authViewModel.username,
                    hasUsernameError = authViewModel.hasUsernameError,
                    showDialog = authViewModel.showDialog,
                    usernameFieldsNotEmpty = { authViewModel.usernameFieldNotEmptyAndValid() },
                    onInformationClick = { authViewModel.showDialog = true },
                    dismissDialog = { authViewModel.showDialog = false }
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
                                        eventViewModel.getEvents()
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
                    selectedLocation = authViewModel.location.locationName,
                    locations = authViewModel.getLocationName(),
                    hasLocationError = authViewModel.hasLocationError,
                    onLocationValueChanged = { selectedLocationName ->
                        authViewModel.onLocationValueChanged(selectedLocationName)
                    }
                )
            }
        }
    }
}

