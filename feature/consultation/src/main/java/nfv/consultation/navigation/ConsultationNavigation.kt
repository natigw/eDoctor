package nfv.consultation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import nfv.consultation.presentation.ConsultationScreen
import nfv.consultation.presentation.ConsultationViewModel
import nfv.navigation.routes.ConsultationNavigation
import nfv.navigation.routes.ConsultationRoute

fun NavGraphBuilder.consultationNavigation() {

    navigation<ConsultationNavigation>(startDestination = ConsultationNavigation.START_DESTINATION) {

        composable<ConsultationRoute> {
            val viewModel = hiltViewModel<ConsultationViewModel>()
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            ConsultationScreen(
                state = state,
                onUiEvent = viewModel::handleEvent
            )
        }
    }

}