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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray300
import nfv.ui_kit.theme.InputFieldShape
import nfv.ui_kit.theme.Typography500
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    titleText: String?,
    hintText: String? = null,
    text: String,
    bottomHelperText: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onTextChange: (String) -> Unit,
    onTextClear: () -> Unit
) {

    Column(
        modifier = modifier
    ) {
        titleText?.let {
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = titleText,
                style = EDoctorTypography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(8.dp))
        }

        Row(
            modifier = Modifier
                .clip(InputFieldShape)
                .border(width = 1.dp, color = Gray300, shape = InputFieldShape)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                modifier = Modifier.weight(1f),
                value = if (text != "null") text else "",
                onValueChange = onTextChange,
                textStyle = EDoctorTypography.bodyMedium,
                singleLine = true,
                cursorBrush = SolidColor(Typography500),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                decorationBox = { innerTextField ->
                    if (text.isEmpty() || text == "null") {
                        Text(
                            text = hintText ?: "",
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
                text.isNotBlank() && text != "null",
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Spacer(Modifier.width(8.dp))  //TODO -> bu niye cixmadiki
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

        AnimatedVisibility(bottomHelperText != null) {
            Spacer(Modifier.height(8.dp))
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = bottomHelperText ?: "",
                style = EDoctorTypography.labelMedium,
                color = MaterialTheme.colorScheme.secondaryContainer,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CustomTextFieldPrev() {
    Column {
        CustomTextField(
            modifier = Modifier.padding(16.dp),
            titleText = "Full name",
            hintText = "Enter your full name",
            text = "sample text",
            bottomHelperText = "*error",
            onTextChange = {},
            onTextClear = {}
        )
    }
}