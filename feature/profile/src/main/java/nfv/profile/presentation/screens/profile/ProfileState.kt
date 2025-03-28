package nfv.profile.presentation.screens.profile

import nfv.profile.presentation.screens.changeLanguage.model.SupportedLanguages
import nfv.profile.presentation.screens.changeTheme.model.SupportedThemes

data class ProfileState(
    val userFullName: String,
    val profileLink: String,
    val currentLanguage: SupportedLanguages,
    val currentTheme: SupportedThemes,
    val allowBiometrics: Boolean,
    val allowScreenshots: Boolean
)