package nfv.network.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideKtorClient(): HttpClient {

        val client = HttpClient(OkHttp) {

            engine {
//                addInterceptor { chain ->
//                    val request = chain.request().newBuilder()
//                        .addHeader(HttpHeaders.Authorization, "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJBdXRoZW50aWNhdGlvbiIsImlzcyI6IkVEb2N0b3IiLCJlbWFpbCI6Im5hdGlnQGdtYWlsLmNvbSIsImV4cCI6MTc0MzI3OTUxNX0.2HbD-z-lR9lIE6HC3phywFwcxgnt3xaqjBmy-MVJuOF6xUZStC9GHRQwc66OiffSQXupe8gEHVp5CiiJkR3F6Q")
//                        .addHeader(HttpHeaders.AcceptLanguage, "ru")
//                        .build()
//                    chain.proceed(request)
//                }
//                addInterceptor(HttpLoggingInterceptor().apply {
//                    level = HttpLoggingInterceptor.Level.BODY
//                })
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("KTOR", message)
                    }
                }
                level = LogLevel.ALL
            }
        }

        return client
    }

}