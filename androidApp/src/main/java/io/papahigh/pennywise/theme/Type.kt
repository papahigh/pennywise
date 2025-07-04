package io.papahigh.pennywise.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import io.papahigh.pennywise.R

@Composable
fun displayFontFamily() =
    FontFamily(
        Font(R.font.libre_baskerville_regular, Normal),
        Font(R.font.libre_baskerville_italic, Normal, FontStyle.Italic),
        Font(R.font.libre_baskerville_bold, FontWeight.Bold),
    )

@Composable
fun pennywiseTypography() =
    Typography().run {
        val displayFontFamily = displayFontFamily()
        copy(
            displayLarge = displayLarge.copy(fontFamily = displayFontFamily),
            displayMedium = displayMedium.copy(fontFamily = displayFontFamily),
            displaySmall = displaySmall.copy(fontFamily = displayFontFamily),
            headlineLarge = headlineLarge.copy(fontFamily = displayFontFamily),
            headlineMedium = headlineMedium.copy(fontFamily = displayFontFamily),
            headlineSmall = headlineSmall.copy(fontFamily = displayFontFamily),
            titleLarge = titleLarge.copy(fontFamily = displayFontFamily),
            titleMedium = titleMedium.copy(fontFamily = displayFontFamily),
            titleSmall = titleSmall.copy(fontFamily = displayFontFamily),
            bodyLarge = bodyLarge.copy(),
            bodyMedium = bodyMedium.copy(),
            bodySmall = bodySmall.copy(),
            labelLarge = labelLarge.copy(),
            labelMedium = labelMedium.copy(),
            labelSmall = labelSmall.copy(),
        )
    }
