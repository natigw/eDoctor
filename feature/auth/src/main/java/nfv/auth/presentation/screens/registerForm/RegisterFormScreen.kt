package nfv.auth.presentation.screens.registerForm

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.buttons.model.ButtonTypes
import nfv.ui_kit.components.buttons.square.ActiveButton
import nfv.ui_kit.components.buttons.transparent.ActiveTransparentButton
import nfv.ui_kit.components.inputFields.CustomTextField
import nfv.ui_kit.components.inputFields.CustomTextFieldPassword
import nfv.ui_kit.components.inputFields.PasswordStrength
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.theme.BaseWhite
import nfv.ui_kit.theme.DefaultScreenPadding
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Typography700
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun RegisterFormScreen(
    state: RegisterFormState,
    onUiEvent: (RegisterFormEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .systemBarsPadding(),
        topBar = {
            TopBar(
                leadingIcon = IconWithAction(
                    icon = drawableR.ic_arrow_left,
                    action = {
                        onUiEvent(RegisterFormEvent.OnNavigateBack)
                    }
                )
            )
        }
    ) { innerPadding ->


        val isFormValid = state.fullNameText.isNotBlank() &&
                state.emailText.isNotBlank() &&
                state.passwordText.isNotBlank() &&
                state.confirmPasswordText.isNotBlank() &&
                state.passwordText == state.confirmPasswordText

        val continueButtonState = if (isFormValid) ButtonState.ENABLED else ButtonState.DISABLED

        val passwordStrength = checkPasswordStrength(state.passwordText)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BaseWhite)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(DefaultScreenPadding)
        ) {

            RegisterInfoSection()

            RegisterFormSection(
                state = state.copy(
                    passwordStrength = passwordStrength,
                    continueButtonState = continueButtonState
                ),
                onUiEvent = onUiEvent
            )

            Spacer(Modifier.weight(1f))

            ButtonSection(
                state = state.copy(
                    passwordStrength = passwordStrength,
                    continueButtonState = continueButtonState
                ),
                onUiEvent = onUiEvent
            )
        }
    }
}

@Composable
fun RegisterInfoSection() {
    Column(
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        Text(
            text = "Register",
            style = EDoctorTypography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Please fill the form to continue the registration",
            style = EDoctorTypography.bodyMedium.copy(color = Typography700)
        )
    }
}

@Composable
fun RegisterFormSection(
    state: RegisterFormState,
    onUiEvent: (RegisterFormEvent) -> Unit
) {
    Column(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomTextField(
            titleText = "Full name",
            hintText = "Enter your full name",
            text = state.fullNameText,
            onTextChange = {
                onUiEvent(RegisterFormEvent.OnFullNameChanged(it))
            },
            onTextClear = {
                onUiEvent(RegisterFormEvent.OnFullNameChanged(""))
            },
            onComplete = {
                //TODO -> butun fieldleri yoxla ve signin ucun request at
            }
        )
        CustomTextField(
            titleText = "Email",
            hintText = "Enter your email",
            text = state.emailText,
            onTextChange = {
                onUiEvent(RegisterFormEvent.OnEmailChanged(it))
            },
            onTextClear = {
                onUiEvent(RegisterFormEvent.OnEmailChanged(""))
            },
            onComplete = {
                //TODO -> butun fieldleri yoxla ve signin ucun request at
            }
        )
        CustomTextFieldPassword(
            passwordStrength = state.passwordStrength,
            titleText = "Password",
            hintText = "Enter your password",
            text = state.passwordText,
            onTextChange = {
                onUiEvent(RegisterFormEvent.OnPasswordChanged(it))
            },
            onTextClear = {
                onUiEvent(RegisterFormEvent.OnPasswordChanged(""))
            },
            onComplete = {

            }
        )
        CustomTextFieldPassword(
            passwordStrength = PasswordStrength.NONE,
            titleText = "Confirm password",
            hintText = "Confirm your password",
            text = state.confirmPasswordText,
            onTextChange = {
                onUiEvent(RegisterFormEvent.OnConfirmPasswordChanged(it))
            },
            onTextClear = {
                onUiEvent(RegisterFormEvent.OnConfirmPasswordChanged(""))
            },
            onComplete = {

            }
        )
    }
}

@Composable
fun ButtonSection(
    state: RegisterFormState,
    onUiEvent: (RegisterFormEvent) -> Unit
) {
    Column(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ActiveButton(
            modifier = Modifier
                .fillMaxWidth(),
            buttonType = ButtonTypes.LARGE,
            state = state.continueButtonState,
            textEnabled = "Continue",
            onClick = {
                onUiEvent(RegisterFormEvent.OnClickContinue)
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
                    style = EDoctorTypography.bodyMedium.copy(color = Typography700)
                        .toSpanStyle()
                ) {
                    append("Have an account? ")
                }
                withStyle(
                    style = EDoctorTypography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Primary500
                    )
                        .toSpanStyle()
                ) {
                    append("Sign in")
                }
            },
            onClick = {
                onUiEvent(RegisterFormEvent.GoToLoginScreen)
            }
        )
    }
}

private fun checkPasswordStrength(password: String): PasswordStrength {
    val passwordLength = password.length
    val hasLowerCase = password.any { it.isLowerCase() }
    val hasUpperCase = password.any { it.isUpperCase() }
    val hasDigit = password.any { it.isDigit() }
    val hasSpecialChar = password.any { !it.isLetterOrDigit() }

    if (password.isBlank() || passwordLength < 4)
        return PasswordStrength.NONE

    else if (passwordLength < 6 || !hasLowerCase || !hasUpperCase || !hasDigit) {
        return PasswordStrength.WEAK
    }

    else if (!hasSpecialChar) {
        return PasswordStrength.MEDIUM
    }

    else return PasswordStrength.STRONG
}

@Preview(showBackground = true)
@Composable
private fun RegisterFormScreenPrev() {
    RegisterFormScreen(
        state = RegisterFormState(
            fullNameText = "",
            emailText = "",
            passwordText = "",
            confirmPasswordText = "",
            passwordStrength = PasswordStrength.NONE,
            continueButtonState = ButtonState.DISABLED
        ),
        onUiEvent = { }
    )
}