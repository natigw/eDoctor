package nfv.auth.presentation.screens.registerForm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.auth.domain.usecase.EmailValidatorUseCase
import nfv.auth.domain.usecase.PasswordStrengthCheckerUseCase
import nfv.navigation.di.Navigator
import nfv.navigation.routes.LoginRoute
import nfv.navigation.routes.RegisterFormMedicalRoute
import nfv.storage.local.domain.AppPreferencesStorage
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.inputFields.PasswordStrength
import javax.inject.Inject

@HiltViewModel
class RegisterFormViewModel @Inject constructor(
    private val emailValidatorUseCase: EmailValidatorUseCase,
    private val passwordCheckerUseCase: PasswordStrengthCheckerUseCase,
    private val appPreferencesStorage: AppPreferencesStorage,
    private val navigator: Navigator,
) : ViewModel() {

    val uiState = MutableStateFlow(
        RegisterFormState(
            fullNameText = "",
            emailText = "",
            emailHelperText = null,
            passwordText = "",
            confirmPasswordText = "",
            passwordStrength = PasswordStrength.NONE,
            arePasswordsIncompatible = false,
            continueButtonState = ButtonState.DISABLED
        )
    )

    fun handleEvent(event: RegisterFormEvent) {

        var isFormValid: Boolean

        viewModelScope.launch {
            uiState.collectLatest {
                isFormValid = it.fullNameText.isNotBlank() &&
                        it.emailText.isNotBlank() &&
//                        emailValidatorUseCase.execute(it.emailText) &&
                        it.passwordText.isNotBlank() &&
                        it.confirmPasswordText.isNotBlank() &&
                        it.arePasswordsIncompatible.not() &&
                        it.passwordStrength != PasswordStrength.NONE &&
                        it.passwordStrength != PasswordStrength.WEAK
                uiState.update { old ->
                    old.copy(
                        continueButtonState = if (isFormValid) ButtonState.ENABLED else ButtonState.DISABLED
                    )
                }
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
                        passwordStrength = passwordCheckerUseCase.execute(event.newValue),
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

                if (emailValidatorUseCase.execute(uiState.value.emailText).not()) {
                    uiState.update {
                        it.copy(
                            emailHelperText = "*invalid email"
                        )
                    }
                    return
                } else
                    uiState.update {
                        it.copy(
                            emailHelperText = null
                        )
                    }

                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            continueButtonState = ButtonState.LOADING
                        )
                    }

                    appPreferencesStorage.setUserFullName(fullName = uiState.value.fullNameText)
                    appPreferencesStorage.setUsername(
                        username = uiState.value.fullNameText.substringBefore(
                            " "
                        )
                    )
                    navigator.sendCommand {
                        navigate(
                            route = RegisterFormMedicalRoute(
                                uiState.value.emailText,
                                uiState.value.passwordText
                            )
                        )
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