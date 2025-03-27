package nfv.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
data object HistoryNavigation {
    val START_DESTINATION = HistoryRoute
}

@Serializable
//data class HistoryRoute(val isComingFromProfile: Boolean)
data object HistoryRoute