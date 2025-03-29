package nfv.auth.presentation.screens.login

import nfv.ui_kit.components.buttons.model.ButtonState

sealed interface LoginEvent {
    data class OnEmailTextChanged(val newValue: String) : LoginEvent
    data class OnPasswordTextChanged(val newValue: String) : LoginEvent
    data class OnLoginButtonStateUpdated(val newButtonState: ButtonState) : LoginEvent //TODO -> buna ehtiyac varmi??
    data object OnNavigateBack : LoginEvent
    data object OnForgotPasswordClicked : LoginEvent
    data object OnLoginButtonClicked : LoginEvent
    data object OnLoginWithGoogleButtonClicked : LoginEvent
    data object OnLoginWithFacebookButtonClicked : LoginEvent
    data object GoToRegister : LoginEvent
}