package nfv.auth.data.repository

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import nfv.auth.data.model.request.RegisterMailRequest
import nfv.auth.data.model.response.RegisterMailResponse
import nfv.auth.domain.model.RegisterMailModel
import nfv.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val client: HttpClient
) : AuthRepository {

    override suspend fun registerWithEmail(email: String, password: String): RegisterMailModel? {

        return try {
            val response = client.post {
                url("http://192.168.2.6:8080/register/mail")
                contentType(ContentType.Application.Json)
                setBody(RegisterMailRequest(email, password))
            }.body<RegisterMailResponse>()  // Deserialize response automatically

            // Convert RegisterMailResponse to RegisterMailModel and return
            RegisterMailModel(response.data)

        } catch (e: RedirectResponseException) {
            // 3xx responses
            Log.d("registerMail", "Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx responses
            Log.d("registerMail", "Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx responses
            Log.d("registerMail", "Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            // other exceptions
            Log.d("registerMail", "Error: ${e.message}")
            e.printStackTrace()
            null
        }
    }

}