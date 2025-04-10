package nfv.consultation.domain

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import nfv.network.endpoints.HttpRoutes
import javax.inject.Inject

class ConsultationRepository @Inject constructor(
    private val client: HttpClient
) {

    suspend fun consultToAi(questionBody: String): ConsultationResponse? {

        return try {
            client.post {
                url(HttpRoutes.CONSULTATION)
                contentType(ContentType.Text.Plain)
                setBody(questionBody)
            }.body<ConsultationResponse>()

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}