package nfv.storage.local.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nfv.storage.local.domain.UserPreferencesStorage
import nfv.storage.local.model.SupportedLanguages
import nfv.storage.local.model.SupportedThemes
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

@Singleton
class UserPreferencesStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : UserPreferencesStorage {

    private companion object {
        val CURRENT_LANGUAGE = stringPreferencesKey("current_language")
        val CURRENT_THEME = stringPreferencesKey("current_theme")
        val ALLOW_BIOMETRICS = booleanPreferencesKey("allow_biometrics")
        val ALLOW_SCREENSHOTS = booleanPreferencesKey("allow_screenshots")
    }


    override suspend fun saveLanguagePreference(language: SupportedLanguages) {
        context.dataStore.edit { prefs ->
            prefs[CURRENT_LANGUAGE] = language.name
        }
    }
    override fun getCurrentLanguage(): Flow<SupportedLanguages> {
        return context.dataStore.data.map { prefs ->
            val languageName = prefs[CURRENT_LANGUAGE] ?: SupportedLanguages.ENGLISH.name
            SupportedLanguages.valueOf(languageName)
        }
    }


    override suspend fun saveThemePreference(theme: SupportedThemes) {
        context.dataStore.edit { prefs ->
            prefs[CURRENT_THEME] = theme.name
        }
    }
    override fun getCurrentTheme(): Flow<SupportedThemes> {
        return context.dataStore.data.map { prefs ->
            val themeName = prefs[CURRENT_THEME] ?: SupportedThemes.LIGHT.name
            SupportedThemes.valueOf(themeName)
        }
    }


    override suspend fun updateBiometricsAllowStatus(allow: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[ALLOW_BIOMETRICS] = allow
        }
    }
    override fun getBiometricsAllowedStatus(): Flow<Boolean> {
        return context.dataStore.data.map { prefs ->
            prefs[ALLOW_BIOMETRICS] ?: true
        }
    }


    override suspend fun updateScreenshotsAllowStatus(allow: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[ALLOW_SCREENSHOTS] = allow
        }
    }
    override fun getScreenshotsAllowedStatus(): Flow<Boolean> {
        return context.dataStore.data.map { prefs ->
            prefs[ALLOW_SCREENSHOTS] ?: false
        }
    }
}