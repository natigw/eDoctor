package nfv.consultation.presentation

sealed interface ConsultationEvent {

    data object OnNavigateBack: ConsultationEvent

    data class OnQuestionTextChanged(val newValue: String) : ConsultationEvent

    data object OnQuestionAsked : ConsultationEvent

}