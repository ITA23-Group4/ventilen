package com.example.ventilen_app.ui.screens.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.CustomTextField
import com.example.ventilen_app.ui.components.TopAuthPageDesign
import com.example.ventilen_app.ui.theme.CustomColorScheme

@Composable
fun LoginScreen(
    onNavigateHome: () -> Unit,
    textEmail: String,
    textPassword: String,
    onValueChangeEmail: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
    onNavigateRegistration: () -> Unit
) {
    CustomColumn(modifier = Modifier
        .fillMaxSize()
        .background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        TopAuthPageDesign(
            topText = "Log ind",
            bottomText = "Indtast din email og password",
        )
        CustomTextField(text = textEmail, label = "Email") { onValueChangeEmail(it) }

        CustomTextField(text = textPassword, label = "Password") { onValueChangePassword(it) }

        CustomFilledButton(
            text = "Forsæt",
            onClick = onNavigateHome,
        )

        // TODO placement should be fixed with CustomColumn
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Har du ikke en bruger?", style = MaterialTheme.typography.headlineMedium)
            TextButton(onClick = onNavigateRegistration) {
                Text(text = "Tilmeld Dig", style = MaterialTheme.typography.headlineSmall)
            }
        }

    }

}