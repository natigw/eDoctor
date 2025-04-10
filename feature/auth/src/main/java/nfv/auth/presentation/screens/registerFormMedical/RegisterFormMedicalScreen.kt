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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.buttons.model.ButtonTypes
import nfv.ui_kit.components.buttons.square.ActiveButton
import nfv.ui_kit.components.buttons.transparent.ActiveTransparentButton
import nfv.ui_kit.components.inputFields.CustomDatePickerField
import nfv.ui_kit.components.inputFields.CustomDropdownFieldBlood
import nfv.ui_kit.components.inputFields.CustomDropdownFieldGender
import nfv.ui_kit.components.inputFields.CustomTextField
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.model.BloodType
import nfv.ui_kit.model.Gender
import nfv.ui_kit.theme.DefaultScreenPadding
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Typography700
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
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
                state = state,
                onUiEvent = onUiEvent
            )

            Spacer(Modifier.weight(1f))

            ButtonSection(
                state = state,
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
        CustomDropdownFieldBlood(
            titleText = "Blood type",
            hintText = "Enter your blood type",
            selectedOption = state.bloodType?.notation,
            onOptionSelected = {
                onUiEvent(RegisterFormMedicalEvent.OnBloodTypeChanged(it))
            },
            onClearSelection = {
                onUiEvent(RegisterFormMedicalEvent.OnBloodTypeChanged(null))
            }
        )

        CustomDropdownFieldGender(
            titleText = "Gender",
            hintText = "Enter your gender",
            selectedOption = state.gender?.notation,
            onOptionSelected = {
                onUiEvent(RegisterFormMedicalEvent.OnGenderChanged(it))
            },
            onClearSelection = {
                onUiEvent(RegisterFormMedicalEvent.OnGenderChanged(null))
            }
        )
        CustomTextField(
            titleText = "Weight",
            hintText = "Enter your weight",
            text = state.weight.toString(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            ),
            onTextChange = {
                onUiEvent(RegisterFormMedicalEvent.OnWeightChanged(it.toDouble()))
            },
            onTextClear = {
                onUiEvent(RegisterFormMedicalEvent.OnWeightChanged(null))
            }
        )
        CustomDatePickerField(
            titleText = "Birth date",
            hintText = "Enter your birth date",
            text = state.birthDate?.let {
                SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(it)
            } ?: "",
            onTextChange = {
                val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).parse(it)
                onUiEvent(RegisterFormMedicalEvent.OnBirthDateChanged(date))
            },
            onTextClear = {
                onUiEvent(RegisterFormMedicalEvent.OnBirthDateChanged(null))
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
            state = state.continueButtonState,
            textEnabled = "Continue",
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
            gender = Gender.MALE,
            weight = 0.0,
            birthDate = Date(),
            continueButtonState = ButtonState.DISABLED
        ),
        onUiEvent = { }
    )
}