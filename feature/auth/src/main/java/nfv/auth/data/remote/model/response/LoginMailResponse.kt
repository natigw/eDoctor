package nfv.auth.data.remote.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LoginMailResponse(
    @SerializedName("data")
    val data: String,   //TODO -> bu null da gele biler, yeni nullable-dir nece olsun??
    @SerializedName("message")
    val message: String
)