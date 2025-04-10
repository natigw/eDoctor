package nfv.storage.local.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nfv.storage.local.domain.OnBoardingStorage
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "app_preferences")

@Singleton
class OnBoardingStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : OnBoardingStorage {

    private val ONBOARD_COMPLETED = booleanPreferencesKey("on_board_completed")

    override suspend fun setCompleted(completed: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[ONBOARD_COMPLETED] = completed
        }
    }

    override fun isCompleted(): Flow<Boolean> {
        return context.dataStore.data.map { prefs ->
            prefs[ONBOARD_COMPLETED] ?: false
        }
    }
}
