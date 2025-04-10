package nfv.ui_kit.components.buttons.icon

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ripple
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.buttons.icon.model.IconButtonTypes
import nfv.ui_kit.theme.Gray100
import nfv.ui_kit.theme.Gray200
import nfv.ui_kit.theme.Gray50
import nfv.ui_kit.theme.Primary200
import nfv.ui_kit.theme.Primary50
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.SquareButtonShape
import nfv.ui_kit.theme.Typography300
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun OutlinedIconButton(
    modifier: Modifier = Modifier,
    buttonType: IconButtonTypes = IconButtonTypes.MEDIUM,
    isCircular: Boolean = false,
    state: ButtonState,
    onClick: (ButtonState) -> Unit,
    @DrawableRes iconRes: Int,
    @DrawableRes onCompletedIconRes: Int? = null
) {

    val backgroundColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> Gray100
            ButtonState.ENABLED -> Gray50
            ButtonState.LOADING -> Primary50
            ButtonState.COMPLETED -> Gray50
        }
    )
    val stokeColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> Gray200
            ButtonState.ENABLED -> Primary500
            ButtonState.LOADING -> Primary200
            ButtonState.COMPLETED -> Primary500
        }
    )
    val contentColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> Typography300
            ButtonState.ENABLED, ButtonState.LOADING, ButtonState.COMPLETED -> Primary500
        }
    )

    Box(
        modifier = modifier
            .sizeIn(minHeight = 32.dp, minWidth = 32.dp)
            .clip(if (isCircular) CircleShape else SquareButtonShape)
            .background(backgroundColor)
            .border(width = 1.dp, color = stokeColor, shape = if (isCircular) CircleShape else SquareButtonShape)
            .clickable(
                enabled = state != ButtonState.DISABLED && state != ButtonState.LOADING,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = {
                    onClick(state)
                }
            )
            .padding(buttonType.iconPadding),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            ButtonState.DISABLED, ButtonState.ENABLED -> {
                Icon(
                    modifier = Modifier
                        .size(buttonType.iconSize),
                    imageVector = ImageVector.vectorResource(iconRes),
                    contentDescription = "Button icon",
                    tint = contentColor
                )
            }

            ButtonState.LOADING -> {
                CircularProgressIndicator(
                    modifier = Modifier.requiredSize(buttonType.iconSize),
                    strokeWidth = buttonType.circularProgressStrokeWidth,
                    strokeCap = StrokeCap.Round,
                    color = contentColor
                )
            }

            ButtonState.COMPLETED -> {
                Icon(
                    modifier = Modifier
                        .size(buttonType.iconSize),
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
private fun OutlinedIconButtonPrev() {

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
        OutlinedIconButton(
            buttonType = IconButtonTypes.SMALL,
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
        OutlinedIconButton(
            buttonType = IconButtonTypes.MEDIUM,
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
        OutlinedIconButton(
            buttonType = IconButtonTypes.LARGE,
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
        OutlinedIconButton(
            buttonType = IconButtonTypes.SMALL,
            iconRes = drawableR.ic_search,
            isCircular = true,
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
        OutlinedIconButton(
            buttonType = IconButtonTypes.MEDIUM,
            iconRes = drawableR.ic_search,
            isCircular = true,
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
        OutlinedIconButton(
            buttonType = IconButtonTypes.LARGE,
            iconRes = drawableR.ic_search,
            onCompletedIconRes = drawableR.ic_notifications,
            isCircular = true,
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