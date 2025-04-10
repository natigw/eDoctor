package nfv.auth.presentation.screens.otp

import android.annotation.SuppressLint
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.navigation.routes.RegisterCompletionRoute
import nfv.ui_kit.components.buttons.model.ButtonState
import javax.inject.Inject

@HiltViewModel
class OTPViewModel @Inject constructor(
    private val navigator: Navigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val email = savedStateHandle.get<String>("email") ?: ""
    private var timerJob: Job? = null

    val uiState = MutableStateFlow(
        OTPState(
            otp = "",
            timer = "00:59",
            email = email,
            continueButtonState = ButtonState.DISABLED
        )
    )

    init {
        startTimerFlow()
    }

    fun handleEvent(event: OTPEvent) {

        when (event) {
            is OTPEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        this.popBackStack()
                    }
                }
            }

            is OTPEvent.OnContinueClicked -> {
                if (uiState.value.continueButtonState == ButtonState.ENABLED)
                    viewModelScope.launch {
                        navigator.sendCommand {
                            navigate(route = RegisterCompletionRoute)
                        }
                    }
            }

            is OTPEvent.OnResendClicked -> {
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            timer = "00:59"
                        )
                    }
                    startTimerFlow()
                }
            }

            is OTPEvent.OnOtpChanged -> {
                if (uiState.value.timer == "OTP is expired") return
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            continueButtonState = if (event.otp.length == 6) ButtonState.ENABLED else ButtonState.DISABLED,
                            otp = event.otp.take(6),
                        )
                    }
                }
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private fun startTimerFlow() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            (59 downTo 0).asFlow().collect { seconds ->
                uiState.update { old ->
                    old.copy(timer = String.format("00:%02d", seconds))
                }
                if (seconds == 0) {
                    uiState.update { old ->
                        old.copy(
                            otp = "",
                            timer = "OTP is expired",
                            continueButtonState = ButtonState.DISABLED
                        )
                    }
                }
                delay(1000)
            }
        }
    }
}