package nfv.auth.data.remote.model.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LoginMailRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)