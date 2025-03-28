package nfv.ui_kit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

object EDoctorColorScheme {
    val Light = lightColorScheme(
        outline = Typography900,            //main texts
        outlineVariant = Typography700,     //gray texts
        onPrimary = Primary700,             //blue texts
        inversePrimary = Primary900,        //dark blue texts
        primary = Primary500,
        secondary = Warning500,
        secondaryContainer = Danger500,
        tertiary = Success500,
        onSecondary = Primary50,
        onTertiary = Typography50,
        background = BaseWhite,
        onBackground = BaseBlack,
        surface = BaseWhite,                //top bar, bottom bar
        onSurface = Gray400,                //bottom bar unselected content; credential dot stroke
        surfaceBright = Info50,             //dialog background
        surfaceContainerLowest = Gray50,    //light gray icons
        surfaceContainerLow = Gray100,      //top bar, bottom bar divider
        surfaceContainer = Gray300,         //light gray icon border
        surfaceContainerHigh = Gray500,     //gray
        surfaceContainerHighest = Typography700, //gray texts
        surfaceVariant = Primary100,        //large icon button border
    )

    val Dark = darkColorScheme(
        outline = Typography50,             //main texts
        outlineVariant = Typography200,     //gray texts
        onPrimary = Info100,                //blue texts
        inversePrimary = Primary100,        //dark blue texts
        primary = Primary500,
        secondary = Warning500,
        secondaryContainer = Danger500,
        tertiary = Success500,
        onSecondary = Gray50,
        onTertiary = Gray50,
        background = BaseBlack,
        onBackground = BaseWhite,
        surface = BaseBlack,                //top bar, bottom bar
        onSurface = Gray500,                //bottom bar unselected content
        surfaceBright = Info900,            //dialog background
        surfaceContainerLowest = Gray900,   //light gray icons
        surfaceContainerLow = Gray800,      //top bar, bottom bar divider
        surfaceContainer = Gray700,         //light gray icon border
        surfaceContainerHigh = Gray500,     //gray
        surfaceContainerHighest = Typography400, //gray texts
        surfaceVariant = Primary800,        //large icon button border
    )
}

@Composable
fun EDoctorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme =
        if (darkTheme)
            EDoctorColorScheme.Dark
        else
            EDoctorColorScheme.Light

    MaterialTheme(
        colorScheme = colorScheme,
        typography = EDoctorTypography,
        content = content
    )
}
