package com.example.ventilen_app.ui.screens.Login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.composables.CustomFilledButton
import com.example.ventilen_app.ui.composables.CustomTextField
import com.example.ventilen_app.ui.composables.TopAuthPageDesign

@Composable
fun LoginScreen(
    onNavigateHome: () -> Unit,
    textEmail: String,
    textPassword: String,
    onValueChangeEmail: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAuthPageDesign(hasBackButton = true, topText = "Log ind", bottomText = "Indtast din email og password")
        CustomTextField(text = textEmail, label = "Email") { onValueChangeEmail(it) }
        Spacer(modifier = Modifier.height(30.dp))

        CustomTextField(text = textPassword, label = "Password") { onValueChangePassword(it) }

        Spacer(modifier = Modifier.height(30.dp))

        CustomFilledButton(
            text = "Forsæt",
            onClick = onNavigateHome,
            padding = PaddingValues(horizontal = 25.dp, vertical = 0.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row {
            Text(text = "Har du ikke en bruger?", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Tilmeld Dig", style = MaterialTheme.typography.headlineSmall)
        }

    }

}