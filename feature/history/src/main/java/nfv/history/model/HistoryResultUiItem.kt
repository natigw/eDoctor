package nfv.history.model

import kotlinx.serialization.Serializable

@Serializable
data class HistoryResultUiItem(
    val id: String,
    val testTitle: String,
    val labName: String,
    val testDate: String,
    val testDescription: String,
    val testFileUrl: String,
    val isRead: Boolean,
)
