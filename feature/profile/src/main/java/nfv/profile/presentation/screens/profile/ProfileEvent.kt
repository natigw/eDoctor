package nfv.profile.presentation.screens.profile

import nfv.storage.local.model.SupportedLanguages
import nfv.storage.local.model.SupportedThemes

sealed interface ProfileEvent {
    data object GoToHome : ProfileEvent
    data object GoToHistory : ProfileEvent
    data object GoToProfile : ProfileEvent

    data object GoToMedicalInfo : ProfileEvent

    data object OnOptionEditProfileClicked : ProfileEvent
    class OnLanguageConfirmed(val language: SupportedLanguages) : ProfileEvent
    class OnThemeConfirmed(val theme: SupportedThemes) : ProfileEvent
    data object OnOptionChangePasscodeClicked : ProfileEvent
    data object OnOptionBiometricsClicked : ProfileEvent
    data object OnOptionAllowScreenshotsClicked : ProfileEvent
    data object OnOptionTermsClicked : ProfileEvent
    data object OnOptionAboutUsClicked : ProfileEvent

    data object OnLogoutClicked : ProfileEvent
}