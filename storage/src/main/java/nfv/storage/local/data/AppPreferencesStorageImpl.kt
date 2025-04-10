package nfv.storage.local.data

import android.content.Context
import android.net.Uri
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nfv.storage.local.domain.AppPreferencesStorage
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "app_preferences")

@Singleton
class AppPreferencesStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AppPreferencesStorage {


    private companion object {
        val ONBOARD_COMPLETED = booleanPreferencesKey("onboard_completed")
        val LOGGED_IN = booleanPreferencesKey("user_logged_in")
        val USER_FULLNAME = stringPreferencesKey("user_full_name")
        val USERNAME = stringPreferencesKey("username")
        val USER_PROFILE_PICTURE = stringPreferencesKey("user_profile_picture")
        val PASSCODE = stringPreferencesKey("passcode")
    }


    override suspend fun setOnBoardCompleted(completed: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[ONBOARD_COMPLETED] = completed
        }
    }

    override fun isOnBoardCompleted(): Flow<Boolean> {
        return context.dataStore.data.map { prefs ->
            prefs[ONBOARD_COMPLETED] ?: false
        }
    }


    override suspend fun updateLoggedInStatus(isLoggedIn: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[LOGGED_IN] = isLoggedIn
        }
    }

    override fun isLoggedIn(): Flow<Boolean> {
        return context.dataStore.data.map { prefs ->
            prefs[LOGGED_IN] ?: false
        }
    }


    override suspend fun setUserFullName(fullName: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_FULLNAME] = fullName
        }
    }

    override fun getUserFullName(): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[USER_FULLNAME]
        }
    }


    override suspend fun setUsername(username: String) {
        context.dataStore.edit { prefs ->
            prefs[USERNAME] = username
        }
    }

    override fun getUsername(): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[USERNAME]
        }
    }


    override suspend fun updateProfilePicture(image: Uri) {
        context.dataStore.edit { prefs ->
            prefs[USER_PROFILE_PICTURE] = image.toString()
        }
    }

    override fun getProfilePicture(): Flow<Uri?> {
        return context.dataStore.data.map { prefs ->
            prefs[USER_PROFILE_PICTURE]?.let { Uri.parse(it) }
        }
    }


    override suspend fun updatePasscode(passcode: List<Int>) {
        val serialized = passcode.joinToString("")
        context.dataStore.edit { prefs ->
            prefs[PASSCODE] = serialized
        }
    }

    override fun getPasscode(): Flow<List<Int>?> {
        return context.dataStore.data.map { prefs ->
            prefs[PASSCODE]?.split("")?.mapNotNull { it.toIntOrNull() }
        }
    }
}
