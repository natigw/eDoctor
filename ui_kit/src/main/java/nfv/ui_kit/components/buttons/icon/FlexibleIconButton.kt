package nfv.ui_kit.components.buttons.icon

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import nfv.ui_kit.components.buttons.ButtonState
import nfv.ui_kit.theme.Gray100
import nfv.ui_kit.theme.Gray200
import nfv.ui_kit.theme.Gray300
import nfv.ui_kit.theme.Gray50
import nfv.ui_kit.theme.SquareButtonShape
import nfv.ui_kit.theme.Typography300
import nfv.ui_kit.theme.Typography900
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun FlexibleIconButton(
    modifier: Modifier = Modifier,
    iconPadding: PaddingValues = PaddingValues(12.dp),
    circularProgressStrokeWidth: Dp = 2.5.dp,
    buttonShape: Shape = SquareButtonShape,
    borderStrokeColor: Color = Gray200,
    buttonBackgroundColor: Color = Gray50,
    iconColor: Color = Typography900,
    state: ButtonState,
    onClick: (ButtonState) -> Unit,
    @DrawableRes iconRes: Int,
    @DrawableRes onCompletedIconRes: Int? = null
) {

    val backgroundColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> Gray100
            ButtonState.ENABLED -> buttonBackgroundColor
            ButtonState.LOADING -> Gray300
            ButtonState.COMPLETED -> buttonBackgroundColor
        }
    )
    val stokeColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> Gray200
            ButtonState.ENABLED -> borderStrokeColor
            ButtonState.LOADING -> Gray300
            ButtonState.COMPLETED -> borderStrokeColor
        }
    )
    val contentColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> Typography300
            ButtonState.ENABLED, ButtonState.LOADING, ButtonState.COMPLETED -> iconColor
        }
    )

    Box(
        modifier = modifier
            .sizeIn(minHeight = 48.dp, minWidth = 48.dp)
            .clip(buttonShape)
            .background(backgroundColor)
            .border(width = 1.dp, color = stokeColor, shape = buttonShape)
            .clickable(
                enabled = state != ButtonState.DISABLED && state != ButtonState.LOADING,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = {
                    onClick(state)
                }
            )
            .padding(iconPadding),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            ButtonState.DISABLED, ButtonState.ENABLED -> {
                Icon(
                    modifier = Modifier
                        .matchParentSize(),
                    imageVector = ImageVector.vectorResource(iconRes),
                    contentDescription = "Button icon",
                    tint = contentColor
                )
            }

            ButtonState.LOADING -> {
                CircularProgressIndicator(
                    modifier = Modifier.matchParentSize(),
                    strokeWidth = circularProgressStrokeWidth,
                    strokeCap = StrokeCap.Round,
                    color = contentColor
                )
            }

            ButtonState.COMPLETED -> {

                Icon(
                    modifier = Modifier
                        .matchParentSize(),
                    imageVector = ImageVector.vectorResource(onCompletedIconRes ?: iconRes),
                    contentDescription = "Button complete icon",
                    tint = contentColor
                )


            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun FlexibleIconButtonPrev() {

    var state by remember { mutableStateOf(ButtonState.DISABLED) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            state = when (state) {
                ButtonState.DISABLED -> ButtonState.ENABLED
                ButtonState.ENABLED -> ButtonState.LOADING
                ButtonState.LOADING -> ButtonState.COMPLETED
                ButtonState.COMPLETED -> ButtonState.DISABLED
            }
        }
    }

    Column {
        FlexibleIconButton(
            iconRes = drawableR.ic_search,
            state = state,
            onClick = {
                state = when (state) {
                    ButtonState.DISABLED -> ButtonState.ENABLED
                    ButtonState.ENABLED -> ButtonState.LOADING
                    ButtonState.LOADING -> ButtonState.COMPLETED
                    ButtonState.COMPLETED -> ButtonState.DISABLED
                }
            }
        )
        FlexibleIconButton(
            iconRes = drawableR.ic_search,
            state = state,
            onClick = {
                state = when (state) {
                    ButtonState.DISABLED -> ButtonState.ENABLED
                    ButtonState.ENABLED -> ButtonState.LOADING
                    ButtonState.LOADING -> ButtonState.COMPLETED
                    ButtonState.COMPLETED -> ButtonState.DISABLED
                }
            }
        )
        FlexibleIconButton(
            iconRes = drawableR.ic_search,
            onCompletedIconRes = drawableR.ic_notifications,
            state = state,
            onClick = {
                state = when (state) {
                    ButtonState.DISABLED -> ButtonState.ENABLED
                    ButtonState.ENABLED -> ButtonState.LOADING
                    ButtonState.LOADING -> ButtonState.COMPLETED
                    ButtonState.COMPLETED -> ButtonState.DISABLED
                }
            }
        )
        FlexibleIconButton(
            iconRes = drawableR.ic_search,
            buttonShape = CircleShape,
            state = state,
            onClick = {
                state = when (state) {
                    ButtonState.DISABLED -> ButtonState.ENABLED
                    ButtonState.ENABLED -> ButtonState.LOADING
                    ButtonState.LOADING -> ButtonState.COMPLETED
                    ButtonState.COMPLETED -> ButtonState.DISABLED
                }
            }
        )
        FlexibleIconButton(
            iconRes = drawableR.ic_search,
            buttonShape = CircleShape,
            state = state,
            onClick = {
                state = when (state) {
                    ButtonState.DISABLED -> ButtonState.ENABLED
                    ButtonState.ENABLED -> ButtonState.LOADING
                    ButtonState.LOADING -> ButtonState.COMPLETED
                    ButtonState.COMPLETED -> ButtonState.DISABLED
                }
            }
        )
        FlexibleIconButton(
            iconRes = drawableR.ic_search,
            onCompletedIconRes = drawableR.ic_notifications,
            state = state,
            onClick = {
                state = when (state) {
                    ButtonState.DISABLED -> ButtonState.ENABLED
                    ButtonState.ENABLED -> ButtonState.LOADING
                    ButtonState.LOADING -> ButtonState.COMPLETED
                    ButtonState.COMPLETED -> ButtonState.DISABLED
                }
            }
        )
    }
}