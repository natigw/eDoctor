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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import nfv.ui_kit.theme.BaseWhite
import nfv.ui_kit.theme.DefaultScreenPadding
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray500
import nfv.ui_kit.theme.Primary50
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Primary900
import nfv.ui_kit.theme.Typography300
import nfv.ui_kit.theme.Typography50
import nfv.ui_kit.theme.Typography700
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun MedicalInfoScreen(
    onClickBack: () -> Unit,
    onClickLabTests: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            TopBar(
                headerText = "Medical info",
                leadingIcon = IconWithAction(
                    icon = drawableR.ic_arrow_left,
                    action = onClickBack
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(BaseWhite)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(16.dp))
            PersonalDetailsSection(
                modifier = Modifier.padding(DefaultScreenPadding)
            )
            Spacer(Modifier.height(16.dp))
            MedicalInfoSection(onClickLabTests = onClickLabTests)
        }
    }
}

@Composable
fun PersonalDetailsSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Natig Mammadov",
            style = EDoctorTypography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Primary900
            )
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Personal details",
            style = EDoctorTypography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
        )

        var isExpanded by remember { mutableStateOf(false) }
        val rotationDegree by animateFloatAsState(
            targetValue = if (isExpanded) 0f else -180f,
            animationSpec = tween(durationMillis = 300)
        )
        AnimatedVisibility(isExpanded) {
            Column {
                Spacer(Modifier.height(8.dp))
                PersonalDetailsItem(
                    detailItem = PersonalDetailsItemData(
                        title = "Blood type",
                        details = "(OR)H+"
                    )
                )
                Spacer(Modifier.height(8.dp))
                PersonalDetailsItem(
                    detailItem = PersonalDetailsItemData(
                        title = "Sex",
                        details = "Male"
                    )
                )
                Spacer(Modifier.height(8.dp))
                PersonalDetailsItem(
                    detailItem = PersonalDetailsItemData(
                        title = "Weight",
                        details = "65.2 kg"
                    )
                )
                Spacer(Modifier.height(8.dp))
                PersonalDetailsItem(
                    detailItem = PersonalDetailsItemData(
                        title = "Date of Birth",
                        details = "18 Mar 2005"
                    )
                )
                Spacer(Modifier.height(10.dp))
                OutlinedButton(
                    buttonType = ButtonTypes.SMALL,
                    state = ButtonState.ENABLED,
                    startIconRes = drawableR.ic_edit_outlined,
                    textEnabled = "Edit details",
                    onClick = {

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
            contentDescription = "More",
            tint = Gray500
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
            style = EDoctorTypography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = detailItem.details,
            style = EDoctorTypography.bodyLarge.copy(
                fontStyle = FontStyle.Italic,
                color = Typography700
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
    modifier: Modifier = Modifier,
    onClickLabTests: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        MedicalInfoItem(
            infoItem = MedicalItemData(
                icon = drawableR.ic_allergy_filled,
                title = "Allergies",
                recordCount = 2,
                onClick = {}
            )
        )
        Spacer(Modifier.height(10.dp))
        MedicalInfoItem(
            infoItem = MedicalItemData(
                icon = drawableR.ic_diagnosis_filled,
                title = "Diagnoses/Conditions",
                recordCount = 4,
                onClick = {}
            )
        )
        Spacer(Modifier.height(10.dp))
        MedicalInfoItem(
            infoItem = MedicalItemData(
                icon = drawableR.ic_medication_filled,
                title = "Medications/Supplements",
                recordCount = 0,
                onClick = {}
            )
        )
        Spacer(Modifier.height(10.dp))
        MedicalInfoItem(
            infoItem = MedicalItemData(
                icon = drawableR.ic_tests_filled,
                title = "Lab tests",
                recordCount = 7,
                onClick = onClickLabTests
            )
        )
        Spacer(Modifier.height(10.dp))
        MedicalInfoItem(
            infoItem = MedicalItemData(
                icon = drawableR.ic_doctor_contact_filled,
                title = "Doctor contacts",
                recordCount = 7,
                onClick = {}
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
            .background(Primary500)
            .padding(vertical = 16.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .size(32.dp),
            imageVector = ImageVector.vectorResource(infoItem.icon),
            contentDescription = infoItem.title,
            tint = Primary50
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
                    color = Typography50
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = if (infoItem.recordCount <= 0) "No records found" else "${infoItem.recordCount} records",
                style = EDoctorTypography.labelMedium.copy(color = Typography300),
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
            tint = Primary50
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
        onClickBack = {},
        onClickLabTests = {}
    )
}