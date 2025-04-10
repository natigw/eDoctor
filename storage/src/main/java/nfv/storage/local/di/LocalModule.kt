package nfv.storage.local.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nfv.storage.local.data.OnBoardingStorageImpl
import nfv.storage.local.domain.OnBoardingStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {

    @Binds
    @Singleton
    abstract fun bindOnBoardingStorage(
        impl: OnBoardingStorageImpl
    ): OnBoardingStorage
}
