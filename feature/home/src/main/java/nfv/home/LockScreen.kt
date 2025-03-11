package nfv.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.components.buttons.ButtonState
import nfv.ui_kit.components.buttons.ButtonTypes
import nfv.ui_kit.components.buttons.keypad.IconKeypadButton
import nfv.ui_kit.components.buttons.keypad.NumberKeypadButton
import nfv.ui_kit.components.buttons.transparent.ActiveTransparentButton
import nfv.ui_kit.theme.BaseTransparent
import nfv.ui_kit.theme.BaseWhite
import nfv.ui_kit.theme.DefaultScreenPadding
import nfv.ui_kit.theme.Gray400
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun LockScreen() {

    val pinList = remember { mutableStateListOf<Int>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaseWhite)
            .padding(DefaultScreenPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(pinList.size) { CredentialDot(isFilled = true) }
            repeat(4 - pinList.size) { CredentialDot() }
        }

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            NumberKeypadButton(number = 1, onClick = {
                pinList.add(1)
            })
            NumberKeypadButton(number = 2, onClick = {
                pinList.add(2)
            })
            NumberKeypadButton(number = 3, onClick = {
                pinList.add(3)
            })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            NumberKeypadButton(number = 4, onClick = {
                pinList.add(4)
            })
            NumberKeypadButton(number = 5, onClick = {
                pinList.add(5)
            })
            NumberKeypadButton(number = 6, onClick = {
                pinList.add(6)
            })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            NumberKeypadButton(number = 7, onClick = {
                pinList.add(7)
            })
            NumberKeypadButton(number = 8, onClick = {
                pinList.add(8)
            })
            NumberKeypadButton(number = 9, onClick = {
                pinList.add(9)
            })
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconKeypadButton(
                iconRes = drawableR.ic_fingerprint,
                contentDescription = "Fingerprint biometrics",
                onClick = { }
            )
            NumberKeypadButton(number = 0, onClick = {
                pinList.add(0)
            })
            IconKeypadButton(
                iconRes = drawableR.ic_delete,
                contentDescription = "Delete",
                onClick = {
                    pinList.removeLastOrNull()
                }
            )
        }

        Spacer(Modifier.height(16.dp))
        ActiveTransparentButton(
            buttonType = ButtonTypes.MEDIUM,
            textEnabled = "Forgot your PIN code?",
            state = ButtonState.ENABLED,
            onClick = {

            }
        )
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun CredentialDot(
    modifier: Modifier = Modifier,
    isFilled: Boolean = false
) {

    val backgroundColor by animateColorAsState(
        targetValue = if (isFilled) Primary500 else BaseTransparent
    )
    val strokeColor by animateColorAsState(
        targetValue = if (isFilled) Primary500 else Gray400
    )

    Box(
        modifier = modifier
            .size(20.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .border(width = 2.dp, color = strokeColor, shape = CircleShape)
    )
}

@Preview
@Composable
private fun LockScreenPrev() {
    LockScreen()
}