package nfv.auth.presentation.screens.registerCompletion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.components.buttons.square.ActiveButton
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.buttons.model.ButtonTypes
import nfv.ui_kit.theme.BaseWhite
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Typography200
import nfv.ui_kit.theme.Typography50
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun WelcomeScreen(
    state: WelcomeState,
    onUiEvent: (WelcomeEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(Primary500),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .offset(y = (-100).dp)
                .clip(RoundedCornerShape(20.dp))
                .background(BaseWhite)
                .padding(12.dp)
                .size(64.dp),
            imageVector = ImageVector.vectorResource(drawableR.ic_double_check),
            contentDescription = null,
            tint = Primary500
        )

        Spacer(Modifier.height(24.dp))

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello, ${state.username} \uD83D\uDC4B",
                style = EDoctorTypography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Typography50
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Welcome to eDoctor app!",
                style = EDoctorTypography.bodyMedium.copy(color = Typography200)
            )
        }

        ActiveButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
            buttonType = ButtonTypes.LARGE,
            state = ButtonState.ENABLED,
            textEnabled = "Continue",
            onClick = {
                onUiEvent(WelcomeEvent.OnContinueClicked)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPrev() {
    WelcomeScreen(
        state = WelcomeState(
            username = "Vusat Orujov"
        ),
        onUiEvent = {}
    )
}