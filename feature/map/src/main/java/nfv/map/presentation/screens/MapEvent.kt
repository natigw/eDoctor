package nfv.map.presentation.screens

sealed interface MapEvent {

    data object OnNavigateBack: MapEvent

}