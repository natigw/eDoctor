package nfv.profile.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import nfv.navigation.routes.ProfileNavigation
import nfv.navigation.routes.ProfileRoute
import nfv.profile.presentation.screens.profile.ProfileScreen
import nfv.profile.presentation.screens.profile.ProfileViewModel

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

    }
}