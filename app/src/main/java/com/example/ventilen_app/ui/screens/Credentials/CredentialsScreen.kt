package com.example.ventilen_app.ui.screens.Credentials

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomTextField
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.TopAuthPageDesign
import com.example.ventilen_app.ui.theme.CustomColorScheme

@Composable
fun CredentialsScreen(
    onNavigateUsername: () -> Unit,
    textEmail: String,
    textPassword: String,
    onValueChangeEmail: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    CustomColumn(
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        TopAuthPageDesign(
            hasBackButton = true,
            topText = "Hvordan skal vi huske dig",
            bottomText = "indtast din email og password",
            onNavigateBack = onNavigateBack
        )
        CustomTextField(text = textEmail, label = "Email") { onValueChangeEmail(it) }

        CustomTextField(text = textPassword, label = "Password") { onValueChangePassword(it) }

        CustomTextField(text = textPassword, label = "Gentag Password") { onValueChangePassword(it) }

        CustomFilledButton(
            text = "Forsæt",
            onClick = onNavigateUsername
        )
    }

}