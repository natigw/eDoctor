package nfv.profile.presentation.screens.changeLanguage

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.svg.SvgDecoder
import nfv.profile.presentation.screens.changeLanguage.model.SupportedLanguages
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.buttons.model.ButtonTypes
import nfv.ui_kit.components.buttons.square.ActiveButton
import nfv.ui_kit.components.radioButton.RadioButton
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun ChangeLanguageDialog(
    modifier: Modifier = Modifier,
    currentLanguage: SupportedLanguages,
    onConfirm: (SupportedLanguages) -> Unit
) {

    val languageList = listOf(
        FlagDialogItemData(
            flagLink = "https://cdn.jsdelivr.net/npm/flag-icon-css/flags/1x1/az.svg",
            language = SupportedLanguages.AZERBAIJANI
        ),
        FlagDialogItemData(
            flagLink = "https://cdn.jsdelivr.net/npm/flag-icon-css/flags/1x1/gb.svg",
            language = SupportedLanguages.ENGLISH
        ),
        FlagDialogItemData(
            flagLink = "https://cdn.jsdelivr.net/npm/flag-icon-css/flags/1x1/ru.svg",
            language = SupportedLanguages.RUSSIAN
        )
    )

    var selectedLanguage by remember { mutableStateOf(currentLanguage) }

    val dialogBackgroundColor = MaterialTheme.colorScheme.surfaceBright //light blue
    val titleColor = MaterialTheme.colorScheme.onPrimary                //a bit dark blue
    val contentColor = MaterialTheme.colorScheme.primary                //primary blue

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
            .background(dialogBackgroundColor)
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(48.dp)
                .height(6.dp)
                .clip(CircleShape)
                .background(contentColor)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "Choose language",
            style = EDoctorTypography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = titleColor
            )
        )

        Column(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 2.dp)
                .verticalScroll(rememberScrollState())
        ) {
            languageList.forEach { item ->
                FlagDialogItem(
                    dialogItem = item.copy(isSelected = item.language == selectedLanguage),
                    onClick = {
                        selectedLanguage = item.language
                    }
                )
            }
        }

        ActiveButton(  //TODO -> nece edimki button hemise fixed asagida dursun data cox olanda gerek en axira scroll edesen
            modifier = Modifier
                .padding(top = 4.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            buttonType = ButtonTypes.LARGE,
            textEnabled = "Confirm",
            state = ButtonState.ENABLED,
            onClick = {
                onConfirm(selectedLanguage)
            }
        )
    }
}

@Composable
fun FlagDialogItem(
    dialogItem: FlagDialogItemData,
    onClick: () -> Unit
) {

    val textColor = MaterialTheme.colorScheme.inversePrimary            //darkest blue

    val context = LocalContext.current

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
        AsyncImage(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(context)
                .data(dialogItem.flagLink)
                .decoderFactory(SvgDecoder.Factory())
                .crossfade(true)
                .build(),
            contentDescription = dialogItem.language.inNative,
            placeholder = rememberVectorPainter(image = ImageVector.vectorResource(drawableR.ic_globe_filled)),
            error = rememberVectorPainter(image = ImageVector.vectorResource(drawableR.ic_globe_filled))
        )
        Spacer(Modifier.width(10.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = dialogItem.language.inNative,
            style = EDoctorTypography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        )
        RadioButton(
            isSelected = dialogItem.isSelected,
            onClick = onClick
        )
    }
}

data class FlagDialogItemData(
    val flagLink: String,
    val language: SupportedLanguages,
    val isSelected: Boolean = false
)

@Preview(showSystemUi = true)
@Composable
private fun ChangeLanguageDialogPrev() {
    ChangeLanguageDialog(currentLanguage = SupportedLanguages.AZERBAIJANI, onConfirm = {})
}