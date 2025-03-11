package nfv.ui_kit.components.buttons.keypad

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.theme.Gray50
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Typography50
import nfv.ui_kit.theme.Typography900
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun IconKeypadButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    contentDescription: String?,
    onClick: () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    var isPressed by remember { mutableStateOf(false) }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> isPressed = true
                is PressInteraction.Release, is PressInteraction.Cancel -> isPressed = false
            }
        }
    }

    val backgroundColor by animateColorAsState(
        targetValue = if (isPressed) Primary500 else Gray50
    )
    val contentColor by animateColorAsState(
        targetValue = if (isPressed) Typography50 else Typography900
    )

    Box(
        modifier = modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .size(32.dp),
            imageVector = ImageVector.vectorResource(iconRes),
            contentDescription = contentDescription,
            tint = contentColor
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun IconKeypadButtonPrev() {
    IconKeypadButton(
        iconRes = drawableR.ic_delete,
        contentDescription = null,
        onClick = { }
    )
}