package com.example.ventilen_app.ui.composables

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable

@Composable
fun CustomTextField(
    text:String,
    onValueChange: (String) -> Unit,

) {
    TextField(value = text, onValueChange = {onValueChange(it)})
}