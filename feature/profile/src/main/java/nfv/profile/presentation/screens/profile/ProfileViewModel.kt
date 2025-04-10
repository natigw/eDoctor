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
import nfv.storage.local.domain.AppPreferencesStorage
import nfv.storage.local.domain.TokenStorage
import nfv.storage.local.domain.UserPreferencesStorage
import nfv.storage.local.model.SupportedLanguages
import nfv.storage.local.model.SupportedThemes
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val navigator: Navigator,
    private val tokenStorage: TokenStorage,
    private val userPreferencesStorage: UserPreferencesStorage,
    private val appPreferencesStorage: AppPreferencesStorage
) : ViewModel() {

    val uiState = MutableStateFlow(
        ProfileState(
            userFullName = "",
            userProfilePicture = null,
            currentLanguage = SupportedLanguages.ENGLISH, //TODO -> better way??
            currentTheme = SupportedThemes.LIGHT,
            allowBiometrics = true,
            allowScreenshots = false
        )
    )

    init {
        viewModelScope.launch {
            userPreferencesStorage.getCurrentLanguage().collectLatest {
                uiState.update { old ->
                    old.copy(
                        currentLanguage = it
                    )
                }
            }
        }
        viewModelScope.launch {
            userPreferencesStorage.getCurrentTheme().collectLatest {
                uiState.update { old ->
                    old.copy(
                        currentTheme = it
                    )
                }
            }
        }
        viewModelScope.launch {
            userPreferencesStorage.getBiometricsAllowedStatus().collectLatest {
                uiState.update { old ->
                    old.copy(
                        allowBiometrics = it
                    )
                }
            }
        }
        viewModelScope.launch {
            userPreferencesStorage.getScreenshotsAllowedStatus().collectLatest {
                uiState.update { old ->
                    old.copy(
                        allowScreenshots = it
                    )
                }
            }
        }
        viewModelScope.launch {
            appPreferencesStorage.getProfilePicture().collectLatest {
                uiState.update { old ->
                    old.copy(
                        userProfilePicture = it
                    )
                }
            }
        }
    }

    fun handleEvent(event: ProfileEvent) {

        when (event) {

            is ProfileEvent.OnLanguageConfirmed -> {
                viewModelScope.launch {
                    userPreferencesStorage.saveLanguagePreference(event.language)
                }
            }

            is ProfileEvent.OnThemeConfirmed -> {
                viewModelScope.launch {
                    userPreferencesStorage.saveThemePreference(event.theme)
                }
            }

            is ProfileEvent.OnProfilePictureSelected -> {
                viewModelScope.launch {
                    if (event.image != null)
                        appPreferencesStorage.updateProfilePicture(event.image)
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

                    viewModelScope.launch {
                        userPreferencesStorage.updateBiometricsAllowStatus(uiState.value.allowBiometrics.not())
                    }
                }
            }

            ProfileEvent.OnOptionAllowScreenshotsClicked -> {
                viewModelScope.launch {
                    //code

                    viewModelScope.launch {
                        userPreferencesStorage.updateScreenshotsAllowStatus(uiState.value.allowScreenshots.not())
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

                    appPreferencesStorage.updateLoggedInStatus(false)

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