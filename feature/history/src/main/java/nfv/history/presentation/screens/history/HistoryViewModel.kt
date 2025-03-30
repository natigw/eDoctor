package nfv.history.presentation.screens.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.history.data.HistoryRepository
import nfv.history.model.Data
import nfv.navigation.di.Navigator
import nfv.navigation.routes.HomeRoute
import nfv.navigation.routes.ProfileRoute
import java.time.Instant
import java.time.ZoneId
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val navigator: Navigator,
    private val repository: HistoryRepository
) : ViewModel() {

    private var resultGrouped: Map<String, List<Data>> = emptyMap()

    init {
        viewModelScope.launch {
            val results = repository.getTestResults()
            Log.e("salam", results.toString())

            if (results != null)
                resultGrouped = results.groupBy { dateMillis ->
                    Instant
                        .ofEpochMilli(dateMillis.testDateMillis)
                        .atZone(ZoneId.of("Asia/Azerbaijan"))
                        .toLocalDate()
                        .month.name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        }
                }
        }

        Log.e("salam", resultGrouped.toString())

    }

    val uiState = MutableStateFlow(
        HistoryState(
            searchText = "",
            testResults = resultGrouped
//            mapOf(
//                "Yanvar" to listOf(
//                    TestResultItem(
//                        0,
//                        "Qan analizi",
//                        "Referans Lab",
//                        "10 yanvar"
//                    ),
//                    TestResultItem(
//                        1,
//                        "Pregnancy test",
//                        "Merkezi klinika",
//                        "12 yanvar"
//                    ),
//                    TestResultItem(
//                        2,
//                        "Qlükoza",
//                        "Merkezi klinika",
//                        "15 yanvar"
//                    )
//                ),
//                "Mart" to listOf(
//                    TestResultItem(
//                        3,
//                        "Xolesterin",
//                        "Referans Lab",
//                        "5 fevral"
//                    )
//                ),
//                "Iyun" to listOf(
//                    TestResultItem(
//                        4,
//                        "D vitamini",
//                        "Referans Lab",
//                        "20 fevral"
//                    ),
//                    TestResultItem(
//                        5,
//                        "Kalsium",
//                        "Merkezi klinika",
//                        "28 fevral"
//                    )
//                ),
//                "Yanvar1" to listOf(
//                    TestResultItem(
//                        0,
//                        "Qan analizi",
//                        "Referans Lab",
//                        "10 yanvar"
//                    ),
//                    TestResultItem(
//                        1,
//                        "Pregnancy test",
//                        "Merkezi klinika",
//                        "12 yanvar"
//                    ),
//                    TestResultItem(
//                        2,
//                        "Qlükoza",
//                        "Merkezi klinika",
//                        "15 yanvar"
//                    )
//                ),
//                "Mart2" to listOf(
//                    TestResultItem(
//                        3,
//                        "Xolesterin",
//                        "Referans Lab",
//                        "5 fevral"
//                    )
//                ),
//                "Iyun3" to listOf(
//                    TestResultItem(
//                        4,
//                        "D vitamini",
//                        "Referans Lab",
//                        "20 fevral"
//                    ),
//                    TestResultItem(
//                        5,
//                        "Kalsium",
//                        "Merkezi klinika",
//                        "28 fevral"
//                    )
//                )
//            )
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