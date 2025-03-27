package nfv.home.presentation

sealed interface HomeEvent {

    data class OnSearchTextChanged(val newValue: String) : HomeEvent
    data object OnSearchTextSearched : HomeEvent
    data class OnSearchFiltered(val filter: (String)->Unit) : HomeEvent   //TODO -> bunu et

    data object GoToHome : HomeEvent
    data object GoToHistory : HomeEvent
    data object GoToProfile : HomeEvent
}