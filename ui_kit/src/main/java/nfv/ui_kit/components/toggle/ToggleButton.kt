package nfv.ui_kit.components.toggle

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import nfv.ui_kit.theme.Gray200
import nfv.ui_kit.theme.Gray300
import nfv.ui_kit.theme.Gray50
import nfv.ui_kit.theme.Gray500
import nfv.ui_kit.theme.Primary100
import nfv.ui_kit.theme.Primary300
import nfv.ui_kit.theme.Primary500

@Composable
fun ToggleButton(
    modifier: Modifier = Modifier,
    isDisabled: Boolean = false,
    isToggleOn: Boolean,
    onToggle: () -> Unit
) {

    val thumbColor by animateColorAsState(
        targetValue = when {
            isDisabled && isToggleOn.not() -> Gray200
            isDisabled && isToggleOn -> Primary100
            isDisabled.not() && isToggleOn.not() -> Gray50
            isDisabled.not() && isToggleOn -> Gray50
            else -> Color.Unspecified
        }
    )

    val backgroundColor by animateColorAsState(
        targetValue = when {
            isDisabled && isToggleOn.not() -> Gray300
            isDisabled && isToggleOn -> Primary300
            isDisabled.not() && isToggleOn.not() -> Gray300
            isDisabled.not() && isToggleOn -> Primary500
            else -> Color.Unspecified
        }
    )

    val rippleColor by animateColorAsState(
        targetValue = if (isToggleOn) Primary300 else Gray500
    )


//    val thumbSize by animateDpAsState(
//        targetValue = if (isToggleOn) 26.dp else 24.dp
//    )
//
//    val alignment by animateDpAsState(
//        targetValue = if (isToggleOn) 27.dp else 4.dp
//    )

    var isPressed by remember { mutableStateOf(false) }

    val thumbSize by animateDpAsState(
        targetValue = when {
            isToggleOn && isPressed -> 28.dp
            isToggleOn && isPressed.not() -> 26.dp
            isToggleOn.not() && isPressed -> 26.dp
            isToggleOn.not() && isPressed.not() -> 24.dp
            else -> Dp.Unspecified
        }
    )

    val alignment by animateDpAsState(
        targetValue = when {
            isToggleOn && isPressed -> 26.dp
            isToggleOn && isPressed.not() -> 27.dp
            isToggleOn.not() && isPressed -> 3.dp
            isToggleOn.not() && isPressed.not() -> 4.dp
            else -> Dp.Unspecified
        }
    )

    Box(
        modifier = modifier
            .width(56.dp)
            .height(32.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(
                enabled = isDisabled.not(),
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = rippleColor),
                onClick = onToggle
            )
//            .pointerInput(Unit) {
//                detectTapGestures(
//                    onPress = {
//                        isPressed = true
//                        tryAwaitRelease()
//                        isPressed = false
//                    }
//                )
//            }
        ,
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .padding(start = alignment)
                .size(thumbSize)
                .clip(CircleShape)
                .background(thumbColor)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ToggleButtonPrev() {

    var isDisabledState by remember { mutableStateOf(false) }
    var toggleState by remember { mutableStateOf(true) }

//    LaunchedEffect(Unit) {
//        while (true) {
//            delay(1000)
//            isDisabledState = !isDisabledState
//            delay(1000)
//            toggleState = !toggleState
//        }
//    }

    ToggleButton(
        isDisabled = isDisabledState,
        isToggleOn = toggleState,
        onToggle = {
            toggleState = !toggleState
        }
    )
}