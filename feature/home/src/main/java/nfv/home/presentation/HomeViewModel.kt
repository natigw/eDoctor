package nfv.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.navigation.routes.HistoryRoute
import nfv.navigation.routes.ProfileRoute
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    val uiState = MutableStateFlow(
        HomeState(
            username = "", //request
            searchText = ""
        )
    )

    fun handleEvent(event: HomeEvent) {

        when (event) {

            is HomeEvent.OnSearchTextChanged -> {
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            searchText = event.newValue
                        )
                    }
                }
            }

            HomeEvent.OnSearchTextSearched -> {
                viewModelScope.launch {
                    //searchRequest(uiState.value.searchText)
                }
            }

            is HomeEvent.OnSearchFiltered -> {}


            HomeEvent.GoToHome -> {}

            HomeEvent.GoToHistory -> {
                viewModelScope.launch {
                    navigator.command {
                        navigate(route = HistoryRoute) {
                            popUpTo(HistoryRoute) {
                                inclusive = true
                            }
                        }
                    }
                }
            }

            HomeEvent.GoToProfile -> {
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