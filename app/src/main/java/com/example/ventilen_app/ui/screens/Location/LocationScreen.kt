package com.example.ventilen_app.ui.screens.Location

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.ventilen_app.AuthViewModel
import com.example.ventilen_app.ui.composables.CustomFilledButton
import com.example.ventilen_app.ui.composables.CustomDropDownMenu


@Composable
fun LocationScreen(
    onNavigateBack: () -> Unit,
    onNavigateHome: () -> Unit,

    ) {

    Column {
        Text(text = "asdasdasd")

        var expanded by remember { mutableStateOf(false) }
        val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8")
        var selectedValue = "Hej"

        CustomDropDownMenu(selectedValue = selectedValue , options = items, label = "Locations", onValueChangedEvent = {selectedValue = it})

        CustomFilledButton(text = "Fortsæt", onClick = onNavigateHome)
    }
}
