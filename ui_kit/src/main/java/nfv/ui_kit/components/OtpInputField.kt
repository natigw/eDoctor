package nfv.ui_kit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.theme.Gray300
import nfv.ui_kit.theme.Gray50

@Composable
fun RowScope.OtpInputField(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .weight(1f)
            .border(width = 1.dp, color = Gray300, shape = RoundedCornerShape(12.dp))
            .background(color = Gray50, shape = RoundedCornerShape(12.dp))
    ) {

    }
}

@Preview
@Composable
private fun OtpInputFieldPrev() {
    Row {
        repeat(4) {
            OtpInputField()
        }
    }
}