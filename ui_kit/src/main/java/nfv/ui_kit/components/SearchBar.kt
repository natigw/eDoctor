package nfv.ui_kit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.R
import nfv.ui_kit.theme.BaseTransparent
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray300
import nfv.ui_kit.theme.Gray500
import nfv.ui_kit.theme.Primary100
import nfv.ui_kit.theme.Primary50
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Typography500
import nfv.ui_kit.theme.Typography900

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    addFilterButton: Boolean = true,
    searchKeywords: String? = null
) {
    Row(
        modifier = modifier
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
                    onClick = {

                    }
                )
                .border(width = 1.dp, color = Gray300, shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                contentDescription = "Search",
                tint = Gray500
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = searchKeywords ?: stringResource(R.string.search___),
                style = EDoctorTypography.bodyMedium.copy(color = Typography500)
            )
        }
        if (addFilterButton) {
            Spacer(Modifier.width(8.dp))
            Icon(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        width = 1.dp,
                        color = Primary100,
                        shape = RoundedCornerShape(8.dp)
                    ) //TODO -> bu shadow olmalidi
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(),
                        onClick = {

                        }
                    )
                    .background(Primary50)
                    .padding(12.dp)
                    .size(24.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_filter),
                contentDescription = stringResource(R.string.filter_search),
                tint = Primary500
            )
        }
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
                indication = rememberRipple(),
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
                text = hintText ?: stringResource(R.string.search___),
                style = EDoctorTypography.bodyMedium.copy(color = Typography500)
            )
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_search),
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
        SearchBar()
        SearchBar(addFilterButton = false, searchKeywords = "Test search")
        SearchBar(addFilterButton = false, searchKeywords = "")
    }
}