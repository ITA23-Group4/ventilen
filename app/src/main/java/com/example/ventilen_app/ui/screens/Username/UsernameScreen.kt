package com.example.ventilen_app.ui.screens.Username

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.ventilen_app.ui.composables.CustomOutlinedButton
import com.example.ventilen_app.ui.composables.CustomTextField
import com.example.ventilen_app.ui.composables.FilledButton

@Composable
fun UsernameScreen(
    onClick: () -> Unit,
    text:String,
    onValueChange: (String) -> Unit,

    ) {
    Text(text = "Hello")

    Column {
        CustomTextField(text = text, onValueChange = {onValueChange(it)} )
        FilledButton(text = "Fortsæt", onClick = onClick, color = Color.Red)
        CustomOutlinedButton(text = "Back", onClick = { /*TODO*/ }, borderColor = MaterialTheme.colorScheme.primary, textColor = MaterialTheme.colorScheme.primary )
        }
}