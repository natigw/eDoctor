package nfv.storage.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalRepository(
    private val dataStore: DataStore<Preferences>
) {

    private companion object {
        val USERNAME = stringPreferencesKey("username")
        val ONBOARD_COMPLETED = booleanPreferencesKey("onboard_completed")
    }

    val getUsername : Flow<String?> = dataStore.data.map { preferences ->
        preferences[USERNAME]
    }

    suspend fun updateUsername(username: String) {
        dataStore.edit { preferences ->
            preferences[USERNAME] = username
        }
    }

    val isOnboardCompleted : Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[ONBOARD_COMPLETED] ?: false
    }

    suspend fun completeOnBoard() {
        dataStore.edit { preferences ->
            preferences[ONBOARD_COMPLETED] = true
        }
    }
}