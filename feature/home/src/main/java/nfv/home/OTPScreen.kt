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
import nfv.ui_kit.components.buttons.square.ActiveButton
import nfv.ui_kit.components.buttons.ButtonState
import nfv.ui_kit.components.buttons.ButtonTypes
import nfv.ui_kit.theme.BaseWhite
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Typography700
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun OTPScreen(
    receiver: String
) {
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
                text = "Send OTP Code",
                style = EDoctorTypography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = EDoctorTypography.bodyMedium.copy(color = Typography700)
                            .toSpanStyle()
                    ) {
                        append("Enter the 6-digit that we have sent via the phone number to ")
                    }
                    withStyle(
                        style = EDoctorTypography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                            .toSpanStyle()
                    ) {
                        append(receiver)
                    }
                }
            )
            Spacer(Modifier.height(32.dp))



            Spacer(Modifier.weight(1f))

            ActiveButton(
                modifier = Modifier
                    .fillMaxWidth(),
                buttonType = ButtonTypes.LARGE,
                state = ButtonState.DISABLED,
                textEnabled = "Continue",
                onClick = {

                }
            )

            Spacer(Modifier.height(32.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    withStyle(
                        style = EDoctorTypography.labelMedium.copy(color = Typography700)
                            .toSpanStyle()
                    ) {
                        append("By signing up or logging in, I accept the apps\n")
                    }
                    withStyle(
                        style = EDoctorTypography.labelMedium.copy(color = Primary500).toSpanStyle()
                    ) {
                        append("Terms of Service")
                    }
                    withStyle(
                        style = EDoctorTypography.labelMedium.copy(color = Typography700)
                            .toSpanStyle()
                    ) {
                        append(" and ")
                    }
                    withStyle(
                        style = EDoctorTypography.labelMedium.copy(color = Primary500).toSpanStyle()
                    ) {
                        append("Privacy Policy")
                    }
                },
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OTPScreenPrev() {
    OTPScreen(
        receiver = "+62 812- 2569 - 2023"
    )
}