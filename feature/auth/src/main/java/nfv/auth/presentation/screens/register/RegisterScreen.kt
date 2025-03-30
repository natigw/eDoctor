package nfv.auth.presentation.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.auth.presentation.screens.registerForm.RegisterFormEvent
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.buttons.model.ButtonTypes
import nfv.ui_kit.components.buttons.square.ActiveButton
import nfv.ui_kit.components.buttons.transparent.ActiveTransparentButton
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.theme.EDoctorTheme
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@Composable
fun RegisterScreen(
    state: RegisterState,
    onUiEvent: (RegisterEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            TopBar(
                leadingIcon = IconWithAction(
                    icon = drawableR.ic_arrow_left,
                    action = {
                        onUiEvent(RegisterEvent.OnNavigateBack)
                    }
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Welcome to eDoctor!",
                style = EDoctorTypography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.outline
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Sign in to the account if you already have one, or sign up for a new account",
                style = EDoctorTypography.bodyMedium.copy(color = MaterialTheme.colorScheme.outlineVariant)
            )

            Spacer(Modifier.weight(1f))

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                ActiveButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    buttonType = ButtonTypes.LARGE,
                    state = ButtonState.DISABLED,
                    textEnabled = stringResource(stringR.continue_),
                    onClick = {
                        onUiEvent(RegisterEvent.OnContinueClicked)
                    }
                )

                ActiveTransparentButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    buttonType = ButtonTypes.LARGE,
                    state = ButtonState.ENABLED,
                    textEnabled = "",
                    textEnabledAnnotated = buildAnnotatedString {
                        withStyle(
                            style = EDoctorTypography.bodyMedium.copy(color = MaterialTheme.colorScheme.outlineVariant)
                                .toSpanStyle()
                        ) {
                            append(stringResource(stringR.already_have_an_account))
                        }
                        withStyle(
                            style = EDoctorTypography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                                .toSpanStyle()
                        ) {
                            append(stringResource(stringR.sign_in))
                        }
                    },
                    onClick = {
                        onUiEvent(RegisterEvent.OnContinueClicked)
                    }
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    withStyle(
                        style = EDoctorTypography.labelMedium.copy(color = MaterialTheme.colorScheme.outlineVariant)
                            .toSpanStyle()
                    ) {
                        append(stringResource(stringR.register_terms1))
                    }
                    withStyle(
                        style = EDoctorTypography.labelMedium.copy(color = MaterialTheme.colorScheme.primary)
                            .toSpanStyle()
                    ) {
                        append(stringResource(stringR.terms_of_service))
                    }
                    withStyle(
                        style = EDoctorTypography.labelMedium.copy(color = MaterialTheme.colorScheme.outlineVariant)
                            .toSpanStyle()
                    ) {
                        append(stringResource(stringR.register_terms2))
                    }
                    withStyle(
                        style = EDoctorTypography.labelMedium.copy(color = MaterialTheme.colorScheme.primary)
                            .toSpanStyle()
                    ) {
                        append(stringResource(stringR.privacy_policy))
                    }
                    withStyle(
                        style = EDoctorTypography.labelMedium.copy(color = MaterialTheme.colorScheme.outlineVariant)
                            .toSpanStyle()
                    ) {
                        append(stringResource(stringR.register_terms3))
                    }
                },
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPrev() {
    EDoctorTheme {
        RegisterScreen(
            state = RegisterState(
            ),
            onUiEvent = {}
        )
    }
}