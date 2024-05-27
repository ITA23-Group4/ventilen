package com.example.ventilen_app.ui.screens.Credentials

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.CustomPasswordTextField
import com.example.ventilen_app.ui.components.CustomTextField
import com.example.ventilen_app.ui.components.TopAuthPageDesign

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
@Composable
fun CredentialsScreen(
    onNavigateUsername: () -> Unit,
    textEmail: String,
    hasEmailError: Boolean,
    password: String,
    repeatPassword: String,
    hasRepeatedPasswordError: Boolean,
    hasPasswordError: Boolean,
    credentialsFieldsNotEmpty: () -> Boolean,
    onValueChangeEmail: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
    onValueChangePasswordRepeat: (String) -> Unit,
    ) {
    CustomColumn(
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        TopAuthPageDesign(
            topText = "Hvordan skal vi huske dig",
            bottomText = "Indtast din email og password",
        )

        CustomTextField(
            text = textEmail,
            label = "Email",
            onValueChange = { onValueChangeEmail(it) },
            hasError = hasEmailError,
            errorMessage = "Ikke en gyldig email"
        )

        CustomPasswordTextField(
            text = password,
            label = "Password" ,
            onValueChange = { onValueChangePassword(it) },
            hasError = hasPasswordError,
            errorMessage = "Password skal indeholde: mindst seks tegn, stort bogstav og et tal"
        )

        CustomPasswordTextField(
            text = repeatPassword,
            label = "Gentag Password",
            onValueChange =  { onValueChangePasswordRepeat(it) },
            hasError = hasRepeatedPasswordError,
            errorMessage = "Passwords skal være ens"
        )

        CustomFilledButton(
            text = "Forsæt",
            onClick = { onNavigateUsername() },
            isEnabled = credentialsFieldsNotEmpty(),
        )
    }

}