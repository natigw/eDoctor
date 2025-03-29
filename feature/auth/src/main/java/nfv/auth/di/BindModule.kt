package nfv.auth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nfv.auth.data.repository.AuthRepositoryImpl
import nfv.auth.domain.repository.AuthRepository

//@Module
//@InstallIn(SingletonComponent::class)
//class BindModule {
//
//    @Provides
//    @Singleton
//    fun provideAuthRepository(client: HttpClient): AuthRepository {
//        return AuthRepositoryImpl(client)
//    }
//
//}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Binds
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

}