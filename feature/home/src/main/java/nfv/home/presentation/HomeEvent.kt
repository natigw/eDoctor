package nfv.home.presentation

sealed interface HomeEvent {

    data class OnSearchTextChanged(val newValue: String) : HomeEvent
    data object OnSearchTextSearched : HomeEvent
    data class OnSearchFiltered(val filter: (String) -> Unit) : HomeEvent   //TODO -> bunu et

    data class OnNewsClicked(val newsId: String) : HomeEvent
    data object OnMapClicked : HomeEvent

    data object GoToHome : HomeEvent
    data object GoToHistory : HomeEvent
    data object GoToProfile : HomeEvent
    data object GoToMedicalInfo : HomeEvent
}