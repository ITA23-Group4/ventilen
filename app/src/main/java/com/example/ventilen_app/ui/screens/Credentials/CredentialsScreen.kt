package com.example.ventilen_app.ui.screens.Credentials

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomTextField
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.TopAuthPageDesign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CredentialsScreen(
    onNavigateUsername: () -> Unit,
    textEmail: String,
    hasEmailError: Boolean,
    password: String,
    repeatPassword: String,
    hasPasswordError: Boolean,
    onValueChangeEmail: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
    onValueChangePasswordRepeat: (String) -> Unit,
    ) {
    CustomColumn(
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        TopAuthPageDesign(
            topText = "Hvordan skal vi huske dig",
            bottomText = "indtast din email og password",
        )

        CustomTextField(
            text = textEmail,
            label = "Email",
            onValueChange = { onValueChangeEmail(it) },
            hasError = hasEmailError
        )

        CustomTextField(
            text = password,
            label = "Password" ,
            onValueChange = { onValueChangePassword(it) },
            hasError = hasPasswordError
        )

        CustomTextField(
            text = repeatPassword,
            label = "Gentag Password",
            onValueChange =  { onValueChangePasswordRepeat(it) },
            hasError = hasPasswordError
        )

        CustomFilledButton(
            text = "Forsæt",
            onClick = { onNavigateUsername() },
            isEnabled = !hasEmailError && !hasPasswordError
        )
    }

}