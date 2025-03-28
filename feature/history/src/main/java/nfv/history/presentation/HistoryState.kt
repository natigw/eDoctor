package nfv.history.presentation

data class HistoryState(
    val searchText: String,
    val testResults: Map<String, List<TestResultItem>>
)