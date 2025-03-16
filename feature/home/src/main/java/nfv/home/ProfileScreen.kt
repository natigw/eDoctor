package nfv.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import nfv.ui_kit.R
import nfv.ui_kit.components.buttons.ButtonState
import nfv.ui_kit.components.buttons.ButtonTypes
import nfv.ui_kit.components.buttons.square.DangerButton
import nfv.ui_kit.components.buttons.square.OutlinedButton
import nfv.ui_kit.components.systemBars.BottomBar
import nfv.ui_kit.components.systemBars.BottomBarItemData
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.components.toggle.ToggleButtonSmall
import nfv.ui_kit.theme.BaseWhite
import nfv.ui_kit.theme.DefaultScreenPadding
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray200
import nfv.ui_kit.theme.Gray400
import nfv.ui_kit.theme.Gray500
import nfv.ui_kit.theme.Primary200
import nfv.ui_kit.theme.Primary300
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.Primary800
import nfv.ui_kit.theme.Primary900
import nfv.ui_kit.theme.Typography500
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@Composable
fun ProfileScreen(
    onGoToHome: () -> Unit,
    onGotoHistory: () -> Unit,
    onGoToProfile: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .background(BaseWhite)
            .systemBarsPadding(),
        topBar = {
            TopBar(headerText = "Profile")
        },
        bottomBar = {
            ProfileBottomBar(
                onGoToHome = onGoToHome,
                onGoToHistory = onGotoHistory,
                onGoToProfile = onGoToProfile
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(BaseWhite)
                .verticalScroll(rememberScrollState())
        ) {
            ProfileHeader(
                profileImageLink = "sss",
                userFullName = "Natig Mammadov"
            )

            Spacer(Modifier.height(24.dp))

            OptionGroup(
                titleGroup = "General",
                options = listOf(
                    OptionItemData(
                        icon = drawableR.ic_edit_outlined,
                        title = "Edit profile",
                        currentOption = null,
                        onClick = {}
                    ),
                    OptionItemData(
                        icon = drawableR.ic_globe_outlined,
                        title = "Language",
                        currentOption = "English",
                        onClick = {}
                    ),
                    OptionItemData(
                        icon = drawableR.ic_theme_outlined,
                        title = "Theme",
                        currentOption = "System",
                        onClick = {}
                    )
                )
            )

            Spacer(Modifier.height(28.dp))

            OptionGroup(
                titleGroup = "Security",
                options = listOf(
                    OptionItemData(
                        icon = drawableR.ic_passcode_outlined,
                        title = "Change passcode",
                        currentOption = null,
                        onClick = {}
                    ),
                    OptionItemData(
                        icon = drawableR.ic_fingerprint,
                        title = "Biometrics",
                        currentOption = null,
                        withSwitchButton = true,
                        onClick = {}
                    ),
                    OptionItemData(
                        icon = drawableR.ic_screenshot_outlined,
                        title = "Allow screenshots",
                        currentOption = null,
                        withSwitchButton = true,
                        onClick = {}
                    )
                )
            )

            Spacer(Modifier.height(28.dp))

            OptionGroup(
                titleGroup = "About",
                options = listOf(
                    OptionItemData(
                        icon = drawableR.ic_terms_outlined,
                        title = "Terms and Conditions",
                        currentOption = null,
                        onClick = {}
                    ),
                    OptionItemData(
                        icon = drawableR.ic_info_outlined,
                        title = "About us",
                        currentOption = null,
                        onClick = {}
                    )
                )
            )

            Spacer(Modifier.height(28.dp))

            DangerButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(DefaultScreenPadding),
                buttonType = ButtonTypes.LARGE,
                state = ButtonState.ENABLED,
                textEnabled = "Logout",
                onClick = {

                }
            )
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    profileImageLink: String,
    userFullName: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(DefaultScreenPadding)
            .padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(16.dp))
        Box {
            AsyncImage(
                modifier = Modifier
                    .border(width = 2.5.dp, color = Primary300, shape = CircleShape)
                    .padding(6.dp)
                    .size(100.dp)
                    .clip(CircleShape),
                model = profileImageLink,
                placeholder = painterResource(R.drawable.img_user_profile),
                error = painterResource(R.drawable.img_user_profile),
                contentDescription = stringResource(stringR.description_user_profile_picture),
                contentScale = ContentScale.Crop
            )
            Icon(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(4.dp)
                    .size(24.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(),
                        onClick = {

                        }
                    )
                    .background(color = Gray200, shape = RoundedCornerShape(6.dp))
                    .border(width = 1.dp, color = Gray400, shape = RoundedCornerShape(6.dp))
                    .padding(2.dp),
                imageVector = ImageVector.vectorResource(drawableR.ic_edit),
                contentDescription = stringResource(stringR.description_edit_user_profile_picture),
                tint = Primary500
            )
        }

        Spacer(Modifier.height(12.dp))
        Text(
            text = userFullName,
            style = EDoctorTypography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Primary800
            )
        )

        Spacer(Modifier.height(12.dp))
        OutlinedButton(
            buttonType = ButtonTypes.MEDIUM,
            state = ButtonState.ENABLED,
            textEnabled = "Medical info",
            onClick = {

            }
        )

        Spacer(Modifier.height(16.dp))
        HorizontalDivider(color = Primary200)
    }
}

