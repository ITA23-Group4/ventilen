package com.example.ventilen_app

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.ventilen_app.ui.screens.Username.UsernameScreen
import com.example.ventilen_app.ui.screens.Welcome.WelcomeScreen
import com.example.ventilen_app.ui.screens.Credentials.CredentialsScreen
import com.example.ventilen_app.ui.screens.Location.LocationScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val authViewModel:AuthViewModel = remember {
        AuthViewModel()
    }
    NavHost(navController = navController, startDestination = "auth") {
        navigation(
            startDestination = "auth/welcome",
            route = "auth"
        ){

            composable("auth/welcome") {
                WelcomeScreen(){
                    navController.navigate("register")
                }

            }

            navigation(
                startDestination = "auth/register/credentials",
                route = "register"
            ) {
                composable("auth/register/credentials"){
                    CredentialsScreen(
                        onNavigateUsername = { navController.navigate("auth/register/username") },
                        textEmail = authViewModel.email,
                        textPassword = authViewModel.password,
                        onValueChangeEmail = {authViewModel.email = it},
                        onValueChangePassword = {authViewModel.password = it},
                    )
                }
                composable("auth/register/username"){
                    UsernameScreen(
                        onNavigateLocation = { navController.navigate("auth/register/location") },
                        onNavigateBack = {  },
                        onValueChange = {authViewModel.username = it},
                        text = authViewModel.username
                    )
                }
                composable("auth/register/location"){
                    // TODO: Should come from database
                    val locations = listOf("København", "Århus", "Aalborg", "Odense")
                    LocationScreen(
                        onNavigateHome = { Log.d("TAGTAGTAGTAG", "${authViewModel.email} ${authViewModel.username} ${authViewModel.password} ${authViewModel.location}" )},
                        onNavigateBack = {  },
                        locations = locations,
                        selectedLocation = authViewModel.location,
                        onLocationValueChanged = { authViewModel.location = it }
                    )
                }
            }
        }
    }
}
