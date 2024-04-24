package com.example.ventilen_app.ui.screens.Home

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.ventilen_app.R

@Composable
fun HomeScreen(
    textUsername: String,
    textUID: String
) {
    // Text(text = "Hello $textUsername with UID: $textUID", style = MaterialTheme.typography.bodyMedium)
    Text(
        text = stringResource(R.string.home_welcome_title, textUsername, textUID),
        style = MaterialTheme.typography.bodyMedium
    )
}