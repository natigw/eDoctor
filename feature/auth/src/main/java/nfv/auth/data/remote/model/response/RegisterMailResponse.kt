package nfv.auth.data.remote.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterMailResponse(
    @SerializedName("data")
    val data: String,
    @SerializedName("message")
    val message: String
)