package nfv.auth.presentation.screens.registerForm

sealed interface RegisterFormEvent {
    class OnFullNameChanged(val newValue: String) : RegisterFormEvent
    class OnEmailChanged(val newValue: String) : RegisterFormEvent
    class OnPasswordChanged(val newValue: String) : RegisterFormEvent
    class OnConfirmPasswordChanged(val newValue: String) : RegisterFormEvent

    data object OnNavigateBack : RegisterFormEvent
    data object GoToLoginScreen : RegisterFormEvent
    data object OnClickContinue: RegisterFormEvent
}