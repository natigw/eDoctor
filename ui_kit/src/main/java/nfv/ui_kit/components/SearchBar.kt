package nfv.ui_kit.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import nfv.ui_kit.components.buttons.ButtonState
import nfv.ui_kit.components.buttons.icon.FlexibleIconButton
import nfv.ui_kit.theme.BaseTransparent
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray300
import nfv.ui_kit.theme.Gray500
import nfv.ui_kit.theme.Primary100
import nfv.ui_kit.theme.Primary50
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Typography500
import nfv.ui_kit.theme.Typography900
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchKeywords: String? = null,
    onClickSearchBar: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = onClickSearchBar
            )
            .border(width = 1.dp, color = Gray300, shape = RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(drawableR.ic_search),
            contentDescription = stringResource(stringR.description_search),
            tint = Gray500
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = searchKeywords ?: stringResource(stringR.search___),
            style = EDoctorTypography.bodyMedium.copy(color = Typography500),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun SearchBarWithFilterButton(
    modifier: Modifier = Modifier,
    searchKeywords: String? = null,
    onClickSearchBar: () -> Unit,
    onClickFilterButton: (ButtonState) -> Unit
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(),
                    onClick = onClickSearchBar
                )
                .border(width = 1.dp, color = Gray300, shape = RoundedCornerShape(12.dp))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(drawableR.ic_search),
                contentDescription = "Search",
                tint = Gray500
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = searchKeywords ?: stringResource(stringR.search___),
                style = EDoctorTypography.bodyMedium.copy(color = Typography500),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(Modifier.width(8.dp))

        FlexibleIconButton(
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxHeight(),
            state = ButtonState.ENABLED,
            iconRes = drawableR.ic_filter,
            buttonShape = RoundedCornerShape(10.dp),
            buttonBackgroundColor = Primary50,
            borderStrokeColor = Primary100,
            iconColor = Primary500,
            onClick = onClickFilterButton
        )
    }
}

@Composable
fun aa(
    modifier: Modifier = Modifier,
    hintText: String? = null
) {
    var text by remember { mutableStateOf("") }
    TextField(
        modifier = modifier
            .padding(16.dp)
            //.height(48.dp) //TODO -> min height 56.dp deyesen bele altin qoyur
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = {

                }
            )
            .border(width = 1.dp, color = Gray300, shape = RoundedCornerShape(12.dp)),
        value = text,
        onValueChange = {
            text = it
        },
        textStyle = EDoctorTypography.bodyMedium,
        placeholder = {
            Text(
                //TODO -> text ve icon arasinda 12.dp bosluq olmalidi burda defaultda daha cox var
                text = hintText ?: stringResource(stringR.search___),
                style = EDoctorTypography.bodyMedium.copy(color = Typography500)
            )
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(drawableR.ic_search),
                contentDescription = "Search",
                tint = Gray500
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Typography900,
            unfocusedContainerColor = BaseTransparent,
            focusedContainerColor = BaseTransparent,
            unfocusedBorderColor = BaseTransparent,
            focusedBorderColor = BaseTransparent
        )
    )
}

@Preview(showSystemUi = true)
@Composable
private fun SearchBarPrev() {
    Column {
        aa()
        aa()
        SearchBar(onClickSearchBar = { })
        SearchBarWithFilterButton(
            onClickSearchBar = { },
            onClickFilterButton = { }
        )
        SearchBarWithFilterButton(
            searchKeywords = "Test search",
            onClickSearchBar = { },
            onClickFilterButton = { }
        )
        SearchBarWithFilterButton(
            searchKeywords = "",
            onClickSearchBar = { },
            onClickFilterButton = { }
        )
    }
}