package nfv.history.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class HistoryResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("message")
    val message: String
)