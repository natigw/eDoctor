package nfv.auth.presentation.screens.registerForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.navigation.routes.LoginRoute
import nfv.navigation.routes.OnBoardRoute
import nfv.navigation.routes.RegisterFormMedicalRoute
import nfv.navigation.routes.RegisterFormRoute
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
            arePasswordsIncompatible = false,
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
                        passwordText = event.newValue,
                        arePasswordsIncompatible = if (old.confirmPasswordText.isNotBlank() && event.newValue.isNotBlank()) old.confirmPasswordText != event.newValue else false  //TODO -> bunu daha yaxsi usul ele, debouncing qoy
                    )
                }
            }

            is RegisterFormEvent.OnConfirmPasswordChanged -> {
                uiState.update { old ->
                    old.copy(
                        confirmPasswordText = event.newValue,
                        arePasswordsIncompatible = if (old.passwordText.isNotBlank() && event.newValue.isNotBlank()) old.passwordText != event.newValue else false
                    )
                }
            }

            is RegisterFormEvent.OnClickContinue -> {
                viewModelScope.launch {
                    //api request
                    //event.email + event.password

//                    uiState.update {
//                        it.copy(
//                            continueButtonState = ButtonState.LOADING
//                        )
//                    }
//                    delay(3000)                                 //TODO -> emit en axirdadi deye button loading state kecdiyini gormuruk nece fix etmeli?
//                    uiState.update {
//                        it.copy(
//                            continueButtonState = ButtonState.ENABLED
//                        )
//                    }

                    navigator.command {
                        navigate(route = OnBoardRoute)
                    }
                }
            }

            RegisterFormEvent.GoToLoginScreen -> {
                viewModelScope.launch {
                    navigator.command {
                        navigate(route = LoginRoute)
                    }
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