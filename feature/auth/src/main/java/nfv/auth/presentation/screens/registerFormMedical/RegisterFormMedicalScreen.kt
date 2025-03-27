package nfv.auth.presentation.screens.registerFormMedical

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.buttons.model.ButtonTypes
import nfv.ui_kit.components.buttons.square.ActiveButton
import nfv.ui_kit.components.buttons.transparent.ActiveTransparentButton
import nfv.ui_kit.components.inputFields.CustomTextField
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.theme.DefaultScreenPadding
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Typography700
import java.util.Date
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun RegisterFormMedicalScreen(
    state: RegisterFormMedicalState,
    onUiEvent: (RegisterFormMedicalEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .systemBarsPadding(),
        topBar = {
            TopBar(
                leadingIcon = IconWithAction(
                    icon = drawableR.ic_arrow_left,
                    action = {
                        onUiEvent(RegisterFormMedicalEvent.OnNavigateBack)
                    }
                )
            )
        }
    ) { innerPadding ->


//        val isFormValid = state.bloodType &&
//                state.sex &&
//                state.weight &&
//                state.birthDate
        val isFormValid = true
        val registerButtonState = if (isFormValid) ButtonState.ENABLED else ButtonState.DISABLED

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(DefaultScreenPadding)
        ) {

            RegisterMedicalInfoSection(userFullName = state.fullNameText)

            RegisterFormMedicalSection(
                state = state.copy(
                    registerButtonState = registerButtonState
                ),
                onUiEvent = onUiEvent
            )

            Spacer(Modifier.weight(1f))

            ButtonSection(
                state = state.copy(
                    registerButtonState = registerButtonState
                ),
                onUiEvent = onUiEvent
            )
        }
    }
}

@Composable
fun RegisterMedicalInfoSection(
    userFullName: String
) {
    Column(
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        Text(
            text = "Medical details",
            style = EDoctorTypography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "$userFullName, please provide some medical details to complete the registration",
            style = EDoctorTypography.bodyMedium.copy(color = Typography700)
        )
    }
}

@Composable
fun RegisterFormMedicalSection(
    state: RegisterFormMedicalState,
    onUiEvent: (RegisterFormMedicalEvent) -> Unit
) {
    Column(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomTextField(
            titleText = "Blood type",
            hintText = "Enter your blood type",
            text = state.bloodType.notation,
            onTextChange = {
//                onUiEvent(RegisterFormMedicalEvent.OnBloodTypeChanged(it))
            },
            onTextClear = {
//                onUiEvent(RegisterFormMedicalEvent.OnBloodTypeChanged(""))
            }
        )
        CustomTextField(
            titleText = "Sex",
            hintText = "Enter your sex",
            text = state.sex.displayName,
            onTextChange = {
//                onUiEvent(RegisterFormMedicalEvent.OnSexChanged(it))
            },
            onTextClear = {
//                onUiEvent(RegisterFormMedicalEvent.OnSexChanged(""))
            }
        )
        CustomTextField(
            titleText = "Weight",
            hintText = "Enter your weight",
            text = state.weight.toString(),
            onTextChange = {
//                onUiEvent(RegisterFormMedicalEvent.OnWeightChanged(it))
            },
            onTextClear = {
                onUiEvent(RegisterFormMedicalEvent.OnWeightChanged(0.0))
            }
        )
        CustomTextField(
            titleText = "Birth date",
            hintText = "Enter your birth date",
            text = state.birthDate.time.toString(),
            onTextChange = {
//                onUiEvent(RegisterFormMedicalEvent.OnBirthDateChanged(it))
            },
            onTextClear = {
//                onUiEvent(RegisterFormMedicalEvent.OnBirthDateChanged(""))
            }
        )
    }
}

@Composable
fun ButtonSection(
    state: RegisterFormMedicalState,
    onUiEvent: (RegisterFormMedicalEvent) -> Unit
) {
    Column(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ActiveButton(
            modifier = Modifier
                .fillMaxWidth(),
            buttonType = ButtonTypes.LARGE,
            state = state.registerButtonState,
            textEnabled = "Register",
            onClick = {
                onUiEvent(RegisterFormMedicalEvent.OnRegisterClicked)
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
                    style = EDoctorTypography.bodyMedium.copy(color = Typography700)
                        .toSpanStyle()
                ) {
                    append("Have an account? ")
                }
                withStyle(
                    style = EDoctorTypography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Primary500
                    )
                        .toSpanStyle()
                ) {
                    append("Sign in")
                }
            },
            onClick = {
                onUiEvent(RegisterFormMedicalEvent.GoToLoginScreen)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterFormMedicalScreenPrev() {
    RegisterFormMedicalScreen(
        state = RegisterFormMedicalState(
            fullNameText = "",
            bloodType = BloodType.FIRST_NEGATIVE,
            sex = Sex.MALE,
            weight = 0.0,
            birthDate = Date(),
            registerButtonState = ButtonState.DISABLED
        ),
        onUiEvent = { }
    )
}