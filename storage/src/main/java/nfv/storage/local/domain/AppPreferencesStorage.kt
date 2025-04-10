package nfv.storage.local.domain

import android.net.Uri
import kotlinx.coroutines.flow.Flow

interface AppPreferencesStorage {

    suspend fun setOnBoardCompleted(completed: Boolean)
    fun isOnBoardCompleted(): Flow<Boolean>

    suspend fun setUserFullName(fullName: String)
    fun getUserFullName(): Flow<String?>

    suspend fun setUsername(username: String)
    fun getUsername(): Flow<String?>

    suspend fun updateLoggedInStatus(isLoggedIn: Boolean)
    fun isLoggedIn(): Flow<Boolean>

    suspend fun updateProfilePicture(image: Uri)
    fun getProfilePicture(): Flow<Uri?>

    suspend fun updatePasscode(passcode: List<Int>)
    fun getPasscode(): Flow<List<Int>?>

}
