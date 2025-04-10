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
                        testResults = resultGrouped,
                        filteredTestResults = resultGrouped
                    )
                }
            }
        }
    }

    val uiState = MutableStateFlow(
        HistoryState(
            searchText = "",
            testResults = resultGrouped,
            filteredTestResults = resultGrouped
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
                    val searchText = event.newValue.lowercase()

                    val filteredResults = resultGrouped.mapValues { (_, items) ->
                        items.filter { item ->
                            item.testTitle.lowercase().contains(searchText) ||
                                    item.labName.lowercase().contains(searchText) ||
                                    item.testDescription.lowercase().contains(searchText)
                        }
                    }.filterValues { it.isNotEmpty() }

                    uiState.update { old ->
                        old.copy(
                            filteredTestResults = filteredResults
                        )
                    }
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

            is HistoryEvent.OnReadStatusChanged -> {
                viewModelScope.launch {
                    val updatedFilteredResults = uiState.value.filteredTestResults.mapValues { (_, items) ->
                        items.map { item ->
                            if (item.id == event.id) {
                                item.copy(isRead = true)
                            } else {
                                item
                            }
                        }
                    }

                    val updatedResults = uiState.value.testResults.mapValues {
                        it.value.map { item ->
                            if (item.id == event.id) {
                                item.copy(isRead = true)
                            } else {
                                item
                            }
                        }
                    }

                    uiState.update { old ->
                        old.copy(
                            testResults = updatedResults,
                            filteredTestResults = updatedFilteredResults
                        )
                    }
                }
            }
        }
    }

}