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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import nfv.storage.local.domain.TokenProvider
import nfv.storage.local.domain.UserPreferencesStorage
import nfv.storage.local.model.SupportedLanguages
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideKtorClient(
        tokenProvider: TokenProvider,
        userPreferencesStorage: UserPreferencesStorage
    ): HttpClient {

        var currentLanguage = SupportedLanguages.ENGLISH

        CoroutineScope(Dispatchers.Default).launch {
            userPreferencesStorage.getCurrentLanguage().collectLatest {
                currentLanguage = it
            }
        }

        val client = HttpClient(OkHttp) {

            engine {
                addInterceptor { chain ->

                    val token : String? = runBlocking { tokenProvider.getToken() }
                    val requestBuilder = chain.request().newBuilder()

                    token?.let {
                        requestBuilder.addHeader(
                            name = HttpHeaders.Authorization,
                            value = "Bearer $it"
                        )
                    }

                    requestBuilder
                        .addHeader(name = HttpHeaders.AcceptLanguage, value = currentLanguage.locale)
                        .build()
                        .let(chain::proceed)
                }
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
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