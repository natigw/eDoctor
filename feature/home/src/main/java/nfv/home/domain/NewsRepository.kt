package nfv.home.domain

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import nfv.network.endpoints.HttpRoutes
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val client: HttpClient
) {

    suspend fun getNews(): NewsResponse? {

        return try {
            client.get {
                url(HttpRoutes.NEWS)
                contentType(ContentType.Application.Json)
                header(
                    "Authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBdXRoZW50aWNhdGlvbiIsImlzcyI6IkVEb2N0b3IiLCJlbWFpbCI6Im5hdGlnQGdtYWlsLmNvbSIsImV4cCI6MTc0NDE3NzM2NH0.ZqdGWpU4pNHzcDB1oQ4Ic9vtYObXbXN10rj14Av468UyS4vs15vVFAabD_zgKmlQ_GYrI8soV7c9UNdPLwIkSQ"
                )
            }.body<NewsResponse>()

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}