package nfv.history.presentation.screens.history

sealed interface HistoryEvent {

    data class OnSearchTextChanged(val newValue: String) : HistoryEvent
    data class OnSearchTextSearched(val newValue: String) : HistoryEvent
    data class OnReadStatusChanged(val id: String) : HistoryEvent

    data class OnClickDownloadDocument(val link: String, val title: String) : HistoryEvent

    data object OnNavigateBack : HistoryEvent
    data object GoToHome : HistoryEvent
    data object GoToHistory : HistoryEvent
    data object GoToProfile : HistoryEvent

}