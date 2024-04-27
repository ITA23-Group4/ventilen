package com.example.ventilen_app.ui.components.CustomSwitchComponent

import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.example.ventilen_app.ui.theme.CustomColorScheme

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
        },
        colors = SwitchDefaults.colors(
            uncheckedBorderColor = CustomColorScheme.LightGray,
            checkedBorderColor = CustomColorScheme.LightGray,
            checkedTrackColor = MaterialTheme.colorScheme.surface,
            checkedThumbColor = MaterialTheme.colorScheme.primary
        )
    )

}
