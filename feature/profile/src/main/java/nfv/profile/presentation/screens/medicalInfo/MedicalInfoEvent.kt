package nfv.profile.presentation.screens.medicalInfo

sealed interface MedicalInfoEvent {

    data object OnEditDetailsClicked: MedicalInfoEvent

    data object GoToAllergies: MedicalInfoEvent

    data object OnNavigateBack: MedicalInfoEvent
}