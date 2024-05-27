package com.example.ventilen_app.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * CustomBackButton is a button component representing a back navigation arrow typically
 * used in navigation bars or toolbars to navigate to the previous screen.
 *
 * @param onNavigateBack Callback function invoked when the back button is clicked.
 * @author Christian, Nikolaj, Marcus
 */
@Composable
fun CustomBackButton(
    onNavigateBack: () -> Unit
) {
    IconButton(
        onClick = onNavigateBack
    ) {
        Icon(
            imageVector = Icons.Rounded.KeyboardArrowLeft,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(34.dp)
        )
    }
}

