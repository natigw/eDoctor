package nfv.profile.presentation.screens.aboutUs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.R
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.theme.DefaultScreenPadding
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.R.drawable as drawableR
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
                headerText = stringResource(stringR.header_about),
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
                text = "About application",
                style = EDoctorTypography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "eDoctor is a mobile healthcare app designed to assist users by providing AI-powered symptom analysis and initial medical advice. Users can input their symptoms to receive possible condition suggestions and recommended next steps. The app aims to promote early detection, reduce unnecessary hospital visits, and improve access to basic medical guidance. Key features include secure chat with AI, health tips, medical result tracking, and personal medical history management. Additionally, the app offers a built-in map to help users locate nearby hospitals, clinics, and pharmacies for timely medical support.",
                style = EDoctorTypography.bodyMedium,
                color = MaterialTheme.colorScheme.outlineVariant
            )
            Spacer(Modifier.height(32.dp))
            Text(
                text = stringResource(stringR.header_about),
                style = EDoctorTypography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Android Developer",
                style = EDoctorTypography.bodyLarge,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    painter = painterResource(drawableR.natig),
                    contentDescription = "Natig Mammadov",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Natig Mammadov",
                    style = EDoctorTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline
                )
            }

            Spacer(Modifier.height(16.dp))
            Text(
                text = "AI Engineer",
                style = EDoctorTypography.bodyLarge,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    painter = painterResource(drawableR.vusat),
                    contentDescription = "Vusat Kematian",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Vusat Kematian Oruclu",
                    style = EDoctorTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline
                )
            }

            Spacer(Modifier.height(16.dp))
            Text(
                text = "Backend Developer",
                style = EDoctorTypography.bodyLarge,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    painter = painterResource(drawableR.farid),
                    contentDescription = "FaridG",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Farid Quli",
                    style = EDoctorTypography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline
                )
            }
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