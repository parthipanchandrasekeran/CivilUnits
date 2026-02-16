package com.civilunits.canada.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = Teal700,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    primaryContainer = Teal200,
    secondary = Orange700,
    onSecondary = androidx.compose.ui.graphics.Color.White,
    secondaryContainer = Orange200,
    surface = androidx.compose.ui.graphics.Color.White,
    background = androidx.compose.ui.graphics.Color(0xFFF5F5F5)
)

private val DarkColorScheme = darkColorScheme(
    primary = Teal200,
    onPrimary = androidx.compose.ui.graphics.Color.Black,
    primaryContainer = Teal700,
    secondary = Orange200,
    onSecondary = androidx.compose.ui.graphics.Color.Black,
    secondaryContainer = Orange700,
    surface = DarkSurface,
    background = DarkBackground
)

@Composable
fun CivilUnitsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
