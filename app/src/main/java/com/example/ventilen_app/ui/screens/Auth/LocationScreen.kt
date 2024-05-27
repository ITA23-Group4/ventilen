package com.example.ventilen_app.ui.screens.Auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomColumn
import com.example.ventilen_app.ui.components.CustomDropDown.CustomDropDownMenu
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.TopAuthPageDesign
import com.example.ventilen_app.ui.theme.CustomColorScheme

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
@Composable
fun LocationScreen(
    hasLocationError: Boolean,
    onNavigateHome: () -> Unit,
    // Parameters for the CustomDropDownMenu
    locations: List<String>,
    selectedLocation: String,
    onLocationValueChanged: (String) -> Unit,
    ) {
    CustomColumn(modifier = Modifier.fillMaxSize()
        .background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp))
    {
        TopAuthPageDesign(
            topText = "Hvor skal vi mødes",
            bottomText ="Vælg dit primære mødested som er tættest på dig",
        )

        CustomDropDownMenu(
            selectedValue = selectedLocation,
            options = locations,
            label = "Locations",
            onValueChangedEvent = onLocationValueChanged
        )

        CustomFilledButton(
            text = "Fortsæt",
            onClick = onNavigateHome,
            isEnabled = hasLocationError
        )
    }
}
