package nfv.auth.presentation.screens.onboard

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import nfv.ui_kit.theme.EDoctorTheme
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@Composable
fun OnBoardScreen(
    state: OnBoardState,
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
            painter = painterResource(state.pages[state.currentPage].image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .padding(DefaultScreenPadding)
        ) {

            Spacer(Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(state.pages.size) {
                    Box(
                        modifier = Modifier
                            .height(4.dp)
                            .width(42.dp)
                            .clip(CircleShape)
                            .background(if (it == state.currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainer)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = state.pages[state.currentPage].title,
                style = EDoctorTypography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.outline
                )
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = state.pages[state.currentPage].description,
                style = EDoctorTypography.bodyMedium.copy(color = MaterialTheme.colorScheme.outlineVariant)
            )

            Spacer(Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (state.currentPage != state.pages.size - 1)    //TODO -> son sehifede bu butonun yoxa cixmasini animasiya ile ede bilmedim
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
    EDoctorTheme {
        OnBoardScreen(
            state = OnBoardState(
                pages = listOf(
                    OnBoardPage(
                        image = drawableR.img_onboard_illustration_1,
                        title = stringResource(stringR.onboard_title1),
                        description = stringResource(stringR.onboard_description1)
                    ),
                    OnBoardPage(
                        image = drawableR.img_onboard_illustration_1,
                        title = stringResource(stringR.onboard_title1),
                        description = stringResource(stringR.onboard_description1)
                    ),
                    OnBoardPage(
                        image = drawableR.img_onboard_illustration_1,
                        title = stringResource(stringR.onboard_title1),
                        description = stringResource(stringR.onboard_description1)
                    )
                ),
                currentPage = 0
            ),
            onUiEvent = { }
        )
    }
}