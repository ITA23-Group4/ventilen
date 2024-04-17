package com.example.ventilen_app.ui.screens.Welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.ventilen_app.ui.composables.FilledButton

@Composable
fun WelcomeScreen(
    onClick: () -> Unit
) {
    Text(text = "Hello")

    Column {
        FilledButton(text = "Login", onClick = onClick, color = Color.Red)
        FilledButton(text = "Tilmeld", onClick = onClick, color = Color.Red)
    }
}