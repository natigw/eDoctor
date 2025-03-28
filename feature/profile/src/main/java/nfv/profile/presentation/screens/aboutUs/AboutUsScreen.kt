package nfv.profile.presentation.screens.aboutUs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.R
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.theme.DefaultScreenPadding
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.R.string as stringR

@Composable
fun AboutUsScreen(
    onUiEvent: (AboutUsEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            TopBar(
                headerText = stringResource(stringR.header_our_team),
                leadingIcon = IconWithAction(
                    icon = R.drawable.ic_arrow_left,
                    action = {
                        onUiEvent(AboutUsEvent.OnNavigateBack)
                    }
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(DefaultScreenPadding)
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(stringR.developers),
                style = EDoctorTypography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview
@Composable
private fun AboutUsScreenPrev() {
    AboutUsScreen(
        onUiEvent = {}
    )
}