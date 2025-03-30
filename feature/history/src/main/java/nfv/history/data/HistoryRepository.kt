package nfv.history.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import nfv.history.model.Data
import nfv.history.model.HistoryResponse
import nfv.network.endpoints.HttpRoutes
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    private val client: HttpClient
) {

    suspend fun getTestResults(): List<Data>? {
        return try {
            val response = client.get {
                url(HttpRoutes.TEST_RESULTS)
                contentType(ContentType.Application.Json)
                header(
                    key = "Authorization",
                    value = "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBdXRoZW50aWNhdGlvbiIsImlzcyI6IkVEb2N0b3IiLCJlbWFpbCI6Im5hdGlnQGdtYWlsLmNvbSIsImV4cCI6MTc0MzM4MDU0M30.1M18s5GH61zVzA1aHHH4hMgcH878aqVrf9ymau0PwApKmKWdKWMrlhLueudG3VeexZ2xNC3p7Xma-Ch2UejHeA"
                )
            }.body<HistoryResponse>()

            response.data

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}