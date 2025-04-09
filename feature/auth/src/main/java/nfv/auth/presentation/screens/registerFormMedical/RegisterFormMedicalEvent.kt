package nfv.auth.presentation.screens.registerFormMedical

import nfv.ui_kit.model.BloodType
import nfv.ui_kit.model.Gender
import java.util.Date

sealed interface RegisterFormMedicalEvent {
    class OnBloodTypeChanged(val newValue: BloodType?) : RegisterFormMedicalEvent
    class OnGenderChanged(val newValue: Gender?) : RegisterFormMedicalEvent
    class OnWeightChanged(val newValue: Double?) : RegisterFormMedicalEvent
    class OnBirthDateChanged(val newValue: Date?) : RegisterFormMedicalEvent

    data object OnNavigateBack : RegisterFormMedicalEvent
    data object OnRegisterClicked : RegisterFormMedicalEvent
    data object GoToLoginScreen : RegisterFormMedicalEvent
}