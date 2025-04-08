package nfv.history.presentation.screens.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.history.data.HistoryRepository
import nfv.history.download.Downloader
import nfv.history.model.HistoryResultUiItem
import nfv.navigation.di.Navigator
import nfv.navigation.routes.HomeRoute
import nfv.navigation.routes.ProfileRoute
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val navigator: Navigator,
    private val repository: HistoryRepository,
    private val downloader: Downloader
) : ViewModel() {

    private var resultGrouped: Map<String, List<HistoryResultUiItem>> = emptyMap()

    init {
        viewModelScope.launch {
            val results = repository.getTestResults()

            Log.d("results", results.toString())

            if (results != null) {
                val zoneId = ZoneId.systemDefault()
                val currentYear = Instant.now().atZone(zoneId).year
                val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm")
                    .withLocale(Locale.getDefault())
                    .withZone(zoneId)

                val uiItems = results.sortedByDescending { it.testDateMillis }.map { data ->
                    val testDate = formatter.format(Instant.ofEpochMilli(data.testDateMillis))
                    HistoryResultUiItem(
                        id = data.id,
                        testTitle = data.testTitle,
                        labName = data.labName,
                        testDate = testDate,
                        testDescription = data.testDescription,
                        testFileUrl = data.testFileUrl,
                        isRead = data.isRead
                    )
                }
                resultGrouped = uiItems.groupBy { test ->
                    val testDate =
                        Instant.from(formatter.parse(test.testDate)).atZone(zoneId)
                    val monthName =
                        testDate.month.name.lowercase().replaceFirstChar { it.uppercaseChar() }
                    if (testDate.year != currentYear) {
                        "$monthName ${testDate.year}"
                    } else {
                        monthName
                    }
                }

                uiState.update {
                    it.copy(
                        testResults = resultGrouped
                    )
                }
            }
        }
    }

    val uiState = MutableStateFlow(
        HistoryState(
            searchText = "", testResults = resultGrouped
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
                viewModelScope.launch {
                    downloader.downloadFile(event.link, event.title)
                }
            }
        }
    }

}