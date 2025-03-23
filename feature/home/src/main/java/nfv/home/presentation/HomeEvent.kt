package nfv.home.presentation

sealed interface HomeEvent {
    data object GoToHistory : HomeEvent
    data object GoToProfile : HomeEvent
}