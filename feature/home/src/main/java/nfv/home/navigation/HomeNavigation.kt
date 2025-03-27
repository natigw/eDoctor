package nfv.home.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import nfv.home.presentation.HomeScreen
import nfv.home.presentation.HomeViewModel
import nfv.navigation.routes.HomeNavigation
import nfv.navigation.routes.HomeRoute

fun NavGraphBuilder.homeNavigation() {

    navigation<HomeNavigation>(startDestination = HomeNavigation.START_DESTINATION) {

        composable<HomeRoute> {
            val viewModel = hiltViewModel<HomeViewModel>()
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            HomeScreen(
                state = state,
                onUiEvent = viewModel::handleEvent
            )
        }
    }

}