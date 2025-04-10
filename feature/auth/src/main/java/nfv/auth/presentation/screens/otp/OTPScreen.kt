package nfv.auth.presentation.screens.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.buttons.model.ButtonTypes
import nfv.ui_kit.components.buttons.square.ActiveButton
import nfv.ui_kit.components.buttons.transparent.ActiveTransparentButton
import nfv.ui_kit.components.inputFields.OtpInputField
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.theme.BaseWhite
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Typography700
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun OTPScreen(
    state: OTPState,
    onUiEvent: (OTPEvent) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    Scaffold(
        modifier = Modifier
            .systemBarsPadding(),
        topBar = {
            TopBar(
                leadingIcon = IconWithAction(
                    icon = drawableR.ic_arrow_left,
                    action = {
                        onUiEvent(OTPEvent.OnNavigateBack)
                    }
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BaseWhite)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Send OTP Code",
                style = EDoctorTypography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = EDoctorTypography.bodyMedium.copy(color = Typography700)
                            .toSpanStyle()
                    ) {
                        append("Enter the 6-digit that we have sent via the email to ")
                    }
                    withStyle(
                        style = EDoctorTypography.bodyMedium
                            .copy(fontWeight = FontWeight.Bold)
                            .toSpanStyle()
                    ) {
                        append(state.email)
                    }
                }
            )

            Spacer(Modifier.height(32.dp))

            Column {
                Box {
                    CompositionLocalProvider(
                        LocalTextSelectionColors provides TextSelectionColors(
                            handleColor = Color.Transparent, backgroundColor = Color.Transparent
                        )
                    ) {
                        OutlinedTextField(
                            value = state.otp,
                            onValueChange = {
                                onUiEvent(OTPEvent.OnOtpChanged(it))
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.NumberPassword,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    onUiEvent(OTPEvent.OnContinueClicked)
                                }
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                cursorColor = Color.Transparent,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            ),
                            visualTransformation = PasswordVisualTransformation(),
                            interactionSource = interactionSource,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(76.dp)
                                .alpha(0f)
                                .pointerInput(Unit) {})
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        repeat(6) {
                            OtpInputField(digit = if (it < state.otp.length) state.otp[it].toString() else "")
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(drawableR.ic_timer),
                        contentDescription = "Remaining time to expiration of OTP",
                        tint = Primary500
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = state.timer,
                        style = EDoctorTypography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Typography700
                        )
                    )
                }
            }

            Spacer(Modifier.weight(1f))

            ActiveTransparentButton(
                modifier = Modifier
                    .fillMaxWidth(),
                buttonType = ButtonTypes.LARGE,
                state = ButtonState.ENABLED,
                textEnabled = "Resend Code",
                onClick = {
                    onUiEvent(OTPEvent.OnResendClicked)
                }
            )
            Spacer(Modifier.height(16.dp))
            ActiveButton(
                modifier = Modifier
                    .fillMaxWidth(),
                buttonType = ButtonTypes.LARGE,
                state = state.continueButtonState,
                textEnabled = "Continue",
                onClick = {
                    onUiEvent(OTPEvent.OnContinueClicked)
                }
            )

            Spacer(Modifier.height(32.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    withStyle(
                        style = EDoctorTypography.labelMedium.copy(color = Typography700)
                            .toSpanStyle()
                    ) {
                        append("By signing up or logging in, I accept the apps\n")
                    }
                    withStyle(
                        style = EDoctorTypography.labelMedium.copy(color = Primary500).toSpanStyle()
                    ) {
                        //TODO -> bura onClick qoy
                        append("Terms of Service")
                    }
                    withStyle(
                        style = EDoctorTypography.labelMedium.copy(color = Typography700)
                            .toSpanStyle()
                    ) {
                        append(" and ")
                    }
                    withStyle(
                        style = EDoctorTypography.labelMedium.copy(color = Primary500).toSpanStyle()
                    ) {
                        append("Privacy Policy")
                    }
                },
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OTPScreenPrev() {
    OTPScreen(
        state = OTPState("", "", "", ButtonState.DISABLED),
        onUiEvent = { }
    )
}