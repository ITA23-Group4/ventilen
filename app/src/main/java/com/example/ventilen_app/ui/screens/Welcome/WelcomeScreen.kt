package com.example.ventilen_app.ui.screens.Welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomOutlinedButton
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.TopAuthPageDesign
import com.example.ventilen_app.ui.theme.CustomColorScheme

@Composable
fun WelcomeScreen(
    onNavigationLogin: () -> Unit,
    onNavigationRegister: () -> Unit
) {
    Column(
        Modifier.background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAuthPageDesign(false,"Velkommen","log ind eller tilmeld dig")
        CustomFilledButton(
            text = "Login",
            onClick = onNavigationLogin,
            color = CustomColorScheme.Orange,
            padding = PaddingValues(horizontal = 25.dp, vertical = 0.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        CustomOutlinedButton(
            text = "Tilmeld",
            onClick = onNavigationRegister,
            padding = PaddingValues(horizontal = 25.dp, vertical = 0.dp))
    }
}