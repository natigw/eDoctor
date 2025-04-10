package nfv.profile.presentation.screens.changePasscode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.storage.local.domain.AppPreferencesStorage
import nfv.ui_kit.theme.Typography900
import javax.inject.Inject

@HiltViewModel
class ChangePasscodeViewModel @Inject constructor(
    private val navigator: Navigator,
    private val appPreferencesStorage: AppPreferencesStorage
) : ViewModel() {

    val uiState = MutableStateFlow(
        ChangePasscodeState(
            directionText = "Set a new passcode", //TODO -> string resource
            directionTextColor = Typography900,
            pinList = emptyList(),
            newPinCode = null
        )
    )

    fun handleEvent(event: ChangePasscodeEvent) {

        when (event) {

            is ChangePasscodeEvent.OnDirectionTextUpdated -> {
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            directionText = event.newText
                        )
                    }
                }
            }

            is ChangePasscodeEvent.OnDirectionTextColorUpdated -> {
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            directionTextColor = event.newColor
                        )
                    }
                }
            }

            is ChangePasscodeEvent.OnPinListChanged -> {
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            pinList = event.newValue
                        )
                    }
                }
            }

            is ChangePasscodeEvent.OnNewPinCodeUpdated -> {
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            newPinCode = event.newPin
                        )
                    }
                }
            }

            is ChangePasscodeEvent.OnPinCodeSet -> {
                viewModelScope.launch {
                    appPreferencesStorage.updatePasscode(event.newPin)
                    navigator.sendCommand {
                        popBackStack()
                    }
                }
            }

            ChangePasscodeEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        popBackStack()
                    }
                }
            }
        }
    }
}