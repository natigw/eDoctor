package nfv.ui_kit.components.inputFields

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.components.buttons.ButtonState
import nfv.ui_kit.components.buttons.icon.FlexibleIconButton
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray300
import nfv.ui_kit.theme.Gray500
import nfv.ui_kit.theme.InputFieldShape
import nfv.ui_kit.theme.Primary100
import nfv.ui_kit.theme.Primary50
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Typography500
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hintText: String? = null,
    onComplete: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Row(
        modifier = modifier
            .clip(InputFieldShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = {

                }
            )
            .border(width = 1.dp, color = Gray300, shape = InputFieldShape)
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
        BasicTextField(
            modifier = Modifier.weight(1f),
            value = text,
            onValueChange = {
                text = it
            },
            textStyle = EDoctorTypography.bodyMedium,
            singleLine = true,
            cursorBrush = SolidColor(Typography500),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onComplete(text)
                    keyboardController?.hide()
//                    focusManager.clearFocus()  //TODO -> focusda problem var: focus qoyanda ne focus getmir ne de keyboard hide olmur ve deyesen tek focus qoyanda da asagidaki textfieldlerden yuxari atir focusu -> bunu fix et ve textden kenara basanda da focus clear olsun
                }
            ),
            decorationBox = { innerTextField ->
                if (text.isEmpty()) {
                    Text(
                        text = hintText ?: stringResource(stringR.search___),
                        style = EDoctorTypography.bodyMedium,
                        color = Typography500,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                innerTextField()
            }
        )

        AnimatedVisibility(
            text.isNotBlank(),
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
                        onClick = {
                            text = ""
                        }
                    ),
                imageVector = ImageVector.vectorResource(drawableR.ic_clear),
                contentDescription = stringResource(stringR.description_clear_button),
                tint = Gray500
            )
        }
    }
}

@Composable
fun SearchBarWithFilterButton(
    modifier: Modifier = Modifier,
    hintText: String? = null,
    onComplete: (String) -> Unit,
    onClickFilterButton: (ButtonState) -> Unit
) {

    Row(
        modifier = modifier
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchBar(
            modifier = Modifier.weight(1f),
            hintText = hintText,
            onComplete = onComplete
        )
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

@Preview(showSystemUi = true)
@Composable
private fun SearchBarPrev() {
    Column {
        SearchBar(
            modifier = Modifier.padding(16.dp),
            hintText = "Test search",
            onComplete = {

            }
        )
        SearchBarWithFilterButton(
            modifier = Modifier.padding(16.dp),
            onComplete = {},
            onClickFilterButton = {}
        )
        SearchBarWithFilterButton(
            modifier = Modifier.padding(16.dp),
            hintText = "",
            onComplete = {},
            onClickFilterButton = {}
        )
    }
}