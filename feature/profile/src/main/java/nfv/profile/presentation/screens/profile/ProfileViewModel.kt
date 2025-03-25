package nfv.profile.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import nfv.profile.presentation.screens.changeLanguage.SupportedLanguages
import nfv.profile.presentation.screens.changeTheme.SupportedThemes
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
                        this.navigate(MedicalInfoNavigation)
                    }
                }
            }

            ProfileEvent.OnOptionEditProfileClicked ->  {
                viewModelScope.launch {
                    navigator.command {
                        this.navigate()
                    }
                }
            }
            ProfileEvent.OnOptionChangePasscodeClicked ->  {
                viewModelScope.launch {
                    navigator.command {
                        this.navigate()
                    }
                }
            }
            ProfileEvent.OnOptionBiometricsClicked ->  {
                viewModelScope.launch {
                    navigator.command {
                        this.navigate()
                    }
                }
            }
            ProfileEvent.OnOptionAllowScreenshotsClicked -> {
                viewModelScope.launch {
                    navigator.command {
                        this.navigate()
                    }
                }
            }
            ProfileEvent.OnOptionTermsClicked ->  {
                viewModelScope.launch {
                    navigator.command {
                        this.navigate()
                    }
                }
            }
            ProfileEvent.OnOptionAboutUsClicked -> {
                viewModelScope.launch {
                    navigator.command {
                        this.navigate()
                    }
                }
            }
            ProfileEvent.OnLogoutClicked -> {
                viewModelScope.launch {
                    navigator.command {
                        this.navigate()
                    }
                }
            }


            ProfileEvent.GoToHome -> {
                viewModelScope.launch {
                    navigator.command {
                        this.navigate(HomeNavigation)
                    }
                }
            }

            ProfileEvent.GoToHistory -> {
                viewModelScope.launch {
                    navigator.command {
                        this.navigate(HistoryNavigation)
                    }
                }
            }

            ProfileEvent.GoToProfile -> {

            }
        }
    }
}