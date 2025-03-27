package nfv.auth.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.buttons.model.ButtonTypes
import nfv.ui_kit.components.buttons.square.ActiveButton
import nfv.ui_kit.components.buttons.square.OutlinedButton
import nfv.ui_kit.components.buttons.transparent.ActiveTransparentButton
import nfv.ui_kit.components.inputFields.CustomTextField
import nfv.ui_kit.components.inputFields.CustomTextFieldPassword
import nfv.ui_kit.components.inputFields.PasswordStrength
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@Composable
fun LoginScreen(
    state: LoginState,
    onUiEvent: (LoginEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .systemBarsPadding(),
        topBar = {
            TopBar(
                leadingIcon = IconWithAction(
                    icon = drawableR.ic_arrow_left,
                    action = {
                        onUiEvent(LoginEvent.OnNavigateBack)
                    }
                )
            )
        }
    ) { innerPadding ->

        if (state.emailText.isNotBlank() && state.passwordText.isNotBlank())
            onUiEvent(LoginEvent.OnLoginButtonStateUpdated(ButtonState.ENABLED))
        else
            onUiEvent(LoginEvent.OnLoginButtonStateUpdated(ButtonState.DISABLED))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(16.dp))
            Text(
                text = stringResource(stringR.login_welcome_back),
                style = EDoctorTypography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.outline
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = stringResource(stringR.login_instruction),
                style = EDoctorTypography.bodyMedium.copy(color = MaterialTheme.colorScheme.outlineVariant)
            )
            Spacer(Modifier.height(32.dp))

            CustomTextField(
                titleText = stringResource(stringR.email),
                hintText = stringResource(stringR.email_description),
                text = state.emailText,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                onTextChange = {
                    onUiEvent(LoginEvent.OnEmailTextChanged(it))
                },
                onTextClear = {
                    onUiEvent(LoginEvent.OnEmailTextChanged(""))
                }
            )
            Spacer(Modifier.height(16.dp))
            CustomTextFieldPassword(
                passwordStrength = PasswordStrength.NONE,
                titleText = stringResource(stringR.password),
                hintText = stringResource(stringR.password_description),
                text = state.passwordText,
                onTextChange = {
                    onUiEvent(LoginEvent.OnPasswordTextChanged(it))
                },
                onTextClear = {
                    onUiEvent(LoginEvent.OnPasswordTextChanged(""))
                }
            )
            Row {
                Spacer(Modifier.weight(1f))
                ActiveTransparentButton(
                    modifier = Modifier.offset(x = 12.dp),
                    buttonType = ButtonTypes.SMALL,
                    state = ButtonState.ENABLED,
                    textEnabled = "",
                    textEnabledAnnotated = buildAnnotatedString {
                        withStyle(
                            style = EDoctorTypography.bodyMedium
                                .copy(color = MaterialTheme.colorScheme.outlineVariant)
                                .toSpanStyle()
                        ) {
                            append("Forgot Password")
                        }
                    },
                    onClick = {
                        onUiEvent(LoginEvent.OnForgotPasswordClicked)
                    }
                )
            }

            Spacer(Modifier.height(32.dp))
            ActiveButton(
                modifier = Modifier
                    .fillMaxWidth(),
                buttonType = ButtonTypes.LARGE,
                state = state.loginButtonState,
                textEnabled = stringResource(stringR.sign_in),
                onClick = {
                    onUiEvent(
                        LoginEvent.OnLoginButtonClicked(
                            email = state.emailText,
                            password = state.passwordText
                        )
                    )
                }
            )
            Spacer(Modifier.height(16.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.surfaceContainerLow)
            Spacer(Modifier.height(16.dp))
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth(),
                buttonType = ButtonTypes.LARGE,
                state = ButtonState.ENABLED,
                textEnabled = stringResource(stringR.sign_in_with_google),
                startIconRes = drawableR.logo_google,
                onClick = {
                    onUiEvent(LoginEvent.OnLoginWithGoogleButtonClicked)
                }
            )
            Spacer(Modifier.height(16.dp))
            ActiveButton(
                modifier = Modifier
                    .fillMaxWidth(),
                buttonType = ButtonTypes.LARGE,
                state = ButtonState.ENABLED,
                textEnabled = stringResource(stringR.sign_in_with_facebook),
                startIconRes = drawableR.logo_facebook_outlined,
                onClick = {
                    onUiEvent(LoginEvent.OnLoginWithFacebookButtonClicked)
                }
            )

            Spacer(Modifier.weight(1f))

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
                        append(stringResource(stringR.dont_have_an_account))
                    }
                    withStyle(
                        style = EDoctorTypography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                            .toSpanStyle()
                    ) {
                        append(stringResource(stringR.register))
                    }
                },
                onClick = {
                    onUiEvent(LoginEvent.GoToRegister)
                }
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPrev() {
    LoginScreen(
        state = LoginState(
            emailText = "",
            passwordText = "",
            loginButtonState = ButtonState.ENABLED
        ),
        onUiEvent = {}
    )
}