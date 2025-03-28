package nfv.profile.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.navigation.routes.AboutUsRoute
import nfv.navigation.routes.ChangePasscodeRoute
import nfv.navigation.routes.HistoryRoute
import nfv.navigation.routes.HomeRoute
import nfv.navigation.routes.LoginRoute
import nfv.navigation.routes.MedicalInfoRoute
import nfv.navigation.routes.TermsConditionsRoute
import nfv.profile.presentation.screens.changeLanguage.model.SupportedLanguages
import nfv.profile.presentation.screens.changeTheme.model.SupportedThemes
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    val uiState = MutableStateFlow(
        ProfileState(
            userFullName = "",
            profileLink = "",
            currentLanguage = SupportedLanguages.ENGLISH, //shared prefden
            currentTheme = SupportedThemes.DARK, //shared prefden
            allowBiometrics = true, //shared prefden
            allowScreenshots = true //shared prefden
        )
    )

    fun handleEvent(event: ProfileEvent) {

        when (event) {

            is ProfileEvent.OnLanguageConfirmed -> {
                uiState.update { old ->
                    old.copy(
                        currentLanguage = event.language
                    )
                }
            }

            is ProfileEvent.OnThemeConfirmed -> {
                uiState.update { old ->
                    old.copy(
                        currentTheme = event.theme
                    )
                }
            }


            ProfileEvent.GoToMedicalInfo -> {
                viewModelScope.launch {
                    navigator.command {
                        navigate(route = MedicalInfoRoute)
                    }
                }
            }

            ProfileEvent.OnOptionEditProfileClicked -> {
                viewModelScope.launch {
                    navigator.command {
//                        navigate()
                    }
                }
            }

            ProfileEvent.OnOptionChangePasscodeClicked -> {
                viewModelScope.launch {
                    navigator.command {
                        navigate(route = ChangePasscodeRoute)
                    }
                }
            }

            ProfileEvent.OnOptionBiometricsClicked -> {
                viewModelScope.launch {
                    //code

                    uiState.update { old ->
                        old.copy(
                            allowBiometrics = uiState.value.allowBiometrics.not()
                        )
                    }
                }
            }

            ProfileEvent.OnOptionAllowScreenshotsClicked -> {
                viewModelScope.launch {
                    //code

                    uiState.update { old ->
                        old.copy(
                            allowScreenshots = uiState.value.allowScreenshots.not()
                        )
                    }
                }
            }

            ProfileEvent.OnOptionTermsClicked -> {
                viewModelScope.launch {
                    navigator.command {
                        navigate(route = TermsConditionsRoute)
                    }
                }
            }

            ProfileEvent.OnOptionAboutUsClicked -> {
                viewModelScope.launch {
                    navigator.command {
                        navigate(route = AboutUsRoute)
                    }
                }
            }

            ProfileEvent.OnLogoutClicked -> {
                viewModelScope.launch {
                    navigator.command {
                        navigate(LoginRoute) {
                            popUpTo(0) {      //TODO -> bunu deyis nese duz islemir naviqasiya
                                inclusive = true
                            }
                        }
                    }
                }
            }


            ProfileEvent.GoToHome -> {
                viewModelScope.launch {
                    navigator.command {
                        navigate(HomeRoute) {
                            popUpTo(HomeRoute) {
                                inclusive = true
                            }
                        }
                    }
                }
            }

            ProfileEvent.GoToHistory -> {
                viewModelScope.launch {
                    navigator.command {
//                        navigate(HistoryNavigation) {        //TODO -> navigation olmalidi yoxsa route?
//                            popUpTo(HistoryNavigation) {
//                                inclusive = true
//                            }
//                        }
                        navigate(HistoryRoute) {
                            popUpTo(HistoryRoute) {
                                inclusive = true
                            }
                        }
                    }
                }
            }

            ProfileEvent.GoToProfile -> {}
        }
    }
}