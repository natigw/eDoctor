package nfv.auth.presentation.screens.registerForm

import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.inputFields.PasswordStrength

data class RegisterFormState(
    val fullNameText: String,
    val emailText: String,
    val passwordText: String,
    val confirmPasswordText: String,
    val emailHelperText: String?,
    val passwordStrength: PasswordStrength,
    val arePasswordsIncompatible: Boolean,
    val continueButtonState: ButtonState
)