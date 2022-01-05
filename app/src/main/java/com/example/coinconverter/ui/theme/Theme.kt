package com.example.coinconverter.ui.theme

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.example.coinconverter.ui.theme.att.*

private val DarkColorPalette = darkColors(
    primary = White,
    background = DarkGray,
    onBackground = Pink,
    surface = Pink,
    onSurface = White
)

private val LightColorPalette = lightColors(
    primary = DarkGray,
    background = White,
    onBackground = Purple,
    surface = Purple,
    onSurface = White
)

@Composable
fun CoinConverterAppTheme(
    darkTheme: Boolean,
    content: @Composable() () -> Unit
) {
    MaterialTheme (
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}