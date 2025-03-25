package nfv.ui_kit.components.buttons.icon.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class IconButtonTypes(
    val iconSize: Dp,
    val iconPadding: PaddingValues,
    val circularProgressStrokeWidth: Dp
) {
    LARGE(
        iconSize = 24.dp,
        iconPadding = PaddingValues(12.dp),
        circularProgressStrokeWidth = 2.5.dp
    ),
    MEDIUM(
        iconSize = 24.dp,
        iconPadding = PaddingValues(8.dp),
        circularProgressStrokeWidth = 2.5.dp
    ),
    SMALL(
        iconSize = 20.dp,
        iconPadding = PaddingValues(6.dp),
        circularProgressStrokeWidth = 2.dp
    )
}