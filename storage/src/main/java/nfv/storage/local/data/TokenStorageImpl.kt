package nfv.storage.local.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nfv.storage.local.domain.TokenStorage
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

private object PreferenceKeys {
    val TOKEN_KEY = stringPreferencesKey("auth_token")
}

class TokenStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : TokenStorage {

    override suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[PreferenceKeys.TOKEN_KEY] = token
        }
    }

    override fun getToken(): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[PreferenceKeys.TOKEN_KEY]
        }
    }

    override suspend fun clearToken() {
        context.dataStore.edit { prefs ->
            prefs.remove(PreferenceKeys.TOKEN_KEY)
        }
    }
}
