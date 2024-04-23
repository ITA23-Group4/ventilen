package com.example.ventilen_app.ui.screens.Credentials

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.composables.CustomTextField
import com.example.ventilen_app.ui.composables.CustomFilledButton
import com.example.ventilen_app.ui.composables.TopAuthPageDesign
import com.example.ventilen_app.ui.theme.CustomColorScheme

@Composable
fun CredentialsScreen(
    onNavigateUsername: () -> Unit,
    textEmail: String,
    textPassword: String,
    onValueChangeEmail: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
) {
    Column(
        Modifier.background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally    ) {
        TopAuthPageDesign(
            hasBackButton = true,
            topText = "Hvordan skal vi huske dig?",
            bottomText = "indtast din email og kodeord"
        )
        CustomTextField(text = textEmail) { onValueChangeEmail(it) }
        Spacer(modifier = Modifier.height(30.dp))
        CustomTextField(text = textPassword) { onValueChangePassword(it) }
        Spacer(modifier = Modifier.height(30.dp))
        CustomFilledButton(
            text = "Forsæt", 
            onClick = onNavigateUsername,
            padding = PaddingValues(25.dp,0.dp))
    }

}