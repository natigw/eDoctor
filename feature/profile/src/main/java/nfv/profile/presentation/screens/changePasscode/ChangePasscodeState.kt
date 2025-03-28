package nfv.profile.presentation.screens.changePasscode

import androidx.compose.ui.graphics.Color

data class ChangePasscodeState(
    val directionText: String,
    val directionTextColor: Color,
    val pinList: List<Int>,
    val newPinCode: List<Int>?
)