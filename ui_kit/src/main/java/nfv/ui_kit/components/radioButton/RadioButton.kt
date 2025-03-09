package nfv.ui_kit.components.radioButton

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import nfv.ui_kit.theme.Gray100
import nfv.ui_kit.theme.Gray300
import nfv.ui_kit.theme.Gray50
import nfv.ui_kit.theme.Gray500
import nfv.ui_kit.theme.Primary300
import nfv.ui_kit.theme.Primary50
import nfv.ui_kit.theme.Primary500

@Composable
fun RadioButton(
    modifier: Modifier = Modifier,
    isDisabled: Boolean = false,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val color by animateColorAsState(
        targetValue = when {
            isDisabled && isSelected.not() -> Gray300
            isDisabled && isSelected -> Gray300
            isDisabled.not() && isSelected.not() -> Gray300
            isDisabled.not() && isSelected -> Primary500
            else -> Color.Unspecified
        }
    )
    val backgroundColor by animateColorAsState(
        targetValue = when {
            isDisabled && isSelected.not() -> Gray100
            isDisabled && isSelected -> Gray100
            isDisabled.not() && isSelected.not() -> Gray50
            isDisabled.not() && isSelected -> Primary50
            else -> Color.Unspecified
        }
    )
    val rippleColor by animateColorAsState(
        targetValue = if (isSelected) Primary300 else Gray500
    )
    val innerCircleSize by animateDpAsState(
        targetValue = if (isSelected) 10.dp else 0.dp
    )
    val strokeSize by animateDpAsState(
        targetValue = if (isSelected) 1.5.dp else 1.dp
    )

    Box(
        modifier = modifier
            .size(20.dp)
            .clip(CircleShape)
            .border(
                width = strokeSize,
                color = color,
                shape = CircleShape
            )
            .background(backgroundColor)
            .clickable(
                enabled = isDisabled.not(),
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = rippleColor),
                onClick = onClick
            )
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(5.dp)
                .size(innerCircleSize)
                .clip(CircleShape)
                .background(color)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun RadioButtonPrev() {

    var isDisabledState by remember { mutableStateOf(true) }
    var isSelectedState by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            isDisabledState = !isDisabledState
            delay(1000)
            isSelectedState = !isSelectedState
        }
    }

    RadioButton(
        isDisabled = isDisabledState,
        isSelected = isSelectedState,
        onClick = {
            isSelectedState = !isSelectedState
        }
    )
}