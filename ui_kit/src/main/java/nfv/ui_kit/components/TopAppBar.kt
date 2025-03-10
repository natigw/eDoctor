package nfv.ui_kit.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import nfv.ui_kit.R
import nfv.ui_kit.theme.BaseBlack
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray100
import nfv.ui_kit.theme.TopBarPadding
import nfv.ui_kit.R.string as stringR

data class IconWithAction(
    @DrawableRes val icon: Int,
    val action: () -> Unit
)

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    headerText: String? = null,
    leadingIcon: IconWithAction? = null,
    trailingIcon: IconWithAction? = null,
    hasBottomLine: Boolean = true
) {
    Column {
        Box(
            modifier = modifier
                .padding(TopBarPadding)
                .fillMaxWidth()
        ) {
            leadingIcon?.let {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = leadingIcon.action
                        ),
                    imageVector = ImageVector.vectorResource(leadingIcon.icon),
                    contentDescription = stringResource(stringR.top_bar_leading_icon),
                    tint = BaseBlack
                )
            }
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = headerText ?: "",
                style = EDoctorTypography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            trailingIcon?.let {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = trailingIcon.action
                        ),
                    imageVector = ImageVector.vectorResource(trailingIcon.icon),
                    contentDescription = stringResource(stringR.top_bar_trailing_icon),
                    tint = BaseBlack
                )
            }
        }
        if (hasBottomLine)
            HorizontalDivider(color = Gray100)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TopAppBarPrev() {
    Column {
        TopAppBar(
            leadingIcon = IconWithAction(
                icon = R.drawable.ic_arrow_left,
                action = {

                }
            )
        )
        TopAppBar(
            headerText = "Test results",
            leadingIcon = IconWithAction(
                icon = R.drawable.ic_arrow_left,
                action = {

                }
            ),
            trailingIcon = IconWithAction(
                icon = R.drawable.ic_search,
                action = {

                }
            )
        )
        TopAppBar(
            headerText = "Test results",
            leadingIcon = IconWithAction(
                icon = R.drawable.ic_arrow_left,
                action = {
                    //
                }
            ),
            hasBottomLine = false
        )
        TopAppBar(
            headerText = "Test results"
        )
    }
}