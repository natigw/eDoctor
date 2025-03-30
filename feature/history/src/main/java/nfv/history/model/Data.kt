package nfv.history.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerializedName("id")
    val id: String,
    @SerializedName("isRead")
    val isRead: Boolean,
    @SerializedName("labName")
    val labName: String,
    @SerializedName("testDateMillis")
    val testDateMillis: Long,
    @SerializedName("testDescription")
    val testDescription: String,
    @SerializedName("testFileUrl")
    val testFileUrl: String,
    @SerializedName("testTitle")
    val testTitle: String
)