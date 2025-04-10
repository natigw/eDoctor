package nfv.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.home.domain.NewsRepository
import nfv.navigation.di.Navigator
import nfv.navigation.routes.ConsultationRoute
import nfv.navigation.routes.HistoryRoute
import nfv.navigation.routes.MapRoute
import nfv.navigation.routes.MedicalInfoRoute
import nfv.navigation.routes.ProfileRoute
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator,
    private val newsRepository: NewsRepository
//    private val localPreferences: LocalRepository
) : ViewModel() {

    val uiState = MutableStateFlow(
        HomeState(
            username = "",
            searchText = "",
            news = emptyList()
        )
    )

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            val newsList = newsRepository.getNews()
            if (newsList != null)
                uiState.update { old ->
                    old.copy(
                        news = newsList.data
                    )
                }
        }
    }

//    init { //TODO
//        viewModelScope.launch {
//            localPreferences.getUsername.collectLatest {
//                if (it != null)
//                    uiState.update { old ->
//                        old.copy(
//                            username = it
//                        )
//                    }
//            }
//        }
//    }

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

                    val searchText = uiState.value.searchText

                    if (searchText.isBlank()) return@launch

                    navigator.sendCommand {
                        navigate(ConsultationRoute(searchText))
                    }

                    uiState.update { old ->
                        old.copy(
                            searchText = ""
                        )
                    }
                }
            }

            is HomeEvent.OnSearchFiltered -> {
                //
            }

            is HomeEvent.OnNewsClicked -> {
                //
            }

            is HomeEvent.OnConsultationClicked -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        navigate(route = ConsultationRoute(""))
                    }
                }
            }

            is HomeEvent.OnMapClicked -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        navigate(route = MapRoute)
                    }
                }
            }

            HomeEvent.GoToHome -> {}

            HomeEvent.GoToHistory -> {
                viewModelScope.launch {
                    navigator.sendCommand {
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
                    navigator.sendCommand {
                        navigate(route = ProfileRoute) {
                            popUpTo(ProfileRoute) {
                                inclusive = true
                            }
                        }
                    }
                }
            }

            HomeEvent.GoToMedicalInfo -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        navigate(route = MedicalInfoRoute)
                    }
                }
            }
        }
    }
}