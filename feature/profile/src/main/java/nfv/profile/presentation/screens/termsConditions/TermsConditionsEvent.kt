package nfv.profile.presentation.screens.termsConditions

sealed interface TermsConditionsEvent {

    data object OnNavigateBack: TermsConditionsEvent

}