@Composable
fun ProfileBottomBar(
    modifier: Modifier = Modifier,
    onGoToHome: () -> Unit,
    onGoToHistory: () -> Unit,
    onGoToProfile: () -> Unit
) {
    BottomBar(
        modifier = modifier,
        items = listOf(
            BottomBarItemData(
                icon = R.drawable.ic_home_outlined,
                label = stringResource(R.string.home),
                onClick = onGoToHome
            ),
            BottomBarItemData(
                icon = R.drawable.ic_history_outlined,
                label = stringResource(R.string.history),
                onClick = onGoToHistory
            ),
            BottomBarItemData(
                icon = R.drawable.ic_profile_filled,
                label = stringResource(R.string.profile),
                onClick = onGoToProfile
            )
        ),
        selectedItemIndex = 2
    )
}

data class OptionItemData(
    @DrawableRes val icon: Int,
    val title: String,
    val currentOption: String?,
    val withSwitchButton: Boolean = false,
    val onClick: () -> Unit
)

@Composable
fun OptionItem(
    modifier: Modifier = Modifier,
    optionItem: OptionItemData
) {

    var toggleState by remember { mutableStateOf(true) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false),
                onClick = {
                    if (optionItem.withSwitchButton)
                        toggleState = !toggleState
                    optionItem.onClick
                }
            )
            .padding(vertical = 16.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(24.dp),
            imageVector = ImageVector.vectorResource(optionItem.icon),
            contentDescription = optionItem.title,
            tint = Primary500
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp),
            text = optionItem.title,
            style = EDoctorTypography.bodyLarge,//.copy(fontWeight = FontWeight.Bold),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        optionItem.currentOption?.let {
            Text(
                text = optionItem.currentOption,
                style = EDoctorTypography.bodyMedium.copy(color = Typography500),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (optionItem.withSwitchButton)
            ToggleButtonSmall(
                modifier = Modifier
                    .padding(horizontal = 4.dp),
                isToggleOn = toggleState,
                onToggle = {
                    toggleState = !toggleState
                }
            )
        else
            Icon(
                modifier = Modifier
                    .padding(start = 2.dp, end = 4.dp)
                    .size(24.dp),
                imageVector = ImageVector.vectorResource(drawableR.ic_arrow_right),
                contentDescription = stringResource(stringR.details_option),
                tint = Gray500
            )
    }
}

@Composable
fun OptionGroup(
    modifier: Modifier = Modifier,
    titleGroup: String,
    options: List<OptionItemData>
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp, bottom = 4.dp),
            text = titleGroup,
            style = EDoctorTypography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Primary900
            )
        )
        options.forEachIndexed { index, item ->
            OptionItem(optionItem = item)
            if (index != options.size - 1)
                HorizontalDivider(Modifier.padding(horizontal = 20.dp), color = Gray200)
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPrev() {
    ProfileScreen(
        onGoToHome = {},
        onGotoHistory = {},
        onGoToProfile = {}
    )
}