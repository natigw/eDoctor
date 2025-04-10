package nfv.storage.local.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nfv.storage.local.domain.TokenStorage
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "token_preferences")

class TokenStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : TokenStorage {


    private companion object {
        val TOKEN_KEY = stringPreferencesKey("auth_token")
    }


    override suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
        }
    }

    override fun getToken(): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[TOKEN_KEY]
        }
    }

    override suspend fun clearToken() {
        context.dataStore.edit { prefs ->
            prefs.remove(TOKEN_KEY)
        }
    }
}
