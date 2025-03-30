package nfv.auth.presentation.screens.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.navigation.routes.LoginRoute
import nfv.ui_kit.R
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    val uiState = MutableStateFlow(
        OnBoardState(
            pages = listOf(
                OnBoardPage(
                    image = R.drawable.img_onboard_illustration_1,
                    title = "Welcome",
                    description = "desc1"
                ),
                OnBoardPage(
                    image = R.drawable.img_onboard_illustration_2,
                    title = "2",
                    description = "desc2"
                ),
                OnBoardPage(
                    image = R.drawable.img_onboard_illustration_3,
                    title = "3",
                    description = "desc3"
                ),
            ),
            currentPage = 0
        )
    )

    fun handleEvent(event: OnBoardEvent) {
        when (event) {

            OnBoardEvent.OnNextClicked -> {
                viewModelScope.launch {
                    if (uiState.value.currentPage == uiState.value.pages.size - 1) {
                        navigator.sendCommand {
                            navigate(route = LoginRoute)
                        }
                        uiState.update { old ->
                            old.copy(
                                currentPage = 0
                            )
                        }
                    }
                    else
                        uiState.update { old ->
                            old.copy(
                                currentPage = uiState.value.currentPage + 1
                            )
                        }
                }
            }

            OnBoardEvent.OnSkipClicked -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        navigate(route = LoginRoute)
                    }
                }
            }
        }
    }

}