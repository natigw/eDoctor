package nfv.map.domain

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MapResponse(
    @SerializedName("data")
    val `data`: List<MapResponseData>,
    @SerializedName("message")
    val message: String
)