package nfv.auth.presentation.screens.register

sealed interface RegisterEvent {
    data object OnNavigateBack: RegisterEvent
    data object OnContinueClicked: RegisterEvent
}