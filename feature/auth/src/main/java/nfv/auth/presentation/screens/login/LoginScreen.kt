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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.buttons.model.ButtonTypes
import nfv.ui_kit.components.buttons.square.ActiveButton
import nfv.ui_kit.components.buttons.square.OutlinedButton
import nfv.ui_kit.components.buttons.transparent.ActiveTransparentButton
import nfv.ui_kit.components.inputFields.CustomTextField
import nfv.ui_kit.components.inputFields.CustomTextFieldPassword
import nfv.ui_kit.components.inputFields.PasswordStrength
import nfv.ui_kit.theme.BaseWhite
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray100
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Typography700
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun LoginScreen(
    onClickGoogleLogin: (ButtonState)-> Unit,
    onClickRegister: (ButtonState)-> Unit
) {
    Scaffold(
        modifier = Modifier
            .systemBarsPadding(),
        topBar = {
            TopBar(
                leadingIcon = IconWithAction(
                    icon = drawableR.ic_arrow_left,
                    action = {
                        //TODO -> navigate back
                    }
                )
            )
        }
    ) { innerPadding ->

        var usernameText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }
        var loginButtonState by remember { mutableStateOf(ButtonState.DISABLED) }

        if (usernameText.isNotBlank() && passwordText.isNotBlank())
            loginButtonState = ButtonState.ENABLED
        else
            loginButtonState = ButtonState.DISABLED

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BaseWhite)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Welcome back",
                style = EDoctorTypography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Please enter credentials to login",
                style = EDoctorTypography.bodyMedium.copy(color = Typography700)
            )
            Spacer(Modifier.height(32.dp))

            CustomTextField(
                titleText = "Username",
                hintText = "Enter your username",
                text = usernameText,
                onTextChange = {
                    usernameText = it
                },
                onTextClear = {
                    usernameText = ""
                },
                onComplete = {
                    //TODO -> butun fieldleri yoxla ve signin ucun request at
                }
            )
            Spacer(Modifier.height(16.dp))
            CustomTextFieldPassword(
                passwordStrength = PasswordStrength.NONE,
                titleText = "Password",
                hintText = "Enter your password",
                text = passwordText,
                onTextChange = {
                    passwordText = it
                },
                onTextClear = {
                    passwordText = ""
                },
                onComplete = {
                    //TODO -> butun fieldleri yoxla ve signin ucun request at
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
                            style = EDoctorTypography.bodyMedium.copy(color = Typography700)
                                .toSpanStyle()
                        ) {
                            append("Forgot Password")
                        }
                    },
                    onClick = {

                    }
                )
            }

            Spacer(Modifier.height(32.dp))
            ActiveButton(
                modifier = Modifier
                    .fillMaxWidth(),
                buttonType = ButtonTypes.LARGE,
                state = loginButtonState,
                textEnabled = "Sign in",
                onClick = {

                }
            )
            Spacer(Modifier.height(16.dp))
            HorizontalDivider(color = Gray100)
            Spacer(Modifier.height(16.dp))
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth(),
                buttonType = ButtonTypes.LARGE,
                state = ButtonState.ENABLED,
                textEnabled = "Sign in with Google",
                startIconRes = drawableR.logo_google,
                onClick = onClickGoogleLogin
            )
            Spacer(Modifier.height(16.dp))
            ActiveButton(
                modifier = Modifier
                    .fillMaxWidth(),
                buttonType = ButtonTypes.LARGE,
                state = ButtonState.ENABLED,
                textEnabled = "Sign in with Facebook",
                startIconRes = drawableR.logo_facebook_outlined,
                onClick = {

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
                        style = EDoctorTypography.bodyMedium.copy(color = Typography700)
                            .toSpanStyle()
                    ) {
                        append("Don’t have an account? ")
                    }
                    withStyle(
                        style = EDoctorTypography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Primary500
                        )
                            .toSpanStyle()
                    ) {
                        append("Register")
                    }
                },
                onClick = onClickRegister
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPrev() {
    LoginScreen(onClickGoogleLogin = { }, onClickRegister = { })
}