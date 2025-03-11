package nfv.ui_kit.components.buttons.keypad

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray50
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Typography50
import nfv.ui_kit.theme.Typography900

@Composable
fun NumberKeypadButton(
    modifier: Modifier = Modifier,
    number: Int,
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
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = number.toString(),
            style = EDoctorTypography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun NumberKeypadButtonPrev() {
    NumberKeypadButton(
        number = 5,
        onClick = { }
    )
}