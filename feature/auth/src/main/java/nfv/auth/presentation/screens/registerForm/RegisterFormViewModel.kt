package nfv.auth.presentation.screens.registerForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.inputFields.PasswordStrength
import javax.inject.Inject

@HiltViewModel
class RegisterFormViewModel @Inject constructor(
    private val navigator: Navigator
): ViewModel() {

    val uiState = MutableStateFlow(
        RegisterFormState(
            fullNameText = "",
            emailText = "",
            passwordText = "",
            confirmPasswordText = "",
            passwordStrength = PasswordStrength.NONE,
            continueButtonState = ButtonState.DISABLED
        )
    )

    fun handleEvent(event: RegisterFormEvent) {
        when(event) {
            is RegisterFormEvent.OnFullNameChanged -> {
                uiState.update { old ->
                    old.copy(
                        fullNameText = event.newValue
                    )
                }
            }

            is RegisterFormEvent.OnEmailChanged -> {
                uiState.update { old ->
                    old.copy(
                        emailText = event.newValue
                    )
                }
            }

            is RegisterFormEvent.OnPasswordChanged -> {
                uiState.update { old ->
                    old.copy(
                        passwordText = event.newValue
                    )
                }
            }

            is RegisterFormEvent.OnConfirmPasswordChanged -> {
                uiState.update { old ->
                    old.copy(
                        confirmPasswordText = event.newValue
                    )
                }
            }

            is RegisterFormEvent.OnClickContinue -> {
                viewModelScope.launch {
                    //navigate
                }
            }

            RegisterFormEvent.GoToLoginScreen -> {
                viewModelScope.launch {
                    //navigate
                }
            }

            RegisterFormEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    navigator.command {
                        this.popBackStack()
                    }
                }
            }
        }
    }
}