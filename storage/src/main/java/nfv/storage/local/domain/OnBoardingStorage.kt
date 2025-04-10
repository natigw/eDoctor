package nfv.storage.local.domain

import kotlinx.coroutines.flow.Flow

interface OnBoardingStorage {
    suspend fun setCompleted(completed: Boolean)
    fun isCompleted(): Flow<Boolean>
}
