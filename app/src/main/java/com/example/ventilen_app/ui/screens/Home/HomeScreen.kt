package com.example.ventilen_app.ui.screens.Home

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    textUsername: String,
    textUID: String
) {
    Text(text = "Hello $textUsername with UID: $textUID", style = MaterialTheme.typography.bodyMedium)
}