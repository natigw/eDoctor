package nfv.profile.presentation.screens.profile

import nfv.storage.local.model.SupportedLanguages
import nfv.storage.local.model.SupportedThemes

data class ProfileState(
    val userFullName: String,
    val profileLink: String,
    val currentLanguage: SupportedLanguages,
    val currentTheme: SupportedThemes,
    val allowBiometrics: Boolean,
    val allowScreenshots: Boolean
)