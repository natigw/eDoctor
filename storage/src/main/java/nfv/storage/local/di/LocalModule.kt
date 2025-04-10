package nfv.storage.local.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nfv.storage.local.data.AppPreferencesStorageImpl
import nfv.storage.local.data.UserPreferencesStorageImpl
import nfv.storage.local.domain.AppPreferencesStorage
import nfv.storage.local.domain.UserPreferencesStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {

    @Binds
    @Singleton
    abstract fun bindAppPreferencesStorage(
        impl: AppPreferencesStorageImpl
    ): AppPreferencesStorage

    @Binds
    @Singleton
    abstract fun bindUserPreferencesStorage(
        impl: UserPreferencesStorageImpl
    ): UserPreferencesStorage
}
