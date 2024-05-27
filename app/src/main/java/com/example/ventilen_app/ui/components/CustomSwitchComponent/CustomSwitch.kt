package com.example.ventilen_app.ui.components.CustomSwitchComponent

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import com.example.ventilen_app.ui.theme.CustomColorScheme

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
@Composable
fun CustomSwitch(
    isAttending: Boolean,
    onNotAttend: () -> Unit,
    onAttend: () -> Unit
) {
    Switch(
        checked = isAttending,
        onCheckedChange = {
            if (isAttending) {
                onNotAttend()
            } else {
                onAttend()
            }
        },
        colors = SwitchDefaults.colors(
            uncheckedBorderColor = CustomColorScheme.LightGray,
            checkedBorderColor = CustomColorScheme.LightGray,
            checkedTrackColor = MaterialTheme.colorScheme.surface,
            checkedThumbColor = MaterialTheme.colorScheme.primary
        )
    )

}
