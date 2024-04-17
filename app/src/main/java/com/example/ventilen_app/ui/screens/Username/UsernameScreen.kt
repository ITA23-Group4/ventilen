package com.example.ventilen_app.ui.screens.Username

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.ventilen_app.ui.composables.FilledButton

@Composable
fun UsernameScreen(
    onClick: () -> Unit
) {
    Text(text = "Hello")

    Column {

        FilledButton(text = "Fortsæt", onClick = onClick, color = Color.Red)
    }
}