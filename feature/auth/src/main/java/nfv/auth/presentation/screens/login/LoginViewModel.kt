package nfv.auth.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.auth.domain.repository.AuthRepository
import nfv.navigation.di.Navigator
import nfv.navigation.routes.HomeRoute
import nfv.navigation.routes.LockRoute
import nfv.navigation.routes.RegisterFormRoute
import nfv.storage.local.domain.AppPreferencesStorage
import nfv.ui_kit.components.buttons.model.ButtonState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator,
    private val repository: AuthRepository,
    private val appPreferencesStorage: AppPreferencesStorage
) : ViewModel() {

    val uiState = MutableStateFlow(
        LoginState(
            emailText = "",
            passwordText = "",
            loginButtonState = ButtonState.DISABLED
        )
    )

    var passcode: List<Int>? = emptyList()
    init {
        viewModelScope.launch {
            appPreferencesStorage.getPasscode().collect {
                passcode = it
            }
        }
    }

    fun handleEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.OnEmailTextChanged -> {
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            emailText = event.newValue
                        )
                    }
                }
            }

            is LoginEvent.OnPasswordTextChanged -> {
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            passwordText = event.newValue
                        )
                    }
                }
            }

            LoginEvent.OnForgotPasswordClicked -> {
                //
            }

            is LoginEvent.OnLoginButtonClicked -> {
                viewModelScope.launch {

                    uiState.update { old ->
                        old.copy(
                            loginButtonState = ButtonState.LOADING
                        )
                    }

                    val response = repository.loginWithEmail(
                        email = uiState.value.emailText,
                        password = uiState.value.passwordText
                    )

                    uiState.update { old ->
                        old.copy(
                            loginButtonState = ButtonState.ENABLED
                        )
                    }

                    if (response != null) {
                        appPreferencesStorage.updateLoggedInStatus(true)

                        navigator.sendCommand {
                            navigate(route = if (passcode == null) HomeRoute else LockRoute)
                        }
                    }
                }
            }

            LoginEvent.OnLoginWithGoogleButtonClicked -> {
                viewModelScope.launch {
                    //request
                    //navigate
                }
            }

            LoginEvent.OnLoginWithFacebookButtonClicked -> {
                viewModelScope.launch {
                    //request
                    //navigate
                }
            }

            LoginEvent.OnNavigateBack -> {
                viewModelScope.launch {

                    navigator.sendCommand {
                        this.popBackStack()
                    }
                }
            }

            LoginEvent.GoToRegister -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        navigate(route = RegisterFormRoute)
                    }
                }
            }

            is LoginEvent.OnLoginButtonStateUpdated -> {
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            loginButtonState = event.newButtonState
                        )
                    }
                }
            }
        }
    }

}