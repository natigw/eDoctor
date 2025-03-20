package nfv.ui_kit.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext

object EDoctorColorScheme {
    val Light = lightColorScheme(
        primary = Primary500,
        secondary = Warning500,
        tertiary = Success500,
        onPrimary = Gray50,
        onSecondary = Gray50,
        onTertiary = Gray50,
        background = BaseWhite,
        surface = BaseWhite, // Ensures consistent surface colors
        onBackground = BaseBlack,
        onSurface = BaseBlack
    )

    val Dark = darkColorScheme(
        primary = Primary500,
        secondary = Warning500,
        tertiary = Success500,
        onPrimary = Gray50,
        onSecondary = Gray50,
        onTertiary = Gray50,
        background = BaseBlack,
        surface = BaseBlack,
        onBackground = BaseWhite,
        onSurface = BaseWhite
    )
}

@Composable
fun EDoctorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> EDoctorColorScheme.Dark
        else -> EDoctorColorScheme.Light
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = EDoctorTypography,
        content = content
    )
}

object EDoctorTheme {
    val colors: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme
}
