package nfv.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
data object ConsultationNavigation {
    val START_DESTINATION = ConsultationRoute("")
}

@Serializable
data class ConsultationRoute(val initialQuestion: String)