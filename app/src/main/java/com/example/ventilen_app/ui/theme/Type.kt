
package com.example.ventilen_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ventilen_app.R

/**
 * @author Chrsitian, Nikolaj, Marcus
 */
val montserratFamily = FontFamily(
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_extrabold, FontWeight.ExtraBold),
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        color = CustomColorScheme.OffBlack
    ),
    headlineMedium = TextStyle(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = CustomColorScheme.OffBlack,
    ),
    headlineSmall = TextStyle(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = CustomColorScheme.OffBlack
    ),
    labelLarge = TextStyle(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = CustomColorScheme.OffBlack
    ),
    labelMedium = TextStyle(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = CustomColorScheme.OffBlack
    ),
    bodyLarge = TextStyle(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = CustomColorScheme.OffBlack
    ),
    bodyMedium = TextStyle(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = CustomColorScheme.OffBlack
    ),
    bodySmall = TextStyle(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        color = CustomColorScheme.OffBlack
    ),
    displayLarge = TextStyle(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = CustomColorScheme.OffBlack
    ),
    displayMedium = TextStyle(
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        color = CustomColorScheme.OffBlack
    )

)

