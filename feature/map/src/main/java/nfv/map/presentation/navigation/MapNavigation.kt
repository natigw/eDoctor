package nfv.map.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import nfv.map.presentation.screens.MapScreen
import nfv.map.presentation.screens.MapViewModel
import nfv.navigation.routes.MapNavigation
import nfv.navigation.routes.MapRoute

fun NavGraphBuilder.mapNavigation() {

    navigation<MapNavigation>(startDestination = MapNavigation.START_DESTINATION) {

        composable<MapRoute> {
            val viewModel = hiltViewModel<MapViewModel>()
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            MapScreen(
                state = state,
                onUiEvent = viewModel::handleEvent
            )
        }
    }
}