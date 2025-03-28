package nfv.profile.presentation.screens.aboutUs

sealed interface AboutUsEvent {
    data object OnNavigateBack : AboutUsEvent
}