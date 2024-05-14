package com.example.ventilen_app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun CustomFloatingActionButton(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add")
    }
}