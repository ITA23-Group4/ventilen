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
    onNavigateEvent: () -> Unit,
    // TODO: Remove
    logout: () -> Unit,
    getCurrentUser: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)) {

        Text(
            text = stringResource(R.string.home_welcome_title, textUsername),
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = stringResource(R.string.home_welcome_uid, textUID),
            style = MaterialTheme.typography.headlineMedium
        )

        CustomFilledButton(text = "Go to Event", onClick =  onNavigateEvent )

        // TODO: Remove
        CustomFilledButton(text = "Logout", onClick = logout)
        CustomFilledButton(text = "Current User", onClick = getCurrentUser)
    }
}