package com.example.ventilen_app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
@Composable
fun CustomFilledButton(
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    color: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.surface
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(4.dp),
        enabled = isEnabled
    ){
        Text(text = text, style = MaterialTheme.typography.labelLarge, color = textColor)
    }
}