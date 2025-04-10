package nfv.auth.presentation.screens.registerCompletion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.navigation.routes.HomeRoute
import nfv.storage.local.domain.AppPreferencesStorage
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenViewModel @Inject constructor(
    private val appPreferencesStorage: AppPreferencesStorage,
    private val navigator: Navigator,
) : ViewModel() {

    val uiState = MutableStateFlow(
        WelcomeScreenState(
            username = ""
        )
    )

    init {
        viewModelScope.launch {
            appPreferencesStorage.getUsername().collect {
                uiState.update { old ->
                    old.copy(
                        username = it.toString()
                    )
                }
            }
        }
    }


    fun handleEvent(event: WelcomeScreenEvent) {
        when (event) {
            is WelcomeScreenEvent.OnContinueClicked -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        navigate(route = HomeRoute)
                    }
                }
            }
        }
    }
}