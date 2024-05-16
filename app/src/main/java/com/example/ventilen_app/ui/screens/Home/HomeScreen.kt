package com.example.ventilen_app.ui.screens.Home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import java.util.Date
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.R
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.theme.CustomColorScheme

@Composable
fun HomeScreen(
    textUsername: String,
    textUID: String,
    isAdmin: Boolean,
    selectedDate: Date?,
    selectedTime: Date?,
    showDatePicker: () -> Unit, // TODO: Dialog should not be made in ViewModel?
    showTimePicker: () -> Unit,  // TODO: Dialog should not be made in ViewModel?
    logout: () -> Unit,
) {
    CustomColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp))
    {
        Text(
            text = stringResource(R.string.home_welcome_title, textUsername),
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = stringResource(R.string.home_welcome_uid, textUID),
            style = MaterialTheme.typography.headlineMedium
        )

        // Conditionally show the logout button for admins
        if (isAdmin) {
            CustomFilledButton(
                text = "Admin Logout",
                onClick = logout
            )
        }

        Button(onClick = { showDatePicker() }) {
            Text("Select Date")
        }

        Button(onClick = { showTimePicker() }) {
            Text("Select Time")
        }

        Text("Selected Date: ${selectedDate?.toString() ?: "None"}", color = Color.Black)
        Text("Selected Time: ${selectedTime?.toString() ?: "None"}", color = Color.Black)

    }
}