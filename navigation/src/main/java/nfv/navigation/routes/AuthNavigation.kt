package nfv.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
data object AuthNavigation {
    val START_DESTINATION = OnBoardRoute
}

@Serializable
data object OnBoardRoute

@Serializable
data object RegisterRoute

@Serializable
data object RegisterFormRoute

@Serializable
data object RegisterFormMedicalRoute

@Serializable
data object RegisterOTPRoute

@Serializable
data object RegisterCompletionRoute

@Serializable
data object LoginRoute

@Serializable
data object LockRoute
