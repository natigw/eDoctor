package nfv.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import nfv.ui_kit.components.buttons.ButtonState
import nfv.ui_kit.components.buttons.ButtonTypes
import nfv.ui_kit.components.buttons.keypad.IconKeypadButton
import nfv.ui_kit.components.buttons.keypad.NumberKeypadButton
import nfv.ui_kit.components.buttons.transparent.ActiveTransparentButton
import nfv.ui_kit.theme.BaseWhite
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray400
import nfv.ui_kit.theme.Gray500
import nfv.ui_kit.theme.Primary300
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun LockScreen() {

    var pinList by remember { mutableStateOf(listOf<Int>()) }

    val maxPinLength = 4

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BaseWhite),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.weight(1f))
        Spacer(Modifier.height(0.dp))
        AsyncImage(
            modifier = Modifier
                .border(width = 1.5.dp, color = Primary300, shape = CircleShape)
                .padding(4.dp)
                .size(64.dp)
                .clip(CircleShape),
            model = "aaa",  //TODO -> backendden link
            placeholder = painterResource(drawableR.img_user_profile),
            error = painterResource(drawableR.img_user_profile),
            contentDescription = "User profile picture",
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Username", //TODO -> backendden
            style = EDoctorTypography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.weight(1f))


        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(maxPinLength) { index ->
                CredentialDot(
                    isFilled = index < pinList.size
                )
            }
        }


        Spacer(Modifier.height(12.dp))

        val keypadNumbers = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9),
            listOf(-1, 0, -2)  //-1 = fingerprint, -2 = delete
        )

        keypadNumbers.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { number ->
                    when (number) {
                        -1 ->
                            IconKeypadButton(
                                iconRes = drawableR.ic_fingerprint,
                                contentDescription = "Fingerprint biometrics",
                                onClick = {

                                }
                            )

                        -2 ->
                            IconKeypadButton(
                                iconRes = drawableR.ic_delete,
                                contentDescription = "Delete",
                                onClick = {
                                    if (pinList.isNotEmpty()) {
                                        pinList = pinList.dropLast(1)
                                    }
                                }
                            )

                        else ->
                            NumberKeypadButton(
                                number = number,
                                onClick = {
                                    if (pinList.size < maxPinLength)
                                        pinList = pinList + number
                                }
                            )
                    }
                }
            }
        }

        Spacer(Modifier.height(0.dp))
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
    val strokeColor by animateColorAsState(
        targetValue = if (isFilled) Primary500 else Gray400,
        animationSpec = tween(500)
    )
    val strokeWidth by animateDpAsState(
        targetValue = if (isFilled) 10.dp else 2.dp,
        animationSpec = tween(500)
    )

    Box(
        modifier = modifier
            .size(20.dp)
            .clip(CircleShape)
            .border(width = strokeWidth, color = strokeColor, shape = CircleShape)
    )
}

@Preview
@Composable
private fun LockScreenPrev() {
    LockScreen()
}