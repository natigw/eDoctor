package nfv.home.domain

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    @SerializedName("data")
    val `data`: List<NewsResponseData>,
    @SerializedName("message")
    val message: String
)