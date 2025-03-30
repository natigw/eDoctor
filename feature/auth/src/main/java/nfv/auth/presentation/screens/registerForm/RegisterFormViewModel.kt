package nfv.auth.presentation.screens.registerForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
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
            continueButtonState = ButtonState.DISABLED
        )
    )

    fun handleEvent(event: RegisterFormEvent) {

        var isFormValid = false

        viewModelScope.launch {
            uiState.collectLatest {
                isFormValid = it.fullNameText.isNotBlank() &&
                        it.emailText.isNotBlank() &&
                        it.passwordText.isNotBlank() &&
                        it.confirmPasswordText.isNotBlank() &&
                        it.arePasswordsIncompatible.not() //&&
//                        it.passwordStrength != PasswordStrength.NONE &&
//                        it.passwordStrength != PasswordStrength.WEAK
            }

            uiState.update { old ->
                old.copy(
                    continueButtonState = if (isFormValid) ButtonState.ENABLED else ButtonState.DISABLED
                )
            }
        }

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

                    //TODO -> fieldlerin bos olub olmadigini, passwordlerin eyni oldugunu burda yoxlamaq lazimdi? -> tasklar bunun ucundu???

//                    val isFormValid = uiState.value.fullNameText.isNotBlank() &&
//                            uiState.value.emailText.isNotBlank() &&
//                            uiState.value.passwordText.isNotBlank() &&
//                            uiState.value.confirmPasswordText.isNotBlank() &&
//                            uiState.value.arePasswordsIncompatible.not() &&
//                            uiState.value.passwordStrength != PasswordStrength.NONE &&
//                            uiState.value.passwordStrength != PasswordStrength.WEAK

                    uiState.update { old ->
                        old.copy(
                            continueButtonState = ButtonState.LOADING
                        )
                    }

                    val response = repository.registerWithEmail(
                        email = uiState.value.emailText,
                        password = uiState.value.passwordText
                    )

                    uiState.update { old ->
                        old.copy(
                            continueButtonState = ButtonState.ENABLED
                        )
                    }

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