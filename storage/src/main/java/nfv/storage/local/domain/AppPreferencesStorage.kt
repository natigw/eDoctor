package nfv.storage.local.domain

import kotlinx.coroutines.flow.Flow

interface AppPreferencesStorage {

    suspend fun setOnBoardCompleted(completed: Boolean)
    fun isOnBoardCompleted(): Flow<Boolean>

    suspend fun setUserFullName(fullName: String)
    fun getUserFullName(): Flow<String?>

    suspend fun updateLoggedInStatus(isLoggedIn: Boolean)
    fun isLoggedIn(): Flow<Boolean>

}
