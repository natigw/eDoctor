package nfv.ui_kit.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
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
import androidx.compose.material.ripple.rememberRipple
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
import nfv.ui_kit.theme.ButtonMediumPadding
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray100
import nfv.ui_kit.theme.Gray300
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.SquareButtonShape
import nfv.ui_kit.theme.Typography400
import nfv.ui_kit.theme.Typography50
import nfv.ui_kit.theme.Typography600
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun ActiveButtonMedium(
    modifier: Modifier = Modifier,
    state: ButtonState,
    onClick: (ButtonState) -> Unit,
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
            ButtonState.ENABLED -> Primary500
            ButtonState.LOADING -> Gray300
            ButtonState.COMPLETED -> Primary500
        },
        label = "Active medium button background color animation"
    )
    val contentColor by animateColorAsState(
        targetValue = when (state) {
            ButtonState.DISABLED -> Typography400
            ButtonState.ENABLED -> Typography50
            ButtonState.LOADING -> Typography600
            ButtonState.COMPLETED -> Typography50
        },
        label = "Active medium button content color animation"
    )

    Box(
        modifier = modifier
            .sizeIn(minHeight = 32.dp, minWidth = 32.dp)
            .clip(SquareButtonShape)
            .background(backgroundColor)
            .clickable(
                enabled = state != ButtonState.DISABLED && state != ButtonState.LOADING,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = {
                    onClick(state)
                }
            )
            .padding(ButtonMediumPadding),
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
                                .size(20.dp),
                            imageVector = ImageVector.vectorResource(startIconRes),
                            contentDescription = "Button start icon",
                            tint = contentColor
                        )
                        Spacer(Modifier.width(12.dp))
                    }
                    Text(
                        text = textEnabled,
                        style = EDoctorTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = contentColor
                    )
                    endIconRes?.let {
                        Spacer(Modifier.width(12.dp))
                        Icon(
                            modifier = Modifier
                                .size(20.dp),
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
                        style = EDoctorTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = contentColor
                    )
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier.requiredSize(18.dp),
                        strokeWidth = 2.dp,
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
                                .size(20.dp),
                            imageVector = ImageVector.vectorResource(onCompletedStartIconRes),
                            contentDescription = "Button start complete icon",
                            tint = contentColor
                        )
                        Spacer(Modifier.width(12.dp))
                    } else {
                        startIconRes?.let {
                            Icon(
                                modifier = Modifier
                                    .size(20.dp),
                                imageVector = ImageVector.vectorResource(startIconRes),
                                contentDescription = "Button start icon",
                                tint = contentColor
                            )
                            Spacer(Modifier.width(12.dp))
                        }
                    }

                    Text(
                        text = textCompleted ?: textEnabled,
                        style = EDoctorTypography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = contentColor
                    )

                    if (onCompletedEndIconRes != null) {
                        Spacer(Modifier.width(12.dp))
                        Icon(
                            modifier = Modifier
                                .size(20.dp),
                            imageVector = ImageVector.vectorResource(onCompletedEndIconRes),
                            contentDescription = "Button end complete icon",
                            tint = contentColor
                        )
                    } else {
                        endIconRes?.let {
                            Spacer(Modifier.width(12.dp))
                            Icon(
                                modifier = Modifier
                                    .size(20.dp),
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
private fun ActiveButtonMediumPrev() {

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
        ActiveButtonMedium(
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
        ActiveButtonMedium(
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
        ActiveButtonMedium(
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
        ActiveButtonMedium(
            state = state,
            textEnabled = "Button",
            textLoading = "loading...",
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