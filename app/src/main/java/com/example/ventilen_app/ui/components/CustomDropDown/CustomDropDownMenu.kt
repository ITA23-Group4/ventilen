package com.example.ventilen_app.ui.components.CustomDropDown

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.data.models.Location

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropDownMenu(
    selectedValue: String,
    options: List<Location>,
    label: String,
    onValueChangedEvent: (Location) -> Unit,
    modifier: Modifier = Modifier
) {
    val customDropDownMenuViewModel: CustomDropDownMenuViewModel = remember { CustomDropDownMenuViewModel() }

    // Found here: https://medium.com/@german220291/building-a-custom-exposed-dropdown-menu-with-jetpack-compose-d65232535bf2
    ExposedDropdownMenuBox(
        expanded = customDropDownMenuViewModel.isExpanded,
        onExpandedChange = { customDropDownMenuViewModel.isExpanded = !customDropDownMenuViewModel.isExpanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedValue,
            onValueChange = {},
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = customDropDownMenuViewModel.isExpanded)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = customDropDownMenuViewModel.isExpanded,
            onDismissRequest = { customDropDownMenuViewModel.isExpanded = false }
        ) {
            options.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurface
                    ) },
                    onClick = {
                        customDropDownMenuViewModel.isExpanded = false
                        onValueChangedEvent(it)
                    }
                )
            }
        }
    }
}