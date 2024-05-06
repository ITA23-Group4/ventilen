package com.example.ventilen_app.ui.screens.Welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomOutlinedButton
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.TopAuthPageDesign
import com.example.ventilen_app.ui.theme.CustomColorScheme

@Composable
fun WelcomeScreen(
    onNavigationLogin: () -> Unit,
    onNavigationRegister: () -> Unit
) {
    CustomColumn(
        modifier = Modifier
        .fillMaxSize()
        .background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Spacer(modifier = Modifier.size(20.dp))
        TopAuthPageDesign(
            hasBackButton = false,
            topText = "Velkommen",
            bottomText = "log ind eller tilmeld dig",
            onNavigateBack = {}
            )
        CustomFilledButton(
            text = "Login",
            onClick = onNavigationLogin,
            color = CustomColorScheme.Orange,
            padding = PaddingValues(horizontal = 25.dp, vertical = 0.dp)
        )
        CustomOutlinedButton(
            text = "Tilmeld dig",
            onClick = onNavigationRegister,
            padding = PaddingValues(horizontal = 25.dp, vertical = 0.dp))
    }
}