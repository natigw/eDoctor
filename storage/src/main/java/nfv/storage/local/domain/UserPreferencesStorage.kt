package nfv.storage.local.domain

import kotlinx.coroutines.flow.Flow
import nfv.storage.local.model.SupportedLanguages
import nfv.storage.local.model.SupportedThemes

interface UserPreferencesStorage {

    suspend fun saveLanguagePreference(language: SupportedLanguages)
    fun getCurrentLanguage(): Flow<SupportedLanguages>

    suspend fun saveThemePreference(theme: SupportedThemes)
    fun getCurrentTheme(): Flow<SupportedThemes>

    suspend fun updateBiometricsAllowStatus(allow: Boolean)
    fun getBiometricsAllowedStatus(): Flow<Boolean>

    suspend fun updateScreenshotsAllowStatus(allow: Boolean)
    fun getScreenshotsAllowedStatus(): Flow<Boolean>
}