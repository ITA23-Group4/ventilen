package com.example.ventilen_app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomBackButton(onNavigateBack: () -> Unit) {
    TextButton(
        onClick = onNavigateBack,
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.offset(x= (-20).dp)
    ) {
        Icon(
            Icons.Rounded.KeyboardArrowLeft,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier
                .size(34.dp)
        )
    }
}

