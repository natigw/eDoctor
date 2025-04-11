package nfv.auth.presentation.screens.lock

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.navigation.routes.HomeRoute
import nfv.storage.local.domain.AppPreferencesStorage
import javax.inject.Inject

@HiltViewModel
class LockViewModel @Inject constructor(
    private val appPreferencesStorage: AppPreferencesStorage,
    private val navigator: Navigator,
) : ViewModel() {

    val uiState = MutableStateFlow(
        LockState(
            username = "",
            pinList = emptyList()
        )
    )

    var userPasscode: List<Int>? = emptyList()

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
        viewModelScope.launch {
            appPreferencesStorage.getPasscode().collect {
                userPasscode = it
            }
        }
    }

    fun handleEvent(event: LockEvent) {
        when (event) {
            is LockEvent.OnPinCodeEntered -> {
                viewModelScope.launch {
                    if (uiState.value.pinList == userPasscode) {
                        delay(500)
                        navigator.sendCommand {
                            navigate(route = HomeRoute)
                        }
                    }
                }
            }

            is LockEvent.OnForgotPinClicked -> {
                viewModelScope.launch {
                    // TODO: Navigate to forgot pin screen
                }
            }

            is LockEvent.OnFingerprintClicked -> {
                viewModelScope.launch {
                    // TODO
                }
            }

            is LockEvent.OnPinCodeChanged -> {
                if (uiState.value.pinList.size <= 4) {
                    viewModelScope.launch {
                        uiState.update { old ->
                            old.copy(
                                pinList = event.pinList
                            )
                        }
                        if (uiState.value.pinList.size == 4) {
                            handleEvent(LockEvent.OnPinCodeEntered)
                        }
                    }
                }
            }
        }
    }
}