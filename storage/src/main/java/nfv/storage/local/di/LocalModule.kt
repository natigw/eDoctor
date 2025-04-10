package nfv.storage.local.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nfv.storage.local.data.TokenProviderImpl
import nfv.storage.local.data.TokenStorageImpl
import nfv.storage.local.domain.TokenProvider
import nfv.storage.local.domain.TokenStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideTokenStorage(
        @ApplicationContext context: Context
    ): TokenStorage = TokenStorageImpl(context)

    @Provides
    @Singleton
    fun provideTokenProvider(tokenStorage: TokenStorage): TokenProvider {
        return TokenProviderImpl(tokenStorage)
    }
}