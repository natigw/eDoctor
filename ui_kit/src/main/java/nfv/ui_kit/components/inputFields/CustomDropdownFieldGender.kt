package nfv.ui_kit.components.inputFields

import nfv.ui_kit.model.Gender
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.theme.EDoctorTheme
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray300
import nfv.ui_kit.theme.InputFieldShape
import nfv.ui_kit.theme.Typography500
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@Composable
fun CustomDropdownFieldGender(
    modifier: Modifier = Modifier,
    titleText: String,
    hintText: String? = null,
    selectedOption: String?,
    onOptionSelected: (Gender) -> Unit,
    onClearSelection: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val options = Gender.entries

    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = titleText,
            style = EDoctorTypography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .clip(InputFieldShape)
                .border(width = 1.dp, color = Gray300, shape = InputFieldShape)
                .clickable { expanded = !expanded }
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = selectedOption ?: hintText ?: "",
                style = EDoctorTypography.bodyMedium,
                color = if (selectedOption != null) MaterialTheme.colorScheme.outline else Typography500,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            AnimatedVisibility(
                visible = selectedOption != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(),
                            onClick = {
                                onClearSelection()
                                expanded = false
                            }
                        ),
                    imageVector = ImageVector.vectorResource(drawableR.ic_clear),
                    contentDescription = stringResource(stringR.description_clear_button),
                    tint = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option.notation,
                            style = EDoctorTypography.bodyMedium,
                            color = MaterialTheme.colorScheme.outline,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TextFieldWithDropdownPrev() {
    EDoctorTheme {
        CustomDropdownFieldGender(
            modifier = Modifier.padding(16.dp),
            titleText = "Full name",
            hintText = "Enter your full name",
            selectedOption = "sample text",
            onOptionSelected = {},
            onClearSelection = {}
        )
    }
}