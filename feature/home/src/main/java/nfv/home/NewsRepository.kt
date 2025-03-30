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
                    "Authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBdXRoZW50aWNhdGlvbiIsImlzcyI6IkVEb2N0b3IiLCJlbWFpbCI6Im5hdGlnQGdtYWlsLmNvbSIsImV4cCI6MTc0MzMxOTE4MH0.PjbfiwCaDWlju186Jg-9g7Qgo29jjDbeaynJebhNpa7vP_cuCfsRswf-Iou10LQdDKMu_cMrlbGBNu4Wxawr9w"
                )
            }.body<List<NewsResponse>>()

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}