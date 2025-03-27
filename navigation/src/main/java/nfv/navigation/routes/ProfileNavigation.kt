package nfv.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
data object ProfileNavigation {
    val START_DESTINATION = ProfileRoute
}

@Serializable
data object ProfileRoute