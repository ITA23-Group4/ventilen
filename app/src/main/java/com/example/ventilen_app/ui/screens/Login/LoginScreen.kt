package com.example.ventilen_app.ui.screens.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
) {
    Column(modifier = Modifier.fillMaxSize()
        .background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        TopAuthPageDesign(hasBackButton = true, topText = "Log ind", bottomText = "Indtast din email og password")
        CustomTextField(text = textEmail, label = "Email") { onValueChangeEmail(it) }

        CustomTextField(text = textPassword, label = "Password") { onValueChangePassword(it) }

        CustomFilledButton(
            text = "Forsæt",
            onClick = onNavigateHome,
            padding = PaddingValues(horizontal = 25.dp, vertical = 0.dp)
        )
        
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Har du ikke en bruger?", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Tilmeld Dig", style = MaterialTheme.typography.headlineSmall)
        }

    }

}