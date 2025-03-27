package nfv.auth.presentation.screens.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.buttons.model.ButtonTypes
import nfv.ui_kit.components.buttons.square.ActiveButton
import nfv.ui_kit.components.buttons.square.DefaultButton
import nfv.ui_kit.theme.DefaultScreenPadding
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@Composable
fun OnBoardScreen(
//    state: OnBoardState,
    onUiEvent: (OnBoardEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f),
            painter = painterResource(drawableR.img_onboard_illustration_1),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .padding(DefaultScreenPadding)
        ) {

            Spacer(Modifier.height(24.dp))



            Spacer(Modifier.height(24.dp))

            Text(
                text = stringResource(stringR.onboard_title1),
                style = EDoctorTypography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.outline
                )
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = stringResource(stringR.onboard_description1),
                style = EDoctorTypography.bodyMedium.copy(color = MaterialTheme.colorScheme.outlineVariant)
            )

            Spacer(Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DefaultButton(
                    modifier = Modifier
                        .weight(1f),
                    buttonType = ButtonTypes.LARGE,
                    state = ButtonState.ENABLED,
                    textEnabled = stringResource(stringR.skip),
                    onClick = {
                        onUiEvent(OnBoardEvent.OnSkipClicked)
                    }
                )
                ActiveButton(
                    modifier = Modifier
                        .weight(1f),
                    buttonType = ButtonTypes.LARGE,
                    state = ButtonState.ENABLED,
                    textEnabled = stringResource(stringR.next),
                    onClick = {
                        onUiEvent(OnBoardEvent.OnNextClicked)
                    }
                )
            }

            Spacer(Modifier.height(45.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnBoardScreenPrev() {
    OnBoardScreen(
//        state = OnBoardState(
//
//        ),
        onUiEvent = { }
    )
}