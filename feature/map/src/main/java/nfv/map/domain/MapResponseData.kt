package nfv.map.domain

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MapResponseData(
    @SerializedName("iconUrl")
    val iconUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longDescription")
    val longDescription: String,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("pharmacyName")
    val pharmacyName: String,
    @SerializedName("shortDescription")
    val shortDescription: String,
    @SerializedName("website")
    val website: String
)