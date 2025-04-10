package nfv.ui_kit.components.buttons.square

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ripple
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.buttons.model.ButtonTypes
import nfv.ui_kit.theme.Gray100
import nfv.ui_kit.theme.Gray200
import nfv.ui_kit.theme.Gray300
import nfv.ui_kit.theme.Gray50
import nfv.ui_kit.theme.SquareButtonShape
import nfv.ui_kit.theme.Typography300
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    buttonType: ButtonTypes = ButtonTypes.MEDIUM,
    state: ButtonState,
    onClick: () -> Unit,
    textEnabled: String,
    textLoading: String? = null,
    textCompleted: String? = null,
    @DrawableRes startIconRes: Int? = null,
    @DrawableRes endIconRes: Int? = null,
    @DrawableRes onCompletedStartIconRes: Int? = null,
    @DrawableRes onCompletedEndIconRes: Int? = null
) {

    val backgroundColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> Gray100
            ButtonState.ENABLED -> Gray50
            ButtonState.LOADING -> Gray300
            ButtonState.COMPLETED -> Gray50
        }
    )
    val stokeColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> Gray200
            ButtonState.ENABLED -> Gray200
            ButtonState.LOADING -> Gray300
            ButtonState.COMPLETED -> Gray200
        }
    )
    val contentColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> Typography300
            ButtonState.ENABLED, ButtonState.LOADING, ButtonState.COMPLETED -> MaterialTheme.colorScheme.outline
        }
    )

    Box(
        modifier = modifier
            .sizeIn(minHeight = 32.dp, minWidth = 32.dp)
            .clip(SquareButtonShape)
            .background(backgroundColor)
            .border(width = 1.dp, color = stokeColor, shape = SquareButtonShape)
            .clickable(
                enabled = state != ButtonState.DISABLED && state != ButtonState.LOADING,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = onClick
            )
            .padding(buttonType.buttonPadding),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            ButtonState.DISABLED, ButtonState.ENABLED -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    startIconRes?.let {
                        Icon(
                            modifier = Modifier
                                .size(buttonType.iconSize),
                            imageVector = ImageVector.vectorResource(startIconRes),
                            contentDescription = "Button start icon",
                            tint = contentColor
                        )
                        Spacer(Modifier.width(buttonType.itemSpacing))
                    }
                    Text(
                        text = textEnabled,
                        style = buttonType.textTypography.copy(fontWeight = FontWeight.Bold),
                        color = contentColor
                    )
                    endIconRes?.let {
                        Spacer(Modifier.width(buttonType.itemSpacing))
                        Icon(
                            modifier = Modifier
                                .size(buttonType.iconSize),
                            imageVector = ImageVector.vectorResource(endIconRes),
                            contentDescription = "Button end icon",
                            tint = contentColor
                        )
                    }
                }
            }

            ButtonState.LOADING -> {
                if (textLoading != null) {
                    Text(
                        text = textLoading,
                        style = buttonType.textTypography.copy(fontWeight = FontWeight.Bold),
                        color = contentColor
                    )
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier.requiredSize(buttonType.circularProgressSize),
                        strokeWidth = buttonType.circularProgressStrokeWidth,
                        strokeCap = StrokeCap.Round,
                        color = contentColor
                    )
                }
            }

            ButtonState.COMPLETED -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (onCompletedStartIconRes != null) {
                        Icon(
                            modifier = Modifier
                                .size(buttonType.iconSize),
                            imageVector = ImageVector.vectorResource(onCompletedStartIconRes),
                            contentDescription = "Button start complete icon",
                            tint = contentColor
                        )
                        Spacer(Modifier.width(buttonType.itemSpacing))
                    } else {
                        startIconRes?.let {
                            Icon(
                                modifier = Modifier
                                    .size(buttonType.iconSize),
                                imageVector = ImageVector.vectorResource(startIconRes),
                                contentDescription = "Button start icon",
                                tint = contentColor
                            )
                            Spacer(Modifier.width(buttonType.itemSpacing))
                        }
                    }

                    Text(
                        text = textCompleted ?: textEnabled,
                        style = buttonType.textTypography.copy(fontWeight = FontWeight.Bold),
                        color = contentColor
                    )

                    if (onCompletedEndIconRes != null) {
                        Spacer(Modifier.width(buttonType.itemSpacing))
                        Icon(
                            modifier = Modifier
                                .size(buttonType.iconSize),
                            imageVector = ImageVector.vectorResource(onCompletedEndIconRes),
                            contentDescription = "Button end complete icon",
                            tint = contentColor
                        )
                    } else {
                        endIconRes?.let {
                            Spacer(Modifier.width(buttonType.itemSpacing))
                            Icon(
                                modifier = Modifier
                                    .size(buttonType.iconSize),
                                imageVector = ImageVector.vectorResource(endIconRes),
                                contentDescription = "Button start icon",
                                tint = contentColor
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun DefaultButtonPrev() {

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
        DefaultButton(
            buttonType = ButtonTypes.SMALL,
            state = state,
            textEnabled = "Button",
            textLoading = "loading...",
            startIconRes = drawableR.ic_notifications,
            onClick = {
                state = when (state) {
                    ButtonState.DISABLED -> ButtonState.ENABLED
                    ButtonState.ENABLED -> ButtonState.LOADING
                    ButtonState.LOADING -> ButtonState.COMPLETED
                    ButtonState.COMPLETED -> ButtonState.DISABLED
                }
            }
        )
        DefaultButton(
            buttonType = ButtonTypes.MEDIUM,
            state = state,
            textEnabled = "Button",
            textLoading = "loading...",
            endIconRes = drawableR.ic_search,
            onClick = {
                state = when (state) {
                    ButtonState.DISABLED -> ButtonState.ENABLED
                    ButtonState.ENABLED -> ButtonState.LOADING
                    ButtonState.LOADING -> ButtonState.COMPLETED
                    ButtonState.COMPLETED -> ButtonState.DISABLED
                }
            }
        )
        DefaultButton(
            buttonType = ButtonTypes.LARGE,
            state = state,
            textEnabled = "Button",
            textLoading = "loading...",
            startIconRes = drawableR.ic_notifications,
            endIconRes = drawableR.ic_search,
            onClick = {
                state = when (state) {
                    ButtonState.DISABLED -> ButtonState.ENABLED
                    ButtonState.ENABLED -> ButtonState.LOADING
                    ButtonState.LOADING -> ButtonState.COMPLETED
                    ButtonState.COMPLETED -> ButtonState.DISABLED
                }
            }
        )
        DefaultButton(
            buttonType = ButtonTypes.SMALL,
            state = state,
            textEnabled = "Button",
            //textLoading = "loading...",
            startIconRes = drawableR.ic_notifications,
            onCompletedEndIconRes = drawableR.ic_arrow_right,
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