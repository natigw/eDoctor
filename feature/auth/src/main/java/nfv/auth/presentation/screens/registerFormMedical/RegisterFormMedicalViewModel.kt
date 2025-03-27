package nfv.auth.presentation.screens.registerFormMedical

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.ui_kit.components.buttons.model.ButtonState
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RegisterFormMedicalViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    val uiState = MutableStateFlow(
        RegisterFormMedicalState(
            fullNameText = "",
            bloodType = BloodType.FIRST_NEGATIVE, //buna bir care
            sex = Sex.MALE,  //buna da
            weight = 0.0,
            birthDate = Date(),
            registerButtonState = ButtonState.DISABLED
        )
    )

    fun handleEvent(event: RegisterFormMedicalEvent) {
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

            is RegisterFormMedicalEvent.OnSexChanged -> {
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            sex = event.newValue
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
                    navigator.command {
                        popBackStack()
                    }
                }
            }

            RegisterFormMedicalEvent.OnRegisterClicked -> {
                viewModelScope.launch {
                    navigator.command {
//                        navigate()
                    }
                }
            }

            RegisterFormMedicalEvent.GoToLoginScreen -> {
                viewModelScope.launch {
                    navigator.command {
//                        navigate()
                    }
                }
            }
        }
    }
}