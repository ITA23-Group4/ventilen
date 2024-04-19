package com.example.ventilen_app.ui.composables

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable

@Composable
fun CustomTextField(
    text:String,
    onValueChange: (String) -> Unit,

) {
    OutlinedTextField(value = text, onValueChange = {onValueChange(it)})
}