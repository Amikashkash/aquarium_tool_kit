package il.co.amikashkash.aquariumtoolkit.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val DarkColorScheme = darkColorScheme(
    primary = green,
    onPrimary = beige,
    secondary = green_pastel,
    tertiary = beige,
    background = dark_gray, // Dark background
    onBackground = Color.White, // Light text on dark background
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White,
    primaryContainer = blue_gray,
    onPrimaryContainer = Color.White,
    secondaryContainer = blue_gray,
    onSecondaryContainer = Color.White,
    tertiaryContainer = blue_gray,
    onTertiaryContainer = Color.White,
    errorContainer = blue_gray,
    onErrorContainer = Color.White,
    outline = blue_gray,
    outlineVariant = blue_gray,
    scrim = blue_gray,
    inverseSurface = blue_gray,
    inverseOnSurface = Color.White,
    inversePrimary = blue_gray,
    error = Color.Red,
    onError = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = blue_green,
    onPrimary = beige,
    secondary = green,
    tertiary = green_pastel,
    background = beige, // Light background
    onBackground = Color.Black, // Dark text on light background
    surface = Color.White,
    onSurface = Color.Black,
    primaryContainer = blue_green,
    onPrimaryContainer = Color.Black,
    secondaryContainer = blue_green,
    onSecondaryContainer = Color.Black,
    tertiaryContainer = blue_gray,
    onTertiaryContainer = Color.Black,
    errorContainer = blue_gray,
    onErrorContainer = Color.Black,
    outline = blue_gray,
    outlineVariant = blue_gray,
    scrim = blue_gray,
    inverseSurface = blue_gray,
    inverseOnSurface = Color.Black,
    inversePrimary = blue_gray,
    error = Color.Red,
    onError = Color.White
)


@Composable
fun AquariumToolKitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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
        content = content
    )
}