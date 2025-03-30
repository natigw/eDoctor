package nfv.history.presentation.screens.history

import nfv.history.model.Data

data class HistoryState(
    val searchText: String,
    val testResults: Map<String, List<Data>>
)