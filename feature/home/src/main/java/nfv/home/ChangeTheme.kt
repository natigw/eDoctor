package nfv.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.components.buttons.ButtonState
import nfv.ui_kit.components.buttons.ButtonTypes
import nfv.ui_kit.components.buttons.square.ActiveButton
import nfv.ui_kit.components.radioButton.RadioButton
import nfv.ui_kit.theme.BaseWhite
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Primary700
import nfv.ui_kit.theme.Primary900
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

enum class SupportedThemes(val displayName: String) {
    LIGHT("Light"),//stringResource(stringR.light)),  //TODO -> bunu nece edimki stringresource-dan gotursun
    DARK("Dark"),//stringResource(stringR.dark)),
    SYSTEM("System")//stringResource(stringR.system))
}

@Composable
fun ChangeThemeDialog(
    modifier: Modifier = Modifier,
    currentTheme: SupportedThemes,
    onConfirm: (SupportedThemes) -> Unit
) {

    val themeList = listOf(
        ThemeDialogItemData(
            theme = SupportedThemes.LIGHT,
            icon = drawableR.ic_light_mode
        ),
        ThemeDialogItemData(
            theme = SupportedThemes.DARK,
            icon = drawableR.ic_dark_mode
        ),
        ThemeDialogItemData(
            theme = SupportedThemes.SYSTEM,
            icon = drawableR.ic_system_mode
        )
    )

    var selectedTheme by remember { mutableStateOf(currentTheme) }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(BaseWhite)
            .padding(top = 12.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(6.dp)
                .clip(CircleShape)
                .background(Primary500)
        )
        Spacer(Modifier.height(12.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "Choose theme",
            style = EDoctorTypography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Primary700
            )
        )

        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 2.dp)
        ) {
            themeList.forEach { item ->
                ThemeDialogItem(
                    dialogItem = item.copy(isSelected = item.theme == selectedTheme),
                    onClick = {
                        selectedTheme = item.theme
                    }
                )
            }
        }

        ActiveButton(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            buttonType = ButtonTypes.LARGE,
            textEnabled = "Confirm",
            state = ButtonState.ENABLED,
            onClick = {
                onConfirm(selectedTheme)
            }
        )
    }
}

@Composable
fun ThemeDialogItem(
    dialogItem: ThemeDialogItemData,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = onClick
            )
            .padding(horizontal = 12.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = ImageVector.vectorResource(dialogItem.icon),
            contentDescription = dialogItem.theme.displayName,
            tint = Primary500
        )
        Spacer(Modifier.width(10.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = dialogItem.theme.displayName,
            style = EDoctorTypography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = Primary900
            )
        )
        RadioButton(
            isSelected = dialogItem.isSelected,
            onClick = onClick
        )
    }
}

data class ThemeDialogItemData(
    @DrawableRes val icon: Int,
    val theme: SupportedThemes,
    val isSelected: Boolean = false
)

@Preview(showSystemUi = true)
@Composable
private fun ChangeThemeDialogPrev() {
    ChangeThemeDialog(currentTheme = SupportedThemes.DARK, onConfirm = {})
}