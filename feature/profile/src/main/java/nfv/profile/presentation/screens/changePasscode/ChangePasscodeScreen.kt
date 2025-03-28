package nfv.profile.presentation.screens.changePasscode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nfv.ui_kit.R
import nfv.ui_kit.components.buttons.keypad.keypad.KeypadAuxiliaryButton
import nfv.ui_kit.components.buttons.keypad.keypad.PasscodeKeypadSection
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.theme.Danger500
import nfv.ui_kit.theme.DefaultScreenPadding
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Success500
import nfv.ui_kit.theme.Typography900
import nfv.ui_kit.R.string as stringR

@Composable
fun ChangePasscodeScreen(
    state: ChangePasscodeState,
    onUiEvent: (ChangePasscodeEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            TopBar(
                headerText = stringResource(stringR.header_change_passcode),
                leadingIcon = IconWithAction(
                    icon = R.drawable.ic_arrow_left,
                    action = {
                        onUiEvent(ChangePasscodeEvent.OnNavigateBack)
                    }
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .padding(top = 20.dp, bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(DefaultScreenPadding),
                text = stringResource(stringR.passcode_description),
                style = EDoctorTypography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(Modifier.weight(1f))
            Text(
                modifier = Modifier.padding(DefaultScreenPadding),
                text = state.directionText,
                style = EDoctorTypography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = state.directionTextColor
                ),
                textAlign = TextAlign.Center
            )

            val scope = rememberCoroutineScope()
            PasscodeKeypadSection(
                keypadAuxiliaryButton = KeypadAuxiliaryButton.CLEAR_ALL,
                pinList = state.pinList,
                onPinChange = {
                    onUiEvent(ChangePasscodeEvent.OnPinListChanged(it))
                    if (it.size == 4) {
                        scope.launch {
                            delay(500)
                            if (state.newPinCode == null) {
                                onUiEvent(ChangePasscodeEvent.OnNewPinCodeUpdated(it))
                                onUiEvent(ChangePasscodeEvent.OnPinListChanged(emptyList()))
                                onUiEvent(ChangePasscodeEvent.OnDirectionTextUpdated("Re-enter the passcode"))   //TODO -> string resource et
                            } else {
                                if (state.newPinCode != it) {
                                    onUiEvent(ChangePasscodeEvent.OnPinListChanged(emptyList()))
                                    onUiEvent(ChangePasscodeEvent.OnDirectionTextUpdated("Passcodes are not same!"))   //TODO -> string resource et
                                    onUiEvent(
                                        ChangePasscodeEvent.OnDirectionTextColorUpdated(
                                            Danger500
                                        )
                                    )   //TODO -> Material resource et
                                    delay(2000)
                                    onUiEvent(ChangePasscodeEvent.OnNewPinCodeUpdated(null))
                                    onUiEvent(ChangePasscodeEvent.OnDirectionTextUpdated("Set a new passcode"))   //TODO -> string resource et
                                    onUiEvent(
                                        ChangePasscodeEvent.OnDirectionTextColorUpdated(
                                            Typography900
                                        )
                                    )   //TODO -> Material resource et
                                } else {
                                    onUiEvent(ChangePasscodeEvent.OnDirectionTextUpdated("New passcode is set!"))   //TODO -> string resource et
                                    onUiEvent(
                                        ChangePasscodeEvent.OnDirectionTextColorUpdated(
                                            Success500
                                        )
                                    )   //TODO -> Material resource et
                                    delay(1000)
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ChangePasscodeScreenPrev() {
    ChangePasscodeScreen(
        state = ChangePasscodeState(
            directionText = "Set a new passcode",
            directionTextColor = Typography900,
            pinList = emptyList(),
            newPinCode = null
        ),
        onUiEvent = {}
    )
}