@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ventilen_app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.ventilen_app.ui.theme.CustomColorScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    text: String,
    label: String = "",
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    isSingleLine: Boolean = true,
    hasError: Boolean = false,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        singleLine = isSingleLine,
        onValueChange = { onValueChange(it)},
        //visualTransformation = PasswordVisualTransformation(),
        label = { Text(text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = CustomColorScheme.Orange
        )},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.primary
        ),
        textStyle = MaterialTheme.typography.bodyMedium,
        isError = hasError
    )
}