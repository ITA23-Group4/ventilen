package com.example.ventilen_app.ui.components.CustomSwitchComponent

import android.util.Log
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun CustomSwitch(
    onNotAttend: () -> Unit,
    onAttend: () -> Unit
) {
    val customSwitchViewModel: CustomSwitchViewModel = remember { CustomSwitchViewModel() }

    Switch(
        checked = customSwitchViewModel.isChecked,
        onCheckedChange = {
            if (customSwitchViewModel.isChecked) {
                onNotAttend()
            } else {
                onAttend()
            }
            customSwitchViewModel.toggle()
        }
    )

}
