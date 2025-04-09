package nfv.auth.presentation.screens.registerFormMedical

import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.model.BloodType
import nfv.ui_kit.model.Gender
import java.util.Date

data class RegisterFormMedicalState(
    val fullNameText: String,
    val bloodType: BloodType?,
    val gender: Gender?,
    val weight: Double?,
    val birthDate: Date?,
    val registerButtonState: ButtonState
)
