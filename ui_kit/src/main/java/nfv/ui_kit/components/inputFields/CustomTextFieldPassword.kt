package nfv.ui_kit.components.inputFields

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import nfv.ui_kit.theme.Danger500
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray200
import nfv.ui_kit.theme.Gray300
import nfv.ui_kit.theme.Gray500
import nfv.ui_kit.theme.Info500
import nfv.ui_kit.theme.InputFieldShape
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Success500
import nfv.ui_kit.theme.Typography500
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

enum class PasswordStrength(val value: String) {
    NONE("None"),
    WEAK("Weak"),
    MEDIUM("Medium"),
    STRONG("Strong")
}

@Composable
fun CustomTextFieldPassword(
    modifier: Modifier = Modifier,
    passwordStrength: PasswordStrength,
    titleText: String,
    hintText: String? = null,
    text: String,
    onTextChange: (String) -> Unit,
    onTextClear: () -> Unit,
    onComplete: (String) -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = titleText,
            style = EDoctorTypography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .clip(InputFieldShape)
                .border(width = 1.dp, color = Gray300, shape = InputFieldShape)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                modifier = Modifier.weight(1f),
                value = text,
                onValueChange = onTextChange,
                textStyle = EDoctorTypography.bodyMedium,
                singleLine = true,
                cursorBrush = SolidColor(Typography500),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onComplete(text)
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                ),
                decorationBox = { innerTextField ->
                    if (text.isEmpty()) {
                        Text(
                            text = hintText ?: "",
                            style = EDoctorTypography.bodyMedium,
                            color = Typography500,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    innerTextField()
                },
                visualTransformation =
                if (isPasswordVisible) VisualTransformation.None
                else PasswordVisualTransformation('⬤')
            )

            AnimatedVisibility(
                text.isNotBlank(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
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
                    tint = Gray500
                )
            }

            Spacer(Modifier.width(12.dp))
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(bounded = false),
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }
                    ),
                imageVector = ImageVector.vectorResource(if (isPasswordVisible) drawableR.ic_eye_opened else drawableR.ic_eye_closed),
                contentDescription = stringResource(stringR.description_password_visibility),
                tint = Gray500
            )
        }

        AnimatedVisibility(passwordStrength != PasswordStrength.NONE) {
            PasswordStrengthSection(passwordStrength)
        }
    }
}

@Composable
fun PasswordStrengthSection(
    passwordStrength: PasswordStrength
) {

    val indicatorColor by animateColorAsState(
        targetValue = when (passwordStrength) {
            PasswordStrength.WEAK -> Danger500
            PasswordStrength.MEDIUM -> Info500
            PasswordStrength.STRONG -> Success500
            else -> Primary500
        }
    )

    Spacer(Modifier.height(12.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(start = 4.dp)
                .weight(1f),
            text = passwordStrength.value,
            style = EDoctorTypography.labelMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = indicatorColor
        )
        repeat(passwordStrength.ordinal) {
            Box(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .height(4.dp)
                    .width(48.dp)
                    .clip(CircleShape)
                    .background(indicatorColor)
            )
        }
        repeat(3 - passwordStrength.ordinal) {
            Box(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .height(4.dp)
                    .width(48.dp)
                    .clip(CircleShape)
                    .background(Gray200)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CustomTextFieldPasswordPrev() {

    var state by remember { mutableStateOf(PasswordStrength.NONE) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            state = when (state) {
                PasswordStrength.NONE -> PasswordStrength.WEAK
                PasswordStrength.WEAK -> PasswordStrength.MEDIUM
                PasswordStrength.MEDIUM -> PasswordStrength.STRONG
                PasswordStrength.STRONG -> PasswordStrength.NONE
            }
        }
    }

    Column {
        CustomTextFieldPassword(
            modifier = Modifier.padding(16.dp),
            titleText = "Password",
            hintText = "Enter your password",
            text = "Sample text",
            passwordStrength = state,
            onTextChange = {},
            onTextClear = {},
            onComplete = {}
        )
    }
}