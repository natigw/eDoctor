package nfv.ui_kit.components.buttons.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import nfv.ui_kit.theme.ButtonLargePadding
import nfv.ui_kit.theme.ButtonMediumPadding
import nfv.ui_kit.theme.ButtonSmallPadding
import nfv.ui_kit.theme.EDoctorTypography

enum class ButtonTypes(
    val buttonPadding: PaddingValues,
    val iconSize: Dp,
    val itemSpacing: Dp,
    val textTypography: TextStyle,
    val circularProgressSize: Dp,
    val circularProgressStrokeWidth: Dp
) {
    LARGE(
        buttonPadding = ButtonLargePadding,
        iconSize = 24.dp,
        itemSpacing = 16.dp,
        textTypography = EDoctorTypography.titleSmall,
        circularProgressSize = 28.5.dp,
        circularProgressStrokeWidth = 3.dp
    ),
    MEDIUM(
        buttonPadding = ButtonMediumPadding,
        iconSize = 20.dp,
        itemSpacing = 12.dp,
        textTypography = EDoctorTypography.bodyMedium,
        circularProgressSize = 21.8.dp,
        circularProgressStrokeWidth = 2.5.dp
    ),
    SMALL(
        buttonPadding = ButtonSmallPadding,
        iconSize = 18.dp,
        itemSpacing = 12.dp,
        textTypography = EDoctorTypography.labelMedium,
        circularProgressSize = 18.dp,
        circularProgressStrokeWidth = 2.dp
    )
}