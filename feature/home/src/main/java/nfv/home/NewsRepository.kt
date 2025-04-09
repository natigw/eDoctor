package nfv.home

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

    suspend fun getNews(): List<NewsResponse>? {

        return try {
            client.get {
                url(HttpRoutes.NEWS)
                contentType(ContentType.Application.Json)
                header(
                    "Authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBdXRoZW50aWNhdGlvbiIsImlzcyI6IkVEb2N0b3IiLCJlbWFpbCI6Im5hdGlnQGdtYWlsLmNvbSIsImV4cCI6MTc0NDE2OTU4NX0.kGLBRjwoIZWASBi9fNY9Z2BRhkFvjzr9_gc3wwKgabxZJkVHM9G3mYStO6INgs3Wq4MKkT7tKt3fXfhcmnCj0g"
                )
            }.body<List<NewsResponse>>()

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}