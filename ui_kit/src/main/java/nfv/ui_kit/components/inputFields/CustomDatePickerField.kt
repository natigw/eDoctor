package nfv.ui_kit.components.inputFields

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.theme.EDoctorTheme
import nfv.ui_kit.theme.Gray300
import nfv.ui_kit.theme.InputFieldShape
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerField(
    modifier: Modifier = Modifier,
    titleText: String,
    hintText: String? = null,
    text: String,
    onTextChange: (String) -> Unit,
    onTextClear: () -> Unit
) {
    val calendar = Calendar.getInstance()
    var expandedDatePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = calendar.timeInMillis,
        yearRange = 1900..2100
    )

    val formatter = remember {
        SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    }

    Column(modifier = modifier) {
        Text(
            text = titleText,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 4.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .clip(InputFieldShape)
                .border(width = 1.dp, color = Gray300, shape = InputFieldShape)
                .clickable {
                    expandedDatePicker = true
                }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text.ifEmpty { hintText ?: "" },
                style = MaterialTheme.typography.bodyMedium,
                color = if (text.isNotEmpty()) Color.Black else Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )

            AnimatedVisibility(
                text.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Spacer(Modifier.width(8.dp))
                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(),
                            onClick = onTextClear
                        ),
                    imageVector = ImageVector.vectorResource(drawableR.ic_clear),
                    contentDescription = stringResource(stringR.description_clear_button),
                    tint = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            }
        }

        if (expandedDatePicker) {
            DatePickerDialog(
                onDismissRequest = { expandedDatePicker = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val millis = datePickerState.selectedDateMillis
                            if (millis != null) {
                                val formattedDate = formatter.format(Date(millis))
                                onTextChange(formattedDate)
                            }
                            expandedDatePicker = false
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { expandedDatePicker = false }) {
                        Text("Cancel")
                    }
                },
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    headlineContentColor = MaterialTheme.colorScheme.onBackground,
                    weekdayContentColor = MaterialTheme.colorScheme.onBackground,
                    subheadContentColor = MaterialTheme.colorScheme.onBackground,
                )
            ) {
                DatePicker(
                    state = datePickerState,
                    colors = DatePickerDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.onBackground,
                        headlineContentColor = MaterialTheme.colorScheme.onBackground,
                        weekdayContentColor = MaterialTheme.colorScheme.onBackground,
                        subheadContentColor = MaterialTheme.colorScheme.onBackground,
                        yearContentColor = MaterialTheme.colorScheme.onBackground,
                        currentYearContentColor = MaterialTheme.colorScheme.onBackground,
                        selectedYearContentColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                        selectedDayContentColor = MaterialTheme.colorScheme.surfaceContainerLowest
                    ),
                    title = null,
                    headline = null,
                    showModeToggle = false
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun CustomDatePickerFieldPrev() {
    EDoctorTheme {
        CustomDatePickerField(
            modifier = Modifier.padding(16.dp),
            titleText = "Full name",
            hintText = "Enter your full name",
            text = "sample text",
            onTextChange = {},
            onTextClear = {}
        )
    }
}