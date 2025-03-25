package nfv.auth.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import nfv.auth.presentation.screens.registerForm.RegisterFormScreen
import nfv.auth.presentation.screens.registerForm.RegisterFormViewModel
import nfv.navigation.routes.AuthNavigation
import nfv.navigation.routes.RegisterFormRoute

fun NavGraphBuilder.authNavigation() {
    navigation<AuthNavigation>(startDestination = AuthNavigation.START_DESTINATION) {

        composable<RegisterFormRoute> {
            val viewModel = hiltViewModel<RegisterFormViewModel>()
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            RegisterFormScreen (
                state = state,
                onUiEvent = viewModel::handleEvent
            )
        }

    }
}