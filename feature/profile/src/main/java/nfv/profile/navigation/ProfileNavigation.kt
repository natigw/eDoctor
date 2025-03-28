package nfv.profile.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import nfv.navigation.routes.AboutUsRoute
import nfv.navigation.routes.ChangePasscodeRoute
import nfv.navigation.routes.MedicalInfoRoute
import nfv.navigation.routes.ProfileNavigation
import nfv.navigation.routes.ProfileRoute
import nfv.navigation.routes.TermsConditionsRoute
import nfv.profile.presentation.screens.aboutUs.AboutUsScreen
import nfv.profile.presentation.screens.aboutUs.AboutUsViewModel
import nfv.profile.presentation.screens.changePasscode.ChangePasscodeScreen
import nfv.profile.presentation.screens.changePasscode.ChangePasscodeViewModel
import nfv.profile.presentation.screens.medicalInfo.MedicalInfoScreen
import nfv.profile.presentation.screens.medicalInfo.MedicalInfoViewModel
import nfv.profile.presentation.screens.profile.ProfileScreen
import nfv.profile.presentation.screens.profile.ProfileViewModel
import nfv.profile.presentation.screens.termsConditions.TermsConditionsScreen
import nfv.profile.presentation.screens.termsConditions.TermsConditionsViewModel

fun NavGraphBuilder.profileNavigation() {

    navigation<ProfileNavigation>(startDestination = ProfileNavigation.START_DESTINATION) {

        composable<ProfileRoute> {
            val viewModel = hiltViewModel<ProfileViewModel>()
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            ProfileScreen(
                state = state,
                onUiEvent = viewModel::handleEvent
            )
        }

        composable<MedicalInfoRoute> {
            val viewModel = hiltViewModel<MedicalInfoViewModel>()
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            MedicalInfoScreen(
                state = state,
                onUiEvent = viewModel::handleEvent
            )
        }

        composable<ChangePasscodeRoute> {
            val viewModel = hiltViewModel<ChangePasscodeViewModel>()
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            ChangePasscodeScreen(
                state = state,
                onUiEvent = viewModel::handleEvent
            )
        }

        composable<TermsConditionsRoute> {
            val viewModel = hiltViewModel<TermsConditionsViewModel>()

            TermsConditionsScreen(
                onUiEvent = viewModel::handleEvent
            )
        }

        composable<AboutUsRoute> {
            val viewModel = hiltViewModel<AboutUsViewModel>()

            AboutUsScreen(
                onUiEvent = viewModel::handleEvent
            )
        }

    }
}