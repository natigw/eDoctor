package nfv.ui_kit.components.inputFields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray300
import nfv.ui_kit.theme.Gray50
import nfv.ui_kit.theme.InputFieldShape

@Composable
fun RowScope.OtpInputField(
    modifier: Modifier = Modifier,
    digit: String = ""
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .weight(1f)
            .border(width = 1.dp, color = Gray300, shape = InputFieldShape)
            .background(color = Gray50, shape = InputFieldShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = digit,
            style = EDoctorTypography.bodyLarge,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun OtpInputFieldPrev() {
    Row {
        repeat(4) {
            OtpInputField(
                digit = "1"
            )
        }
    }
}