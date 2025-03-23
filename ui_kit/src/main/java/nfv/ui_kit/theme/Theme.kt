package nfv.ui_kit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

object EDoctorColorScheme {
    val Light = lightColorScheme(
        primary = Primary500,
        secondary = Warning500,
        tertiary = Success500,
        onPrimary = Gray50,
        onSecondary = Gray50,
        onTertiary = Gray50,
        background = BaseWhite,
        onBackground = BaseBlack,
        surface = BaseWhite,                //top bar, bottom bar
        onSurface = Gray400,                //bottom bar unselected content
        surfaceContainerLowest = Gray50,    //light gray icons
        surfaceContainerLow = Gray100,      //top bar, bottom bar divider
        surfaceContainer = Gray300,         //light gray icon border
        surfaceContainerHigh = Gray500,     //gray
        surfaceContainerHighest = Typography700, //gray texts
        surfaceVariant = Primary100,        //large icon button border
        //inverseOnSurface
        inversePrimary = Primary900         //dark blue texts
    )

    val Dark = darkColorScheme(
        primary = Primary500,
        secondary = Warning500,
        tertiary = Success500,
        onPrimary = Gray50,
        onSecondary = Gray50,
        onTertiary = Gray50,
        background = BaseBlack,
        surface = BaseBlack,                //top bar, bottom bar
        onSurface = Gray500,                //bottom bar unselected content
        surfaceContainerLowest = Gray900,   //light gray icons
        surfaceContainerLow = Gray800,      //top bar, bottom bar divider
        surfaceContainer = Gray700,         //light gray icon border
        surfaceContainerHigh = Gray500,     //gray
        surfaceContainerHighest = Typography400, //gray texts
        surfaceVariant = Primary800,        //large icon button border
        //inverseOnSurface
        inversePrimary = Primary500,        //dark blue texts
    )
}

@Composable
fun EDoctorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
        darkTheme -> EDoctorColorScheme.Dark
        else -> EDoctorColorScheme.Light
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = EDoctorTypography,
        content = content
    )
}
