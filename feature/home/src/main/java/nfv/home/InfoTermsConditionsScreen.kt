package nfv.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.R
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.theme.BaseWhite
import nfv.ui_kit.theme.DefaultScreenPadding
import nfv.ui_kit.theme.EDoctorTypography

@Composable
fun InfoTermsConditionsScreen(
    onClickBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            TopBar(
                headerText = "Terms and Conditions",
                leadingIcon = IconWithAction(
                    icon = R.drawable.ic_arrow_left,
                    action = onClickBack
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(BaseWhite)
                .verticalScroll(rememberScrollState())
                .padding(DefaultScreenPadding)
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "Some texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\nSome texts here\n",
                style = EDoctorTypography.bodyMedium
            )
            Text(
                text = "App version",
                style = EDoctorTypography.bodyMedium
            )
            Text(
                text = "v1.0.1",
                style = EDoctorTypography.bodyMedium
            )
            Text(
                text = "All rights reserved. C",
                style = EDoctorTypography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
private fun InfoTermsConditionsScreenPrev() {
    InfoTermsConditionsScreen(onClickBack = {})
}