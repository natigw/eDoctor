package nfv.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
data object AuthNavigation {
    val START_DESTINATION = RegisterFormRoute
}

@Serializable
data object LoginRoute

@Serializable
data object RegisterRoute

@Serializable
data object RegisterFormRoute
