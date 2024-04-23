package com.example.ventilen_app.ui.screens.Username

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.composables.CustomTextField
import com.example.ventilen_app.ui.composables.CustomFilledButton
import com.example.ventilen_app.ui.composables.TopAuthPageDesign
import com.example.ventilen_app.ui.theme.CustomColorScheme

@Composable
fun UsernameScreen(
    onNavigateBack: () -> Unit,
    onNavigateLocation: () -> Unit,
    textUsername: String,
    onValueChange: (String) -> Unit,

    ) {
    Column(
        Modifier.background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally){
        TopAuthPageDesign(
            hasBackButton = true,
            topText = "Hvad skal vi kalde dig",
            bottomText = "Indtast dit anonyme brugernavn" )
        CustomTextField(text = textUsername, onValueChange = {onValueChange(it)} )
        Spacer(modifier = Modifier.height(30.dp))
        CustomFilledButton(text = "Fortsæt", onClick = onNavigateLocation, padding = PaddingValues(horizontal = 25.dp, vertical = 0.dp))
        }
}