package nfv.profile.presentation.screens.profile

import nfv.profile.presentation.screens.changeLanguage.SupportedLanguages
import nfv.profile.presentation.screens.changeTheme.SupportedThemes

data class ProfileState(
    val userFullName: String,
    val profileLink: String,
    val currentLanguage: SupportedLanguages,
    val currentTheme: SupportedThemes
)