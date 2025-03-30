package nfv.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
data object MapNavigation {
    val START_DESTINATION = MapRoute
}

@Serializable
data object MapRoute