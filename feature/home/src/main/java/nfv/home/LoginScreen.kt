package nfv.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.components.IconWithAction
import nfv.ui_kit.components.TopAppBar
import nfv.ui_kit.components.buttons.ButtonState
import nfv.ui_kit.components.buttons.ButtonTypes
import nfv.ui_kit.components.buttons.square.ActiveButton
import nfv.ui_kit.components.buttons.square.OutlinedButton
import nfv.ui_kit.theme.BaseWhite
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray100
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Typography700
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun LoginScreen() {
    Scaffold(
        modifier = Modifier
            .systemBarsPadding(),
        topBar = {
            TopAppBar(
                leadingIcon = IconWithAction(
                    icon = drawableR.ic_arrow_left,
                    action = {
                        //TODO -> navigate back
                    }
                )
            )
        }
    ) { innerPadding ->
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
                text = "Please enter a form to login this app",
                style = EDoctorTypography.bodyMedium.copy(color = Typography700)
            )
            Spacer(Modifier.height(32.dp))

            ActiveButton(
                modifier = Modifier
                    .fillMaxWidth(),
                buttonType = ButtonTypes.LARGE,
                state = ButtonState.DISABLED,
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
                onClick = {

                }
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

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    withStyle(
                        style = EDoctorTypography.labelMedium.copy(color = Typography700)
                            .toSpanStyle()
                    ) {
                        append("Don’t have an account? ")
                    }
                    withStyle(
                        style = EDoctorTypography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Primary500
                        )
                            .toSpanStyle()
                    ) {
                        append("Register")
                    }
                },
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(36.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPrev() {
    LoginScreen()
}