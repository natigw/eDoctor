package nfv.history.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import nfv.history.presentation.screens.history.HistoryViewModel
import nfv.history.presentation.screens.history.TestResultsScreen
import nfv.navigation.routes.HistoryNavigation
import nfv.navigation.routes.HistoryRoute

fun NavGraphBuilder.historyNavigation() {

    navigation<HistoryNavigation>(startDestination = HistoryNavigation.START_DESTINATION) {

        composable<HistoryRoute> {
            val viewModel = hiltViewModel<HistoryViewModel>()
            val state by viewModel.uiState.collectAsStateWithLifecycle()
            TestResultsScreen(
                state = state,
                onUiEvent = viewModel::handleEvent
            )
        }

    }
}