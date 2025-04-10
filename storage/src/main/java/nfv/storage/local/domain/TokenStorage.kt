package nfv.storage.local.domain

import kotlinx.coroutines.flow.Flow

interface TokenStorage {
    suspend fun saveToken(token: String)
    fun getToken(): Flow<String?>
    suspend fun clearToken()
}
