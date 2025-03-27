package nfv.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.navigation.routes.HomeRoute
import nfv.navigation.routes.ProfileRoute
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    val uiState = MutableStateFlow(
        HistoryState(
            searchText = ""
        )
    )

    fun handleEvent(event: HistoryEvent) {

        when (event) {

            is HistoryEvent.OnSearchTextChanged -> {
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            searchText = event.newValue
                        )
                    }
                }
            }

            is HistoryEvent.OnSearchTextSearched -> {
                viewModelScope.launch {
                    //search
                }
            }


            HistoryEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    navigator.command {
                        this.popBackStack()
                    }
                }
            }

            HistoryEvent.GoToHistory -> {}

            HistoryEvent.GoToHome -> {
                viewModelScope.launch {
                    navigator.command {
                        navigate(route = HomeRoute) {
                            popUpTo(HomeRoute) {
                                inclusive = true
                            }
                        }
                    }
                }
            }

            HistoryEvent.GoToProfile -> {
                viewModelScope.launch {
                    navigator.command {
                        navigate(route = ProfileRoute) {
                            popUpTo(ProfileRoute) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }
    }

}