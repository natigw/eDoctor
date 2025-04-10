package nfv.auth.presentation.screens.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.navigation.routes.HomeRoute
import nfv.navigation.routes.LockRoute
import nfv.navigation.routes.LoginRoute
import nfv.storage.local.domain.AppPreferencesStorage
import nfv.ui_kit.R
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(
    private val navigator: Navigator,
    private val appPreferencesStorage: AppPreferencesStorage
) : ViewModel() {

    val uiState = MutableStateFlow(
        OnBoardState(
            pages = listOf(
                OnBoardPage(
                    image = R.drawable.img_onboard_illustration_1,
                    title = "Welcome!",
                    description = "Get instant medical advice anytime with our intelligent AI-powered consultation system.\nLet's get started!"
                ),
                OnBoardPage(
                    image = R.drawable.img_onboard_illustration_2,
                    title = "Interactive map",
                    description = "Whether it's an emergency or a routine check-up, help is just around the corner."
                ),
                OnBoardPage(
                    image = R.drawable.img_onboard_illustration_3,
                    title = "Keep medical records",
                    description = "Access and manage your past medical tests, prescriptions, and doctor visits all in one secure place."
                ),
            ),
            currentPage = 0
        )
    )

    init {
        viewModelScope.launch {
            appPreferencesStorage.isLoggedIn().collectLatest { isLoggedIn ->
                appPreferencesStorage.getPasscode().collectLatest { passcode ->
                    if (isLoggedIn) {
                        if (passcode == null) {
                            navigator.sendCommand {
                                navigate(route = HomeRoute)
                            }
                        } else {
                            navigator.sendCommand {
                                navigate(route = LockRoute)
                            }
                        }
                    } else {
                        navigator.sendCommand {
                            navigate(route = LoginRoute)
                        }
                    }
                }
            }
            appPreferencesStorage.isOnBoardCompleted().collectLatest {
                if (it)
                    navigator.sendCommand {
                        navigate(route = LoginRoute)
                    }
            }
        }
    }

    fun handleEvent(event: OnBoardEvent) {
        when (event) {

            OnBoardEvent.OnNextClicked -> {
                viewModelScope.launch {
                    if (uiState.value.currentPage == uiState.value.pages.size - 1) {

                        appPreferencesStorage.setOnBoardCompleted(true)

                        navigator.sendCommand {
                            navigate(route = LoginRoute)
                        }

                        uiState.update { old ->
                            old.copy(
                                currentPage = 0
                            )
                        }
                    } else
                        uiState.update { old ->
                            old.copy(
                                currentPage = uiState.value.currentPage + 1
                            )
                        }
                }
            }

            OnBoardEvent.OnSkipClicked -> {
                viewModelScope.launch {

                    appPreferencesStorage.setOnBoardCompleted(true)

                    navigator.sendCommand {
                        navigate(route = LoginRoute)
                    }
                }
            }
        }
    }

}