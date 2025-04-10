package nfv.storage.local.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nfv.storage.local.data.OnBoardStorageImpl
import nfv.storage.local.data.PreferencesStorageImpl
import nfv.storage.local.domain.OnBoardStorage
import nfv.storage.local.domain.PreferencesStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {

    @Binds
    @Singleton
    abstract fun bindOnBoardStorage(
        impl: OnBoardStorageImpl
    ): OnBoardStorage

    @Binds
    @Singleton
    abstract fun bindPreferencesStorage(
        impl: PreferencesStorageImpl
    ): PreferencesStorage
}
