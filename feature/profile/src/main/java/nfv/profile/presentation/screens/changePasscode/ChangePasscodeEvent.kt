package nfv.profile.presentation.screens.changePasscode

sealed interface ChangePasscodeEvent {
    data object OnNavigateBack: ChangePasscodeEvent
}