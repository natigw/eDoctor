package nfv.ui_kit.components.systemBars

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.R.drawable as drawableR

data class BottomBarItemData(
    @DrawableRes val icon: Int,
    val label: String,
    val onClick: () -> Unit
)

@Composable
private fun RowScope.BottomBarItem(
    modifier: Modifier = Modifier,
    bottomItem: BottomBarItemData,
    isSelected: Boolean = false
) {
    Column(
        modifier = modifier
            .weight(1f)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = bottomItem.onClick
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(bottomItem.icon),
            contentDescription = bottomItem.label,
            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = bottomItem.label,
            style = EDoctorTypography.labelMedium.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun BottomBar(
    items: List<BottomBarItemData>,
    selectedItemIndex: Int
) {

    val safeSelectedIndex = selectedItemIndex.coerceIn(0, items.size - 1)

    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)   //TODO -> background burda verilmelidi?
    ) {
        HorizontalDivider(color = MaterialTheme.colorScheme.surfaceContainerLow)

        Row {
            items.forEachIndexed { index, item ->
                BottomBarItem(
                    bottomItem = item,
                    isSelected = index == safeSelectedIndex
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun BottomBarPrev() {
    Column {
        BottomBar(
            items = listOf(
                BottomBarItemData(
                    icon = drawableR.ic_search,
                    label = "Search",
                    onClick = {}
                ),
                BottomBarItemData(
                    icon = drawableR.ic_profile_filled,
                    label = "Profile",
                    onClick = {}
                ),
                BottomBarItemData(
                    icon = drawableR.ic_filter,
                    label = "Search",
                    onClick = {}
                ),
                BottomBarItemData(
                    icon = drawableR.ic_profile_outlined,
                    label = "Profile",
                    onClick = {}
                )
            ),
            selectedItemIndex = 1
        )


        BottomBar(
            items = listOf(
                BottomBarItemData(
                    icon = drawableR.ic_search,
                    label = "Search",
                    onClick = {}
                ),
                BottomBarItemData(
                    icon = drawableR.ic_profile_outlined,
                    label = "Profile",
                    onClick = {}
                ),
                BottomBarItemData(
                    icon = drawableR.ic_filter,
                    label = "Search",
                    onClick = {}
                ),
                BottomBarItemData(
                    icon = drawableR.ic_profile_outlined,
                    label = "Profile",
                    onClick = {}
                )
            ),
            selectedItemIndex = 55   //in case of wrong choice - no error
        )
    }
}