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
            searchText = "",
            testResults = mapOf(
                "Yanvar" to listOf(
                    TestResultItem(
                        0,
                        "Qan analizi",
                        "Referans Lab",
                        "10 yanvar"
                    ),
                    TestResultItem(
                        1,
                        "Pregnancy test",
                        "Merkezi klinika",
                        "12 yanvar"
                    ),
                    TestResultItem(
                        2,
                        "Qlükoza",
                        "Merkezi klinika",
                        "15 yanvar"
                    )
                ),
                "Mart" to listOf(
                    TestResultItem(
                        3,
                        "Xolesterin",
                        "Referans Lab",
                        "5 fevral"
                    )
                ),
                "Iyun" to listOf(
                    TestResultItem(
                        4,
                        "D vitamini",
                        "Referans Lab",
                        "20 fevral"
                    ),
                    TestResultItem(
                        5,
                        "Kalsium",
                        "Merkezi klinika",
                        "28 fevral"
                    )
                ),
                "Yanvar1" to listOf(
                    TestResultItem(
                        0,
                        "Qan analizi",
                        "Referans Lab",
                        "10 yanvar"
                    ),
                    TestResultItem(
                        1,
                        "Pregnancy test",
                        "Merkezi klinika",
                        "12 yanvar"
                    ),
                    TestResultItem(
                        2,
                        "Qlükoza",
                        "Merkezi klinika",
                        "15 yanvar"
                    )
                ),
                "Mart2" to listOf(
                    TestResultItem(
                        3,
                        "Xolesterin",
                        "Referans Lab",
                        "5 fevral"
                    )
                ),
                "Iyun3" to listOf(
                    TestResultItem(
                        4,
                        "D vitamini",
                        "Referans Lab",
                        "20 fevral"
                    ),
                    TestResultItem(
                        5,
                        "Kalsium",
                        "Merkezi klinika",
                        "28 fevral"
                    )
                )
            )
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
                    navigator.sendCommand {
                        this.popBackStack()
                    }
                }
            }

            HistoryEvent.GoToHistory -> {}

            HistoryEvent.GoToHome -> {
                viewModelScope.launch {
                    navigator.sendCommand {
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
                    navigator.sendCommand {
                        navigate(route = ProfileRoute) {
                            popUpTo(ProfileRoute) {
                                inclusive = true
                            }
                        }
                    }
                }
            }

            is HistoryEvent.OnClickDownloadDocument -> {
                //download
            }
        }
    }

}