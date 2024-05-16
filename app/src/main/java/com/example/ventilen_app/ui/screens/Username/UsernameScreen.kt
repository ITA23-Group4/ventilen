package com.example.ventilen_app.ui.screens.Username

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
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
fun UsernameScreen(
    onNavigateLocation: () -> Unit,
    textUsername: String,
    onValueChange: (String) -> Unit,
    hasUsernameError: Boolean
    ) {
    CustomColumn(modifier = Modifier.fillMaxSize()
        .background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ){
        TopAuthPageDesign(
            topText = "Hvad skal vi kalde dig",
            bottomText = "Indtast dit anonyme username" ,
        )
        CustomTextField(
            text = textUsername,
            label = "Username",
            onValueChange = {onValueChange(it)},
            hasError = hasUsernameError
        )
        CustomFilledButton(
            text = "Fortsæt",
            onClick = onNavigateLocation,
        )

    }

}