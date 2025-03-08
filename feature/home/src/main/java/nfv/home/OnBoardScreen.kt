package nfv.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.components.buttons.ActiveButtonLarge
import nfv.ui_kit.components.buttons.ButtonState
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Typography700
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@Composable
fun OnBoardScreen() {
    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f),
            painter = painterResource(drawableR.image_onboard_illustration_1),
            contentDescription = "Onboard screen presentation image",
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(stringR.welcome),
            style = EDoctorTypography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "We will assist you in efficiently and easily scheduling appointments with doctors.\nLet's get started!",
            style = EDoctorTypography.bodyMedium.copy(color = Typography700)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ActiveButtonLarge(
                modifier = Modifier
                    .weight(1f),
                state = ButtonState.LOADING,
                textEnabled = "Next",
                textLoading = "Skip",
                onClick = {

                }
            )
            ActiveButtonLarge(
                modifier = Modifier
                    .weight(1f),
                state = ButtonState.ENABLED,
                textEnabled = "Next",
                onClick = {

                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun OnBoardScreenPrev() {
    OnBoardScreen()
}