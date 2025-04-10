package nfv.auth.presentation.screens.registerForm

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.auth.domain.repository.AuthRepository
import nfv.navigation.di.Navigator
import nfv.navigation.routes.HomeRoute
import nfv.navigation.routes.LoginRoute
import nfv.navigation.routes.OnBoardRoute
import nfv.navigation.routes.RegisterFormMedicalRoute
import nfv.storage.local.data.AppPreferencesStorageImpl
import nfv.storage.local.domain.AppPreferencesStorage
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.inputFields.PasswordStrength
import javax.inject.Inject

@HiltViewModel
class RegisterFormViewModel @Inject constructor(
    private val appPreferencesStorage: AppPreferencesStorage,
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

    @SuppressLint("SuspiciousIndentation")
    fun handleEvent(event: RegisterFormEvent) {

        var isFormValid: Boolean

        viewModelScope.launch {
            uiState.collectLatest {
                isFormValid = it.fullNameText.isNotBlank() &&
                        it.emailText.isNotBlank() &&
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
                        passwordStrength = checkPasswordStrength(event.newValue),
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

                    val response = repository.registerWithEmail(
                        email = uiState.value.emailText,
                        password = uiState.value.passwordText
                    )

                    Log.d("salam", response.toString())

                    uiState.update { old ->
                        old.copy(
                            continueButtonState = ButtonState.ENABLED
                        )
                    }

                    if (response != null)
                        appPreferencesStorage.setUserFullName(fullName = uiState.value.fullNameText)
                        appPreferencesStorage.setUsername(username = uiState.value.fullNameText.substringBefore(" "))
                        navigator.sendCommand {
                            navigate(route = RegisterFormMedicalRoute(uiState.value.emailText))
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

    private fun checkPasswordStrength(password: String): PasswordStrength {
        val passwordLength = password.length
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }

        return if (password.isBlank() || passwordLength < 4)
            PasswordStrength.NONE
        else if (passwordLength < 6 || !hasLowerCase || !hasUpperCase || !hasDigit) {
            PasswordStrength.WEAK
        } else if (!hasSpecialChar) {
            PasswordStrength.MEDIUM
        } else PasswordStrength.STRONG
    }
}