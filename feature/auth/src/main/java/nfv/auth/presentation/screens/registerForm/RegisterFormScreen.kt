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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import nfv.ui_kit.components.buttons.transparent.ActiveTransparentButton
import nfv.ui_kit.components.inputFields.CustomTextField
import nfv.ui_kit.components.inputFields.CustomTextFieldPassword
import nfv.ui_kit.components.inputFields.PasswordStrength
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.theme.DefaultScreenPadding
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

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
                state.arePasswordsIncompatible.not() //&&
//                state.passwordStrength != PasswordStrength.NONE &&   //TODO -> burani duz etmisem yoxsa remember olmalidi??
//                state.passwordStrength != PasswordStrength.WEAK

//        val continueButtonState = if (isFormValid) ButtonState.ENABLED else ButtonState.DISABLED

        val passwordStrength = checkPasswordStrength(state.passwordText)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(DefaultScreenPadding)
        ) {

            RegisterInfoSection()

            RegisterFormSection(
                state = state.copy(
                    passwordStrength = passwordStrength,
//                    continueButtonState = continueButtonState
                ),
                onUiEvent = onUiEvent
            )

            Spacer(Modifier.weight(1f))

            ButtonSection(
                state = state.copy(
                    passwordStrength = passwordStrength,
//                    continueButtonState = continueButtonState
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
            text = stringResource(stringR.register),
            style = EDoctorTypography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.outline
            )
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = stringResource(stringR.register_instruction_form),
            style = EDoctorTypography.bodyMedium.copy(color = MaterialTheme.colorScheme.outlineVariant)
        )
    }
}

@Composable
fun RegisterFormSection(
    state: RegisterFormState,
    onUiEvent: (RegisterFormEvent) -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomTextField(
            titleText = stringResource(stringR.full_name),
            hintText = stringResource(stringR.full_name_description),
            text = state.fullNameText,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            onTextChange = {
                onUiEvent(RegisterFormEvent.OnFullNameChanged(it))
            },
            onTextClear = {
                onUiEvent(RegisterFormEvent.OnFullNameChanged(""))
            }
        )
        CustomTextField(
            titleText = stringResource(stringR.email),
            hintText = stringResource(stringR.email_description),
            text = state.emailText,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            onTextChange = {
                onUiEvent(RegisterFormEvent.OnEmailChanged(it))
            },
            onTextClear = {
                onUiEvent(RegisterFormEvent.OnEmailChanged(""))
            }
        )
        CustomTextFieldPassword(
            passwordStrength = state.passwordStrength,
            titleText = stringResource(stringR.password),
            hintText = stringResource(stringR.password_description),
            text = state.passwordText,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            onTextChange = {
                onUiEvent(RegisterFormEvent.OnPasswordChanged(it))
            },
            onTextClear = {
                onUiEvent(RegisterFormEvent.OnPasswordChanged(""))
            }
        )
        CustomTextFieldPassword(
            passwordStrength = PasswordStrength.NONE,
            titleText = stringResource(stringR.confirm_password),
            hintText = stringResource(stringR.confirm_password_description),
            text = state.confirmPasswordText,
            bottomHelperText = if (state.arePasswordsIncompatible) stringResource(stringR.passwords_incompatible) else null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            ),
            onTextChange = {
                onUiEvent(RegisterFormEvent.OnConfirmPasswordChanged(it))
            },
            onTextClear = {
                onUiEvent(RegisterFormEvent.OnConfirmPasswordChanged(""))
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
            textEnabled = stringResource(stringR.continue_),
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
    } else if (!hasSpecialChar) {
        return PasswordStrength.MEDIUM
    } else return PasswordStrength.STRONG
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
            arePasswordsIncompatible = false,
            continueButtonState = ButtonState.DISABLED
        ),
        onUiEvent = { }
    )
}