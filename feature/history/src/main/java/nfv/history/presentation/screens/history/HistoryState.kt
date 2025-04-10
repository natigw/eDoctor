package nfv.history.presentation.screens.history

import nfv.history.model.HistoryResultUiItem

data class HistoryState(
    val searchText: String,
    val testResults: Map<String, List<HistoryResultUiItem>>,
    val filteredTestResults: Map<String, List<HistoryResultUiItem>>
)