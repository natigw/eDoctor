package nfv.ui_kit.components.buttons.square

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import nfv.ui_kit.theme.BaseWhite
import nfv.ui_kit.theme.Gray100
import nfv.ui_kit.theme.Gray200
import nfv.ui_kit.theme.Primary200
import nfv.ui_kit.theme.Primary50
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.SquareButtonShape
import nfv.ui_kit.theme.Typography300
import nfv.ui_kit.theme.Typography50
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun OutlinedButton(
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
    // TODO -> Eger yaxsi olsa butun outlined butonlara elave et, ripple null etmek yaddan cixmasin
    val interactionSource = remember { MutableInteractionSource() }
    var isPressed by remember { mutableStateOf(false) }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect {
            when (it) {
                is PressInteraction.Press -> isPressed = true
                is PressInteraction.Release, is PressInteraction.Cancel -> isPressed = false
            }
        }
    }

    val backgroundColor by animateColorAsState(
        targetValue = if (isPressed) {
            when (state) {
                ButtonState.DISABLED -> Gray100  //cannot be pressed
                ButtonState.ENABLED -> Primary500
                ButtonState.LOADING -> Primary50 //cannot be pressed
                ButtonState.COMPLETED -> Primary500
            }
        } else {
            when (state) {
                ButtonState.DISABLED -> Gray100
                ButtonState.ENABLED -> BaseWhite
                ButtonState.LOADING -> Primary50
                ButtonState.COMPLETED -> BaseWhite
            }
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
        targetValue = if (isPressed) {
            when (state) {
                ButtonState.DISABLED -> Typography300  //cannot be pressed
                ButtonState.ENABLED, ButtonState.LOADING, ButtonState.COMPLETED -> Typography50
            }
        } else {
            when (state) {
                ButtonState.DISABLED -> Typography300
                ButtonState.ENABLED, ButtonState.LOADING, ButtonState.COMPLETED -> Primary500
            }
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
                interactionSource = interactionSource,
                indication = null,
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
private fun OutlinedButtonPrev() {

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
        OutlinedButton(
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
        OutlinedButton(
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
        OutlinedButton(
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
        OutlinedButton(
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