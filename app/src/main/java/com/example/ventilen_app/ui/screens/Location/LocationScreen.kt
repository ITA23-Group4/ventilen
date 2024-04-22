package com.example.ventilen_app.ui.screens.Location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ventilen_app.ui.composables.CustomFilledButton
import com.example.ventilen_app.ui.composables.CustomDropDownMenu
import com.example.ventilen_app.ui.composables.TopAuthPageDesign
import com.example.ventilen_app.ui.theme.CustomColorScheme


@Composable
fun LocationScreen(
    onNavigateBack: () -> Unit,
    onNavigateHome: () -> Unit,
    locations: List<String>,
    selectedLocation: String,
    onLocationValueChanged: (String) -> Unit,
    ) {
    Column(
        Modifier.background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopAuthPageDesign(
            hasBackButton = true,
            topText = "Hvor skal vi mødes",
            bottomText ="Vælg det mødested som er tættest på dig")

        var expanded by remember { mutableStateOf(false) }

        CustomDropDownMenu(selectedValue = selectedLocation , options = locations, label = "Locations", onValueChangedEvent = onLocationValueChanged)

        CustomFilledButton(text = "Fortsæt", onClick = onNavigateHome)
    }
}
