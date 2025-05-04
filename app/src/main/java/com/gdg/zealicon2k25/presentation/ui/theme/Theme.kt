package com.gdg.zealicon2k25.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


val ButtonBackgroundColor = Color(0xFFF5387C)
val ButtonBorderColor = Color(0xFF942724)
val ButtonRippleColor = Color.White
val BackgroundColor = Color(0xFFFFF1CC)
val ButtonBackgroundColor2 = Color(0xFFFFF1CC)
val ButtonBorderColor2 = Color(0xFF4DF5387C)
val HeadingTextColor = Color(0xFF942724)
val TextFieldBackgroundColor = Color.White
val TextFieldBorderColor = Color(0xFFFFE1E0)
val TextFieldPlaceholderColor = Color(0xFFB2942724)
val PhotoBorderColor = Color(0xFFF1C364)
val BorderRed = Color(0xFF942724)
val BorderDark = Color(0xFF942724)
val BorderLight = Color(0xFF942724)
val cardBackground=Color(0xFFFFE7A7)
val cardBorderColor=Color(0xFFFEDF8C)
val cardTextColor=Color(0xFF942724)
val SuccessTextColor = Color(0xFF1E4E1E)
val ErrorTextColor = Color(0xFFAC0000)

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun Zealicon2K25Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
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