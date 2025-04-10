package nfv.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
data object AuthNavigation {
    val START_DESTINATION = OnBoardRoute
}

@Serializable
data object OnBoardRoute

@Serializable
data object LoginRoute

@Serializable
data object RegisterRoute

@Serializable
data object RegisterFormRoute

@Serializable
data class RegisterFormMedicalRoute(
    val email: String,
    val password: String
)

@Serializable
data class RegisterOTPRoute(
    val email: String,
    val password: String
)

@Serializable
data object RegisterCompletionRoute

@Serializable
data object LockRoute