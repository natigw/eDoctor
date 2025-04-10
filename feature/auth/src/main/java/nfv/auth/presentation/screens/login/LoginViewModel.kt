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
import nfv.navigation.routes.RegisterFormRoute
import nfv.ui_kit.components.buttons.model.ButtonState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator,
    private val repository: AuthRepository
) : ViewModel() {

    val uiState = MutableStateFlow(
        LoginState(
            emailText = "",
            passwordText = "",
            loginButtonState = ButtonState.DISABLED
        )
    )

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

                    if (response != null)
                        navigator.sendCommand {
                            navigate(route = HomeRoute)
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