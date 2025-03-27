package nfv.ui_kit.components.buttons.keypad.keypad

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.R
import nfv.ui_kit.components.buttons.keypad.IconKeypadButton
import nfv.ui_kit.components.buttons.keypad.NumberKeypadButton

@Composable
fun PasscodeKeypadSection(
    keypadAuxiliaryButton: KeypadAuxiliaryButton,
    pinList: List<Int>,
    onPinChange: (List<Int>) -> Unit
) {

    val maxPinLength = 4

    Column {
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
            listOf(
                keypadAuxiliaryButton.value,
                0,
                -1
            )  //-1 = delete, -2 = none, -3 = C (clear), -4 = fingerprint, -5 = face id
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
                                iconRes = R.drawable.ic_delete,
                                contentDescription = "Delete",
                                onClick = {
                                    if (pinList.isNotEmpty()) {
                                        onPinChange(pinList.dropLast(1))
                                    }
                                }
                            )

                        -2 ->
                            Spacer(modifier = Modifier.size(80.dp))

                        -3 ->
                            IconKeypadButton(
                                iconRes = R.drawable.ic_restart_outlined,
                                contentDescription = "Clear",
                                onClick = {
                                    onPinChange(emptyList())
                                }
                            )

                        -4 ->
                            IconKeypadButton(
                                iconRes = R.drawable.ic_fingerprint,
                                contentDescription = "Fingerprint biometrics",
                                onClick = {

                                }
                            )

                        -5 ->
                            IconKeypadButton(
                                iconRes = R.drawable.ic_face_recognition2,
                                contentDescription = "Face recognition biometrics",
                                onClick = {

                                }
                            )

                        else ->
                            NumberKeypadButton(
                                number = number,
                                onClick = {
                                    if (pinList.size < maxPinLength)
                                        onPinChange(pinList + number)
                                }
                            )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
private fun KeypadGridPrev() {
    PasscodeKeypadSection(
        keypadAuxiliaryButton = KeypadAuxiliaryButton.FACE_RECOGNITION,
        pinList = listOf(1, 2, 3, 4),
        onPinChange = {}
    )
}