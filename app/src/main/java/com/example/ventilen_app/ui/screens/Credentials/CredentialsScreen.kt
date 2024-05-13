package com.example.ventilen_app.ui.screens.Credentials

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ventilen_app.ui.components.ConditionsRow
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomTextField
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.TopAuthPageDesign
import com.example.ventilen_app.ui.theme.CustomColorScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CredentialsScreen(
    viewModel: CredentialsScreenVievModel = viewModel(),
    onNavigateUsername: () -> Unit,
    textEmail: String,
    textPassword: String,
    onValueChangeEmail: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
    onValueChangePasswordRepeat: (String) -> Unit
) {
    val passwordError by viewModel.passwordError.collectAsState()

    CustomColumn(
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        TopAuthPageDesign(
            topText = "Hvordan skal vi huske dig",
            bottomText = "indtast din email og password",
        )
        CustomTextField(text = textEmail, label = "Email") { onValueChangeEmail(it) }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.password,
            onValueChange = viewModel::changePassword,
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary
            )
            )



        CustomTextField(text = textPassword, label = "Gentag Password") { onValueChangePasswordRepeat(it) }

        Column(modifier = Modifier.padding(0.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)) {
            ConditionsRow(condition = "Minimun 6 characters" , check = passwordError.hasMinimun )
            ConditionsRow(condition = "Has capitalized letter" , check = passwordError.hasCapitalizedLetter )
            ConditionsRow(condition = "Has a number" , check = passwordError.hasNumber )
        }

        CustomFilledButton(
            text = "Forsæt",
            onClick = onNavigateUsername
        )
    }

}