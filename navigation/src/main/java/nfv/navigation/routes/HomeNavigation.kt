package nfv.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
data object HomeNavigation {
    val START_DESTINATION = HomeRoute
}

@Serializable
data object HomeRoute