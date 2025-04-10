package nfv.storage.local.domain

import kotlinx.coroutines.flow.Flow

interface OnBoardStorage {

    suspend fun setCompleted(completed: Boolean)

    fun isCompleted(): Flow<Boolean>
}
