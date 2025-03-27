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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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

            var directionText by remember { mutableStateOf("Set a new passcode") }
            var directionTextColor by rememberSaveable { mutableStateOf(Typography900) }
            var pinList by remember { mutableStateOf(listOf<Int>()) }

            var newPinCode by remember { mutableStateOf<List<Int>?>(null) }

            Text(
                modifier = Modifier.padding(DefaultScreenPadding),
                text = "The passcode is a numeric code for quick and easy access to the application.",
                style = EDoctorTypography.bodyMedium
            )
            Spacer(Modifier.weight(1f))
            Text(
                modifier = Modifier.padding(DefaultScreenPadding),
                text = directionText,
                style = EDoctorTypography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = directionTextColor
                ),
                textAlign = TextAlign.Center
            )
            val scope = rememberCoroutineScope()
            PasscodeKeypadSection(
                keypadAuxiliaryButton = KeypadAuxiliaryButton.CLEAR_ALL,
                pinList = pinList,
                onPinChange = {
                    pinList = it
                    if (it.size == 4) {
                        scope.launch {
                            delay(500)
                            if (newPinCode == null) {
                                newPinCode = it
                                pinList = emptyList()
                                directionText = "Re-enter the passcode"
                            } else {
                                if (newPinCode != it) {
                                    pinList = emptyList()
                                    directionText = "Passcodes are not same!"
                                    directionTextColor = Danger500
                                    delay(2000)
                                    newPinCode = null
                                    directionText = "Set a new passcode"
                                    directionTextColor = Typography900
                                } else {
                                    directionText = "New passcode is set!"
                                    directionTextColor = Success500
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

        ),
        onUiEvent = {}
    )
}