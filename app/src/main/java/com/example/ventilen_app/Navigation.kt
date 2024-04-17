package com.example.ventilen_app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.ventilen_app.ui.screens.Welcome.WelcomeScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "auth") {
        navigation(
            startDestination = "welcome",
            route = "auth"
        ){
            composable("welcome") {
                WelcomeScreen(){

                }

            }

        }
    }
}
