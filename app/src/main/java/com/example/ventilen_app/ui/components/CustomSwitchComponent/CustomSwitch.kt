package com.example.ventilen_app.ui.components.CustomSwitchComponent

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun CustomSwitch() {
    val customSwitchViewModel: CustomSwitchViewModel = remember { CustomSwitchViewModel() }

    Switch(
        checked = customSwitchViewModel.isChecked,
        onCheckedChange = { customSwitchViewModel.toggle() }
    )
}
