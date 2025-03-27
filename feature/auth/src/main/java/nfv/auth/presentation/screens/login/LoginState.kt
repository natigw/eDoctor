package nfv.auth.presentation.screens.login

import nfv.ui_kit.components.buttons.model.ButtonState

data class LoginState(
    val emailText: String,
    val passwordText: String,
    val loginButtonState: ButtonState
)