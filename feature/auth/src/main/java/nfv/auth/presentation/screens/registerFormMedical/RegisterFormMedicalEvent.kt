package nfv.auth.presentation.screens.registerFormMedical

import java.util.Date

sealed interface RegisterFormMedicalEvent {
    class OnBloodTypeChanged(val newValue: BloodType) : RegisterFormMedicalEvent
    class OnSexChanged(val newValue: Sex) : RegisterFormMedicalEvent
    class OnWeightChanged(val newValue: Double) : RegisterFormMedicalEvent
    class OnBirthDateChanged(val newValue: Date) : RegisterFormMedicalEvent

    data object OnNavigateBack : RegisterFormMedicalEvent
    data object OnRegisterClicked : RegisterFormMedicalEvent
    data object GoToLoginScreen : RegisterFormMedicalEvent
}