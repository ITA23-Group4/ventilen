package com.example.ventilen_app.ui.screens.Location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ventilen_app.ui.components.CustomFilledButton
import com.example.ventilen_app.ui.components.CustomDropDown.CustomDropDownMenu
import com.example.ventilen_app.ui.components.TopAuthPageDesign
import com.example.ventilen_app.ui.theme.CustomColorScheme


@Composable
fun LocationScreen(
    onNavigateBack: () -> Unit,
    onNavigateHome: () -> Unit,
    // Parameters for the CustomDropDownMenu
    locations: List<String>,
    selectedLocation: String,
    onLocationValueChanged: (String) -> Unit,
    ) {
    Column(modifier = Modifier.fillMaxSize()
        .background(CustomColorScheme.Mocha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)) {
        TopAuthPageDesign(
            hasBackButton = true,
            topText = "Hvor skal vi mødes",
            bottomText ="Vælg det mødested som er tættest på dig",
            onNavigateBack = onNavigateBack
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
            padding = PaddingValues(horizontal = 25.dp, vertical = 0.dp)
        )
    }
}
