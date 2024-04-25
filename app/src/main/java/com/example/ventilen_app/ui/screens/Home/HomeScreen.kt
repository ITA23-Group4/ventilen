package com.example.ventilen_app.ui.screens.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.R
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.theme.CustomColorScheme

@Composable
fun HomeScreen(
    textUsername: String,
    textUID: String,
    onNavigateEvent: () -> Unit
) {
    // Text(text = "Hello $textUsername with UID: $textUID", style = MaterialTheme.typography.bodyMedium)
    Column(modifier = Modifier.fillMaxSize()
        .background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)) {
        Text(
            text = stringResource(R.string.home_welcome_title, textUsername, textUID),
            style = MaterialTheme.typography.bodyMedium
        )
        CustomFilledButton(text = "Go to Event", onClick =  onNavigateEvent )
    }

}