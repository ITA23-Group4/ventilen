package com.example.ventilen_app.ui.screens.Auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.CustomOutlinedButton
import com.example.ventilen_app.ui.components.TopAuthPageDesign
import com.example.ventilen_app.ui.theme.CustomColorScheme

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
@Composable
fun WelcomeScreen(
    onNavigationLogin: () -> Unit,
    onNavigationRegister: () -> Unit,
) {
    CustomColumn(
        modifier = Modifier
        .fillMaxSize()
        .background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        TopAuthPageDesign(
            topText = "Velkommen",
            bottomText = "Login eller tilmeld dig",
            )
        CustomFilledButton(
            text = "Login",
            textColor = CustomColorScheme.OffWhite,
            onClick = onNavigationLogin,
            color = CustomColorScheme.Orange
        )
        CustomOutlinedButton(
            text = "Tilmeld dig",
            onClick = onNavigationRegister
        )
    }
}