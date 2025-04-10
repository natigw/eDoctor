package nfv.profile.presentation.screens.profile

import android.net.Uri
import nfv.storage.local.model.SupportedLanguages
import nfv.storage.local.model.SupportedThemes

data class ProfileState(
    val userFullName: String,
    val userProfilePicture: Uri?,
    val currentLanguage: SupportedLanguages,
    val currentTheme: SupportedThemes,
    val allowBiometrics: Boolean,
    val allowScreenshots: Boolean
)