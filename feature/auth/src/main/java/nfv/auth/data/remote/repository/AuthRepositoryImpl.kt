package nfv.auth.data.remote.repository

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
import nfv.auth.data.remote.model.request.LoginMailRequest
import nfv.auth.data.remote.model.request.RegisterMailRequest
import nfv.auth.data.remote.model.response.LoginMailResponse
import nfv.auth.data.remote.model.response.RegisterMailResponse
import nfv.auth.domain.model.LoginMailModel
import nfv.auth.domain.model.RegisterMailModel
import nfv.auth.domain.repository.AuthRepository
import nfv.network.endpoints.HttpRoutes
import nfv.storage.local.domain.TokenStorage
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val client: HttpClient,
    private val tokenStorage: TokenStorage
) : AuthRepository {

    override suspend fun registerWithEmail(email: String, password: String): RegisterMailModel? {

        return try {
            val response = client.post {
                url(HttpRoutes.REGISTER_MAIL)
                contentType(ContentType.Application.Json)
                setBody(
                    RegisterMailRequest(
                        email = email,
                        password = password
                    )
                )
            }.body<RegisterMailResponse>()  // Deserialize response automatically

            // Save token to DataStore
            tokenStorage.saveToken(response.data)

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


    override suspend fun loginWithEmail(email: String, password: String): LoginMailModel? {

        return try {
            val response = client.post {
                url(HttpRoutes.LOGIN_MAIL)
                contentType(ContentType.Application.Json)
                setBody(
                    LoginMailRequest(
                        email = email,
                        password = password
                    )
                )
            }.body<LoginMailResponse>()

            tokenStorage.saveToken(response.data)

            LoginMailModel(response.data)

        } catch (e: RedirectResponseException) {
            // 3xx responses
            Log.d("loginMail", "Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx responses
            Log.d("loginMail", "Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx responses
            Log.d("loginMail", "Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            // other exceptions
            Log.d("loginMail", "Error: ${e.message}")
            e.printStackTrace()
            null
        }
    }

}