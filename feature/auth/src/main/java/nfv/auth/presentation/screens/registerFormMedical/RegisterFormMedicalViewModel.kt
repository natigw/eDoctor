package nfv.auth.presentation.screens.registerFormMedical

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.ui_kit.components.buttons.model.ButtonState
import javax.inject.Inject

@HiltViewModel
class RegisterFormMedicalViewModel @Inject constructor(
    private val navigator: Navigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val userFullName = savedStateHandle.get<String>("userFullName") ?: ""

    val uiState = MutableStateFlow(
        RegisterFormMedicalState(
            fullNameText = userFullName,
            bloodType = null,
            gender = null,
            weight = null,
            birthDate = null,
            continueButtonState = ButtonState.DISABLED
        )
    )

    fun handleEvent(event: RegisterFormMedicalEvent) {

        var isFormValid: Boolean

        viewModelScope.launch {
            uiState.collectLatest {
                isFormValid =
                    it.bloodType != null && it.gender != null && it.weight != null && it.birthDate != null
                uiState.update { old ->
                    old.copy(
                        continueButtonState = if (isFormValid) ButtonState.ENABLED else ButtonState.DISABLED
                    )
                }
            }
        }

        when (event) {

            is RegisterFormMedicalEvent.OnBloodTypeChanged -> {
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            bloodType = event.newValue
                        )
                    }
                }
            }

            is RegisterFormMedicalEvent.OnGenderChanged -> {
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            gender = event.newValue
                        )
                    }
                }
            }

            is RegisterFormMedicalEvent.OnWeightChanged -> {
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            weight = event.newValue
                        )
                    }
                }
            }

            is RegisterFormMedicalEvent.OnBirthDateChanged -> {
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            birthDate = event.newValue
                        )
                    }
                }
            }

            RegisterFormMedicalEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        popBackStack()
                    }
                }
            }

            RegisterFormMedicalEvent.OnRegisterClicked -> {
                viewModelScope.launch {
                    navigator.sendCommand {
//                        navigate()
                    }
                }
            }

            RegisterFormMedicalEvent.GoToLoginScreen -> {
                viewModelScope.launch {
                    navigator.sendCommand {
//                        navigate()
                    }
                }
            }
        }
    }
}