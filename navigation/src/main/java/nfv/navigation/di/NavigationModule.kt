package nfv.navigation.di

import androidx.navigation.NavController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @Provides
    @Singleton
    fun provideNavigator() : Navigator {
        return Navigator()
    }
}

class Navigator {

    private val navigationChannel = Channel<NavController.()->Unit>()

    suspend fun command(command: NavController.()->Unit) {
        navigationChannel.send(command)
    }

    fun observe(scope: CoroutineScope, onCommand: (NavController.()->Unit)->Unit) {
        scope.launch {
            for (command in navigationChannel) {
                onCommand(command)
            }
        }
    }
}