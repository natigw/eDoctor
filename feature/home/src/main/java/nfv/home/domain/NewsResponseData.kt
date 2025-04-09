package nfv.home.domain

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseData(
    @SerializedName("extras")
    val extras: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("imageLink")
    val imageLink: String,
    @SerializedName("longDescription")
    val longDescription: String,
    @SerializedName("readMoreLink")
    val readMoreLink: String,
    @SerializedName("shortDescription")
    val shortDescription: String,
    @SerializedName("title")
    val title: String
)