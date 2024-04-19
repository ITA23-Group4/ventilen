package com.example.ventilen_app.ui.screens.Username

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.ventilen_app.ui.composables.CustomTextField
import com.example.ventilen_app.ui.composables.CustomFilledButton

@Composable
fun UsernameScreen(
    onNavigateBack: () -> Unit,
    onNavigateLocation: () -> Unit,
    text:String,
    onValueChange: (String) -> Unit,

    ) {
    Text(text = "Hello")

    Column {
        CustomTextField(text = text, onValueChange = {onValueChange(it)} )
        CustomFilledButton(text = "Fortsæt", onClick = onNavigateLocation)
        }
}