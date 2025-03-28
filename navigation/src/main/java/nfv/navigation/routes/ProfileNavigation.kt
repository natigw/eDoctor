package nfv.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
data object ProfileNavigation {
    val START_DESTINATION = ProfileRoute
}

@Serializable
data object ProfileRoute

@Serializable
data object MedicalInfoRoute

@Serializable
data object EditProfileRoute

@Serializable
data object ChangePasscodeRoute

@Serializable
data object TermsConditionsRoute

@Serializable
data object AboutUsRoute