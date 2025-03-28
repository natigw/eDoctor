package nfv.profile.presentation.screens.changePasscode

import androidx.compose.ui.graphics.Color

sealed interface ChangePasscodeEvent {

    data class OnDirectionTextUpdated(val newText: String): ChangePasscodeEvent
    data class OnDirectionTextColorUpdated(val newColor: Color): ChangePasscodeEvent
    data class OnPinListChanged(val newValue: List<Int>): ChangePasscodeEvent
    data class OnNewPinCodeUpdated(val newPin: List<Int>?): ChangePasscodeEvent

    data object OnNavigateBack: ChangePasscodeEvent
}