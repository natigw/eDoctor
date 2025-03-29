package nfv.auth.presentation.screens.registerForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.auth.domain.repository.AuthRepository
import nfv.navigation.di.Navigator
import nfv.navigation.routes.LoginRoute
import nfv.navigation.routes.OnBoardRoute
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.inputFields.PasswordStrength
import javax.inject.Inject

@HiltViewModel
class RegisterFormViewModel @Inject constructor(
    private val navigator: Navigator,
    private val repository: AuthRepository
) : ViewModel() {

    val uiState = MutableStateFlow(
        RegisterFormState(
            fullNameText = "",
            emailText = "",
            passwordText = "",
            confirmPasswordText = "",
            passwordStrength = PasswordStrength.NONE,
            arePasswordsIncompatible = false,
            continueButtonState = ButtonState.ENABLED
        )
    )

    fun handleEvent(event: RegisterFormEvent) {
        when (event) {
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

                    uiState.update { old ->
                        old.copy(
                            continueButtonState = ButtonState.LOADING
                        )
                    }

                    //TODO -> fieldlerin bos olub olmadigini, passwordlerin eyni oldugunu burda yoxlamaq lazimdi? -> tasklar bunun ucundu???

                    delay(4000)
                    val response = repository.registerWithEmail(
                        email = uiState.value.emailText,
                        password = uiState.value.passwordText
                    )

//                    uiState.update { old ->
//                        old.copy(
//                            continueButtonState = ButtonState.ENABLED   //TODO -> button niye loading state kecmedi
//                        )
//                    }

                    if (response != null)
                        navigator.sendCommand {
                            navigate(route = OnBoardRoute)
                        }
                }
            }

            RegisterFormEvent.GoToLoginScreen -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        navigate(route = LoginRoute)
                    }
                }
            }

            RegisterFormEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        this.popBackStack()
                    }
                }
            }
        }
    }
}