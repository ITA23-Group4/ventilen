package com.example.ventilen_app.ui.screens.Credentials

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.ventilen_app.ui.composables.CustomTextField
import com.example.ventilen_app.ui.composables.CustomFilledButton

@Composable
fun CredentialsScreen(
    onNavigateUsername: () -> Unit,
    textEmail: String,
    textPassword: String,
    onValueChangeEmail: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextField(text = textEmail, label = "Email") { onValueChangeEmail(it) }
        CustomTextField(text = textPassword, label = "Password") { onValueChangePassword(it) }

        CustomFilledButton(text = "Forsæt", onClick = onNavigateUsername)
    }

}