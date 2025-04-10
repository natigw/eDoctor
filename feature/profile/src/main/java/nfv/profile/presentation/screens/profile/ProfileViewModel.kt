package nfv.profile.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
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
import nfv.storage.local.domain.PreferencesStorage
import nfv.storage.local.domain.TokenStorage
import nfv.storage.local.model.SupportedLanguages
import nfv.storage.local.model.SupportedThemes
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val navigator: Navigator,
    private val tokenStorage: TokenStorage,
    private val preferencesStorage: PreferencesStorage
) : ViewModel() {

    val uiState = MutableStateFlow(
        ProfileState(
            userFullName = "",
            profileLink = "",
            currentLanguage = SupportedLanguages.ENGLISH, //TODO -> better way??
            currentTheme = SupportedThemes.LIGHT,
            allowBiometrics = true, //shared prefden
            allowScreenshots = true //shared prefden
        )
    )

    init {
        viewModelScope.launch {
            preferencesStorage.getCurrentLanguage().collectLatest {
                uiState.update { old ->
                    old.copy(
                        currentLanguage = it
                    )
                }
            }
        }
        viewModelScope.launch {
            preferencesStorage.getCurrentTheme().collectLatest {
                uiState.update { old ->
                    old.copy(
                        currentTheme = it
                    )
                }
            }
        }
    }

    fun handleEvent(event: ProfileEvent) {

        when (event) {

            is ProfileEvent.OnLanguageConfirmed -> {
                viewModelScope.launch {
                    preferencesStorage.saveLanguagePreference(event.language)
                }
            }

            is ProfileEvent.OnThemeConfirmed -> {
                viewModelScope.launch {
                    preferencesStorage.saveThemePreference(event.theme)
                }
            }


            ProfileEvent.GoToMedicalInfo -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        navigate(route = MedicalInfoRoute)
                    }
                }
            }

            ProfileEvent.OnOptionEditProfileClicked -> {
                viewModelScope.launch {
                    navigator.sendCommand {
//                        navigate()
                    }
                }
            }

            ProfileEvent.OnOptionChangePasscodeClicked -> {
                viewModelScope.launch {
                    navigator.sendCommand {
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
                    navigator.sendCommand {
                        navigate(route = TermsConditionsRoute)
                    }
                }
            }

            ProfileEvent.OnOptionAboutUsClicked -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        navigate(route = AboutUsRoute)
                    }
                }
            }

            ProfileEvent.OnLogoutClicked -> {
                viewModelScope.launch {

                    tokenStorage.clearToken()

                    navigator.sendCommand {
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
                    navigator.sendCommand {
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
                    navigator.sendCommand {
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