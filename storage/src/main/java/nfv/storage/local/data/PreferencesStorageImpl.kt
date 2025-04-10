package nfv.storage.local.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nfv.storage.local.domain.PreferencesStorage
import nfv.storage.local.model.SupportedLanguages
import nfv.storage.local.model.SupportedThemes
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

@Singleton
class PreferencesStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PreferencesStorage {

    private val CURRENT_LANGUAGE = stringPreferencesKey("current_language")

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


    private val CURRENT_THEME = stringPreferencesKey("current_theme")

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
}