package nfv.auth.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import nfv.auth.presentation.screens.login.LoginScreen
import nfv.auth.presentation.screens.login.LoginViewModel
import nfv.auth.presentation.screens.onboard.OnBoardScreen
import nfv.auth.presentation.screens.onboard.OnBoardViewModel
import nfv.auth.presentation.screens.otp.OTPScreen
import nfv.auth.presentation.screens.otp.OTPViewModel
import nfv.auth.presentation.screens.register.RegisterScreen
import nfv.auth.presentation.screens.register.RegisterViewModel
import nfv.auth.presentation.screens.registerCompletion.WelcomeScreen
import nfv.auth.presentation.screens.registerCompletion.WelcomeViewModel
import nfv.auth.presentation.screens.registerForm.RegisterFormScreen
import nfv.auth.presentation.screens.registerForm.RegisterFormViewModel
import nfv.auth.presentation.screens.registerFormMedical.RegisterFormMedicalScreen
import nfv.auth.presentation.screens.registerFormMedical.RegisterFormMedicalViewModel
import nfv.navigation.routes.AuthNavigation
import nfv.navigation.routes.LoginRoute
import nfv.navigation.routes.OnBoardRoute
import nfv.navigation.routes.RegisterCompletionRoute
import nfv.navigation.routes.RegisterFormMedicalRoute
import nfv.navigation.routes.RegisterFormRoute
import nfv.navigation.routes.RegisterOTPRoute
import nfv.navigation.routes.RegisterRoute

fun NavGraphBuilder.authNavigation() {

    navigation<AuthNavigation>(startDestination = AuthNavigation.START_DESTINATION) {

        composable<OnBoardRoute> {
            val viewModel = hiltViewModel<OnBoardViewModel>()
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            OnBoardScreen(
                state = state,
                onUiEvent = viewModel::handleEvent
            )

        }

        composable<RegisterRoute> {
            val viewModel = hiltViewModel<RegisterViewModel>()
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            RegisterScreen(
                state = state,
                onUiEvent = viewModel::handleEvent
            )
        }

        composable<RegisterFormRoute> {
            val viewModel = hiltViewModel<RegisterFormViewModel>()
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            RegisterFormScreen(
                state = state,
                onUiEvent = viewModel::handleEvent
            )
        }

        composable<RegisterFormMedicalRoute> {
            val viewModel = hiltViewModel<RegisterFormMedicalViewModel>()
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            RegisterFormMedicalScreen(
                state = state,
                onUiEvent = viewModel::handleEvent
            )
        }

        composable<RegisterOTPRoute> {
            val viewModel = hiltViewModel<OTPViewModel>()
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            OTPScreen(
                state = state,
                onUiEvent = viewModel::handleEvent
            )
        }

        composable<RegisterCompletionRoute> {
            val viewModel = hiltViewModel<WelcomeViewModel>()
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            WelcomeScreen(
                state = state,
                onUiEvent = viewModel::handleEvent
            )
        }

        composable<LoginRoute> {
            val viewModel = hiltViewModel<LoginViewModel>()
            val state by viewModel.uiState.collectAsStateWithLifecycle()

            LoginScreen(
                state = state,
                onUiEvent = viewModel::handleEvent
            )
        }

    }
}