package nfv.ui_kit.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nfv.ui_kit.R

val ManropeFontFamily = FontFamily(
    //Font(resId = R.font., weight = FontWeight.Black),
    Font(resId = R.font.manrope_extra_bold, weight = FontWeight.ExtraBold),
    Font(resId = R.font.manrope_bold, weight = FontWeight.Bold),
    Font(resId = R.font.manrope_semibold, weight = FontWeight.SemiBold),
    Font(resId = R.font.manrope_medium, weight = FontWeight.Medium),
    Font(resId = R.font.manrope_regular, weight = FontWeight.Normal),
    Font(resId = R.font.manrope_light, weight = FontWeight.Light),
    Font(resId = R.font.manrope_extra_light, weight = FontWeight.ExtraLight),
    //Font(resId = R.font., weight = FontWeight.Thin)
)



@Composable
fun ManropeText(
    fontWeight: FontWeight
) {
    Text(text = "Sample text goes here.", fontWeight = fontWeight, fontSize = 18.sp)
}

@Composable
fun ManropeTextTypography(
    style: TextStyle
) {
    Text(text = "Sample text goes here.\nSecond line - 2", style = style)
}

@Preview(showSystemUi = true)
@Composable
private fun ManropeFontPreview() {
    Column(Modifier.background(Color.White)) {
        ManropeText(fontWeight = FontWeight.Black)
        ManropeText(fontWeight = FontWeight.ExtraBold)
        ManropeText(fontWeight = FontWeight.Bold)
        ManropeText(fontWeight = FontWeight.SemiBold)
        ManropeText(fontWeight = FontWeight.Medium)
        ManropeText(fontWeight = FontWeight.Normal)
        ManropeText(fontWeight = FontWeight.Light)
        ManropeText(fontWeight = FontWeight.ExtraLight)
        ManropeText(fontWeight = FontWeight.Thin)

        ManropeTextTypography(style = EDoctorTypography.headlineMedium)
        ManropeTextTypography(style = EDoctorTypography.titleLarge)
        ManropeTextTypography(style = EDoctorTypography.titleMedium)
        ManropeTextTypography(style = EDoctorTypography.bodyLarge)
        ManropeTextTypography(style = EDoctorTypography.bodyMedium)
        ManropeTextTypography(style = EDoctorTypography.labelMedium)
        ManropeTextTypography(style = EDoctorTypography.labelSmall)
    }
}