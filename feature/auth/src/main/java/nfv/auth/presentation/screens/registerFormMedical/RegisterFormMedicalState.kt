package nfv.auth.presentation.screens.registerFormMedical

import nfv.ui_kit.components.buttons.model.ButtonState
import java.util.Date

data class RegisterFormMedicalState(
    val fullNameText: String,
    val bloodType: BloodType,
    val sex: Sex,
    val weight: Double,
    val birthDate: Date,
    val registerButtonState: ButtonState
)

enum class BloodType(val notation: String) {
    FIRST_NEGATIVE("O-"),
    FIRST_POSITIVE("O+"),
    SECOND_NEGATIVE("B-"),
    SECOND_POSITIVE("B+"),
    THIRD_NEGATIVE("A-"),
    THIRD_POSITIVE("A+"),
    FORTH_NEGATIVE("AB-"),
    FORTH_POSITIVE("AB+"),
}

enum class Sex(val displayName: String) {
    MALE("Male"),
    FEMALE("Female")
}