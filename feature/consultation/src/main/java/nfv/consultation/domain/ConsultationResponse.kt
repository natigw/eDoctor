package nfv.consultation.domain

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ConsultationResponse(
    @SerializedName("data")
    val `data`: String,
    @SerializedName("message")
    val message: String
)