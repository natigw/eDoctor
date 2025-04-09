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
                header(
                    "Authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBdXRoZW50aWNhdGlvbiIsImlzcyI6IkVEb2N0b3IiLCJlbWFpbCI6Im5hdGlnQGdtYWlsLmNvbSIsImV4cCI6MTc0NDIzODgwMH0.hgWQ9vS1uheV6XhK79tHCHheXxhHUJVqmu_TyECylMJsSf3d09njMCMFzjFe_IRqigqDJ8vgMwnUfxlmBIQ_Kw"
                )
                header(
                    "Accept-Language", "en"
                )
            }.body<ConsultationResponse>()

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}