package nfv.profile.presentation.screens.medicalInfo

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.R
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.buttons.model.ButtonTypes
import nfv.ui_kit.components.buttons.square.OutlinedButton
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.theme.DefaultScreenPadding
import nfv.ui_kit.theme.EDoctorTypography
import java.util.Date
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@Composable
fun MedicalInfoScreen(
    state: MedicalInfoState,
    onUiEvent: (MedicalInfoEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            TopBar(
                headerText = stringResource(stringR.header_medical_info),
                leadingIcon = IconWithAction(
                    icon = drawableR.ic_arrow_left,
                    action = {
                        onUiEvent(MedicalInfoEvent.OnNavigateBack)
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
                .verticalScroll(rememberScrollState())
                .padding(vertical = 16.dp)
        ) {
            PersonalDetailsSection(
                modifier = Modifier.padding(DefaultScreenPadding),
                state = state,
                onUiEvent = onUiEvent
            )
            Spacer(Modifier.height(16.dp))
            MedicalInfoSection(
                state = state,
                onUiEvent = onUiEvent
            )
        }
    }
}

@Composable
fun PersonalDetailsSection(
    modifier: Modifier,
    state: MedicalInfoState,
    onUiEvent: (MedicalInfoEvent) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = state.userDetails.fullName,
            style = EDoctorTypography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.inversePrimary
            )
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = stringResource(stringR.personal_details),
            style = EDoctorTypography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.outline
            )
        )

        var isExpanded by rememberSaveable { mutableStateOf(false) }
        val rotationDegree by animateFloatAsState(
            targetValue = if (isExpanded) 0f else -180f,
            animationSpec = tween(durationMillis = 300)
        )
        AnimatedVisibility(isExpanded) {
            Column {
                Spacer(Modifier.height(8.dp))
                PersonalDetailsItem(
                    detailItem = PersonalDetailsItemData(
                        title = stringResource(stringR.blood_type),
                        details = "(OR)H+"
                    )
                )
                Spacer(Modifier.height(8.dp))
                PersonalDetailsItem(
                    detailItem = PersonalDetailsItemData(
                        title = stringResource(stringR.sex),
                        details = "Male"
                    )
                )
                Spacer(Modifier.height(8.dp))
                PersonalDetailsItem(
                    detailItem = PersonalDetailsItemData(
                        title = stringResource(stringR.weight),
                        details = "65.2 kg"
                    )
                )
                Spacer(Modifier.height(8.dp))
                PersonalDetailsItem(
                    detailItem = PersonalDetailsItemData(
                        title = stringResource(stringR.date_of_birth),
                        details = "18 Mar 2005"
                    )
                )
                Spacer(Modifier.height(10.dp))
                OutlinedButton(
                    buttonType = ButtonTypes.SMALL,
                    state = ButtonState.ENABLED,
                    startIconRes = drawableR.ic_edit_outlined,
                    textEnabled = stringResource(stringR.edit_details),
                    onClick = {
                        onUiEvent(MedicalInfoEvent.OnEditDetailsClicked)
                    }
                )
            }
        }
        Icon(
            modifier = Modifier
                .rotate(rotationDegree)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = false),
                    onClick = {
                        isExpanded = !isExpanded
                    }
                )
                .animateContentSize(),
            imageVector = ImageVector.vectorResource(drawableR.ic_arrow_up),
            contentDescription = stringResource(stringR.more),
            tint = MaterialTheme.colorScheme.surfaceContainerHigh
        )
    }
}

@Composable
fun PersonalDetailsItem(
    modifier: Modifier = Modifier,
    detailItem: PersonalDetailsItemData
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "${detailItem.title}:",
            style = EDoctorTypography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.outline
            )
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = detailItem.details,
            style = EDoctorTypography.bodyLarge.copy(
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.outlineVariant
            )
        )
    }
}

data class PersonalDetailsItemData(
    val title: String,
    val details: String
)

@Composable
fun MedicalInfoSection(
    state: MedicalInfoState,
    onUiEvent: (MedicalInfoEvent) -> Unit
) {
    Column {
        MedicalInfoItem(
            infoItem = MedicalItemData(
                icon = drawableR.ic_allergy_filled,
                title = stringResource(stringR.allergies),
                recordCount = state.allergies.size,
                onClick = {
                    onUiEvent(MedicalInfoEvent.GoToAllergies)
                }
            )
        )
        Spacer(Modifier.height(10.dp))
        MedicalInfoItem(
            infoItem = MedicalItemData(
                icon = drawableR.ic_diagnosis_filled,
                title = stringResource(stringR.diagnoses_conditions),
                recordCount = state.diagnoses.size,
                onClick = {
                    onUiEvent(MedicalInfoEvent.GoToAllergies)
                }
            )
        )
        Spacer(Modifier.height(10.dp))
        MedicalInfoItem(
            infoItem = MedicalItemData(
                icon = drawableR.ic_medication_filled,
                title = stringResource(stringR.medications_supplements),
                recordCount = state.medications.size,
                onClick = {
                    onUiEvent(MedicalInfoEvent.GoToAllergies)
                }
            )
        )
        Spacer(Modifier.height(10.dp))
        MedicalInfoItem(
            infoItem = MedicalItemData(
                icon = drawableR.ic_tests_filled,
                title = stringResource(stringR.lab_tests),
                recordCount = state.labTests.size,
                onClick = {
                    onUiEvent(MedicalInfoEvent.GoToAllergies)
                }
            )
        )
        Spacer(Modifier.height(10.dp))
        MedicalInfoItem(
            infoItem = MedicalItemData(
                icon = drawableR.ic_doctor_contact_filled,
                title = stringResource(stringR.doctor_contacts),
                recordCount = state.doctorContacts.size,
                onClick = {
                    onUiEvent(MedicalInfoEvent.GoToAllergies)
                }
            )
        )
    }
}

@Composable
fun MedicalInfoItem(
    modifier: Modifier = Modifier,
    infoItem: MedicalItemData
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = {
                    infoItem.onClick
                }
            )
            .background(MaterialTheme.colorScheme.primary)
            .padding(vertical = 16.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .size(32.dp),
            imageVector = ImageVector.vectorResource(infoItem.icon),
            contentDescription = infoItem.title,
            tint = MaterialTheme.colorScheme.onSecondary
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = infoItem.title,
                style = EDoctorTypography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiary
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = if (infoItem.recordCount <= 0) stringResource(stringR.no_records) else "${infoItem.recordCount} ${
                    stringResource(
                        stringR.records
                    )
                }",
                style = EDoctorTypography.labelMedium.copy(color = MaterialTheme.colorScheme.surfaceContainer),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Icon(
            modifier = Modifier
                .padding(start = 4.dp, end = 6.dp)
                .size(24.dp),
            imageVector = ImageVector.vectorResource(drawableR.ic_arrow_right),
            contentDescription = stringResource(R.string.details_option),
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}

data class MedicalItemData(
    @DrawableRes val icon: Int,
    val title: String,
    val recordCount: Int,
    val onClick: () -> Unit
)

@Preview
@Composable
private fun MedicalInfoScreenPrev() {
    MedicalInfoScreen(
        state = MedicalInfoState(
            userDetails = UserDetails(
                fullName = "Cart",
                bloodType = BloodType.FIRST_NEGATIVE,
                sex = Sex.MALE,
                weight = 65.0,
                birthDate = Date()
            ),
            allergies = emptyList(),
            diagnoses = emptyList(),
            medications = emptyList(),
            labTests = emptyList(),
            doctorContacts = emptyList()
        ),
        onUiEvent = {}
    )
}