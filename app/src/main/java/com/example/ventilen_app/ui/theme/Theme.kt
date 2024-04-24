
package com.example.ventilen_app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val myLightColorScheme = lightColorScheme(
    primary = CustomColorScheme.Orange,
    secondary = CustomColorScheme.Mocha,
    tertiary = CustomColorScheme.Beige,
    background = CustomColorScheme.Mocha,
    surface = CustomColorScheme.OffWhite,
    onBackground = CustomColorScheme.OffBlack,
    onSurface = CustomColorScheme.OffBlack,
    error = Color.Red, // Placeholder color for error messages
    onError = CustomColorScheme.OffBlack,
    surfaceVariant = CustomColorScheme.OffWhite,
)

private val myDarkColorScheme = darkColorScheme(
    primary = CustomColorScheme.Orange,
    secondary = CustomColorScheme.Mocha,
    tertiary = CustomColorScheme.Beige,
    background = CustomColorScheme.Mocha,
    surface = CustomColorScheme.OffWhite,
    onBackground = CustomColorScheme.OffBlack,
    onSurface = CustomColorScheme.OffBlack,
    error = Color.Red, // Placeholder color for error messages
    onError = CustomColorScheme.OffBlack,
    surfaceVariant = CustomColorScheme.OffWhite
)

@Composable
fun VentilenAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) myDarkColorScheme else myLightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
