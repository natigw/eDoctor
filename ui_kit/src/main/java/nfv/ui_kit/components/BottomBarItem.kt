package nfv.ui_kit.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray400
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Typography400
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun RowScope.BottomBarItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .weight(1f)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = label,
            tint = if (isSelected) Primary500 else Gray400
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = label,
            style = EDoctorTypography.labelMedium.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) Primary500 else Typography400
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun BottomBarItemPrev() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        BottomBarItem(
            icon = drawableR.ic_search,
            label = "Search",
            isSelected = true,
            onClick = {

            }
        )
        BottomBarItem(
            icon = drawableR.ic_profile_outlined,
            label = "Profile",
            isSelected = false,
            onClick = {

            }
        )
    }
}