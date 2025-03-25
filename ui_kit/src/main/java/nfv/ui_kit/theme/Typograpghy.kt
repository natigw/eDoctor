package nfv.ui_kit.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private const val fontUnit = 1.15     // 1px font unit

val EDoctorTypography = Typography(

    // For different variations: EDoctorTypography.titleLarge.copy(fontWeight = FontWeight.Bold, color = ..., ...)

    headlineMedium = TextStyle(
        fontFamily = ManropeFontFamily,
        fontWeight = FontWeight.Medium,        // Bold
        fontSize = (fontUnit * 28).sp,         // 28px font size
        lineHeight = (fontUnit * 40).sp,       // 40
        letterSpacing = (fontUnit * 0.4).sp    // 0.4px
    ),
    titleLarge = TextStyle(
        fontFamily = ManropeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = (fontUnit * 24).sp,
        lineHeight = (fontUnit * 36).sp,
        letterSpacing = (fontUnit * 0.2).sp
    ),
    titleMedium = TextStyle(
        fontFamily = ManropeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = (fontUnit * 20).sp,
        lineHeight = (fontUnit * 32).sp,
        letterSpacing = (fontUnit * 0.2).sp
    ),
    titleSmall = TextStyle(
        fontFamily = ManropeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = (fontUnit * 18).sp,
        lineHeight = (fontUnit * 30).sp,
        letterSpacing = (fontUnit * 0.2).sp
    ),
    bodyLarge = TextStyle(
        fontFamily = ManropeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = (fontUnit * 16).sp,
        lineHeight = (fontUnit * 28).sp,
        letterSpacing = (fontUnit * 0.2).sp
    ),
    bodyMedium = TextStyle(
        fontFamily = ManropeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = (fontUnit * 14).sp,
        lineHeight = (fontUnit * 24).sp,
        letterSpacing = (fontUnit * 0.2).sp
    ),
    labelMedium = TextStyle(
        fontFamily = ManropeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = (fontUnit * 12).sp,
        lineHeight = (fontUnit * 20).sp,
        letterSpacing = (fontUnit * 0.2).sp
    ),
    labelSmall = TextStyle(
        fontFamily = ManropeFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = (fontUnit * 11).sp,
        lineHeight = (fontUnit * 16).sp,
        letterSpacing = (fontUnit * 0.2).sp
    )
)
