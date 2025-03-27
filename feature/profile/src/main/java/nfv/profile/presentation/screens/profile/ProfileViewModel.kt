package nfv.profile.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.navigation.routes.HistoryRoute
import nfv.navigation.routes.HomeRoute
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
//                        navigate()
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
//                        navigate()
                    }
                }
            }

            ProfileEvent.OnOptionBiometricsClicked -> {
                viewModelScope.launch {
                    navigator.command {
//                        navigate()
                    }
                }
            }

            ProfileEvent.OnOptionAllowScreenshotsClicked -> {
                viewModelScope.launch {
                    navigator.command {
//                        navigate()
                    }
                }
            }

            ProfileEvent.OnOptionTermsClicked -> {
                viewModelScope.launch {
                    navigator.command {
//                        navigate()
                    }
                }
            }

            ProfileEvent.OnOptionAboutUsClicked -> {
                viewModelScope.launch {
                    navigator.command {
//                        navigate()
                    }
                }
            }

            ProfileEvent.OnLogoutClicked -> {
                viewModelScope.launch {
                    navigator.command {
//                        navigate()
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