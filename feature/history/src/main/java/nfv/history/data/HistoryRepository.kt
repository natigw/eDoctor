package nfv.history.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
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
            }.body<HistoryResponse>()

            response.data

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}