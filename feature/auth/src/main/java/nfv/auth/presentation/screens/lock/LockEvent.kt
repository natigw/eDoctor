package nfv.auth.presentation.screens.lock

interface LockEvent {
    data object OnPinCodeEntered : LockEvent
    data object OnForgotPinClicked : LockEvent
    data object OnFingerprintClicked : LockEvent
    data class OnPinCodeChanged(val pinList: List<Int>) : LockEvent

}