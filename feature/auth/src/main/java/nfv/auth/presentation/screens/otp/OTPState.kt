package nfv.auth.presentation.screens.otp

import nfv.ui_kit.components.buttons.model.ButtonState

data class OTPState(
    val otp: String,
    val timer: String,
    val email: String,
    val continueButtonState: ButtonState
)
