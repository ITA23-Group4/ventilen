@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ventilen_app.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.theme.CustomColorScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    text: String,
    label: String = "",
    isSingleLine: Boolean = true,
    hasError: Boolean = false,
    isIntegerOnly: Boolean = false,
    errorIndicator: String = "*",
    errorMessage: String = "",
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        singleLine = isSingleLine,
        onValueChange = { newText ->
            if (!isIntegerOnly || newText.all { it.isDigit() }) {
                onValueChange(newText)
            }
        },        label = { Text(text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = CustomColorScheme.Orange
        )},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.primary
        ),
        textStyle = MaterialTheme.typography.bodyMedium,
        isError = hasError,
        supportingText = {
            if (hasError) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = (-11).dp),
                    verticalAlignment = Alignment.Top
                ){
                    Text(
                        text = errorIndicator,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = errorMessage,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    )
}