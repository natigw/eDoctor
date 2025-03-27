package nfv.history.presentation

sealed interface HistoryEvent {

    data class OnSearchTextChanged(val newValue: String) : HistoryEvent
    data class OnSearchTextSearched(val newValue: String) : HistoryEvent

    data object OnNavigateBack : HistoryEvent
    data object GoToHome : HistoryEvent
    data object GoToHistory : HistoryEvent
    data object GoToProfile : HistoryEvent

}