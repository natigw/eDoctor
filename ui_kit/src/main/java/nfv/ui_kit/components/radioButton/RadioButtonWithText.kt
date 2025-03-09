package nfv.ui_kit.components.radioButton

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.theme.EDoctorTypography

@Composable
fun RadioButtonWithText(
    modifier: Modifier = Modifier,
    text: String,
    isDisabled: Boolean = false,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            isDisabled = isDisabled,
            isSelected = isSelected,
            onClick = onClick
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = text,
            style = EDoctorTypography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun RadioButtonWithTextPrev() {

    var isSelected by remember { mutableStateOf(false) }

    RadioButtonWithText(
        text = "Selection label",
        isSelected = isSelected,
        onClick = {
            isSelected = !isSelected
        }
    )
}