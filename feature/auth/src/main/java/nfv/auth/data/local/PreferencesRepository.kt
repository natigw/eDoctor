package nfv.auth.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {

    private companion object {
        val LANGUAGE_PREFERENCE = intPreferencesKey("language")
    }

    val currentLanguage : Flow<Int> = dataStore.data.map { preferences ->
        preferences[LANGUAGE_PREFERENCE] ?: SupportedLanguages.ENGLISH.ordinal
    }

    suspend fun saveLanguage(language: SupportedLanguages) {
        dataStore.edit { preferences ->
            preferences[LANGUAGE_PREFERENCE] = language.ordinal
        }
    }

}

enum class SupportedLanguages(val inNative: String) {
    ENGLISH("English"),          //0
    AZERBAIJANI("Azərbaycanca"), //1
    RUSSIAN("Русский")           //2
}