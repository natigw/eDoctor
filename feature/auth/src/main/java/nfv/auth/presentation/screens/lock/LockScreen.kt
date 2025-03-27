package nfv.auth.presentation.screens.lock

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import nfv.ui_kit.components.buttons.keypad.keypad.KeypadAuxiliaryButton
import nfv.ui_kit.components.buttons.keypad.keypad.PasscodeKeypadSection
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.buttons.model.ButtonTypes
import nfv.ui_kit.components.buttons.transparent.ActiveTransparentButton
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Primary300
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun LockScreen() {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.weight(1f))
        Spacer(Modifier.height(0.dp))
        AsyncImage(
            modifier = Modifier
                .border(width = 1.5.dp, color = Primary300, shape = CircleShape)
                .padding(4.dp)
                .size(64.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(context)
                .data("aaa")  //TODO -> backendden link
                .crossfade(true)
                .build(),
            placeholder = rememberVectorPainter(image = ImageVector.vectorResource(drawableR.ic_profile_filled)),
            error = painterResource(drawableR.img_user_profile),
            contentDescription = "User profile picture",
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Username", //TODO -> backendden
            style = EDoctorTypography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.weight(1f))

        val pinList by remember { mutableStateOf(listOf<Int>()) }

        PasscodeKeypadSection(
            keypadAuxiliaryButton = KeypadAuxiliaryButton.FINGERPRINT,
            pinList = pinList,
            onPinChange = {}
        )

        Spacer(Modifier.height(0.dp))
        ActiveTransparentButton(
            buttonType = ButtonTypes.MEDIUM,
            textEnabled = "Forgot your PIN code?",
            state = ButtonState.ENABLED,
            onClick = {

            }
        )
        Spacer(Modifier.height(16.dp))
    }
}

@Preview
@Composable
private fun LockScreenPrev() {
    LockScreen()
}