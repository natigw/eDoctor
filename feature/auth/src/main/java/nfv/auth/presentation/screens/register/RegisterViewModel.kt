package nfv.auth.presentation.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import nfv.auth.presentation.screens.onboard.OnBoardEvent
import nfv.navigation.di.Navigator
import nfv.navigation.routes.RegisterFormRoute
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    val uiState = MutableStateFlow(
        RegisterState(

        )
    )

    fun handleEvent(event: RegisterEvent) {
        when(event) {

            RegisterEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    navigator.command {
                        popBackStack()
                    }
                }
            }

            RegisterEvent.OnContinueClicked -> {
                viewModelScope.launch {
                    navigator.command {
                        navigate(RegisterFormRoute)
                    }
                }
            }
        }
    }

}