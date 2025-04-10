package nfv.auth.presentation.screens.otp

interface OTPEvent {
    data object OnNavigateBack: OTPEvent
    data object OnContinueClicked: OTPEvent
    data object OnResendClicked: OTPEvent
    data class OnOtpChanged(val otp: String): OTPEvent
}