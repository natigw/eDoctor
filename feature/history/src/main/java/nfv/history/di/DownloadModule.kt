package nfv.history.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nfv.history.download.AndroidDownloader
import nfv.history.download.Downloader
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DownloadModule {

    @Binds
    @Singleton
    abstract fun bindDownloader(
        androidDownloader: AndroidDownloader
    ): Downloader
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAndroidDownloader(@ApplicationContext context: Context): AndroidDownloader {
        return AndroidDownloader(context)
    }
}