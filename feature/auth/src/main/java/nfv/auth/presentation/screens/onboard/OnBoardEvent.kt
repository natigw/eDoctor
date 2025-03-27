package nfv.auth.presentation.screens.onboard

sealed interface OnBoardEvent {
    data object OnNextClicked: OnBoardEvent
    data object OnSkipClicked: OnBoardEvent
}