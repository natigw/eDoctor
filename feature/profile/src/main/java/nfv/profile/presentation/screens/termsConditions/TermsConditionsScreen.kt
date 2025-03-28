package nfv.profile.presentation.screens.termsConditions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.R
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.theme.DefaultScreenPadding
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.R.string as stringR

@Composable
fun TermsConditionsScreen(
    onUiEvent: (TermsConditionsEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            TopBar(
                headerText = stringResource(stringR.header_terms_conditions),
                leadingIcon = IconWithAction(
                    icon = R.drawable.ic_arrow_left,
                    action = {
                        onUiEvent(TermsConditionsEvent.OnNavigateBack)
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
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(stringR.general_terms),
                style = EDoctorTypography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Welcome to eDoctor! By using our app, you agree to these terms. If you do not agree, please do not use the app.\n" +
                        "\n" +
                        "eDoctor provides general health-related information based on user inputs but does not replace professional medical advice. Always consult a qualified healthcare provider for medical concerns, as we do not guarantee the accuracy of any information provided.\n" +
                        "\n" +
                        "Users must be at least 18 years old or have parental consent. You agree to provide accurate information and not misuse the app, including attempting unauthorized access or violating laws.\n" +
                        "\n" +
                        "Your data is handled according to our Privacy Policy. We collect and process information to improve our services while ensuring confidentiality. We do not store or share sensitive health data without consent.\n" +
                        "\n" +
                        "You are responsible for maintaining the security of your account. Notify us immediately if you suspect unauthorized access. We are not liable for losses due to unauthorized use of your account.\n" +
                        "\n" +
                        "eDoctor is provided \"as is\" without warranties. We are not responsible for any harm, injury, or damages resulting from the use of the app. We reserve the right to modify or discontinue the app at any time. Continued use after updates signifies acceptance of new terms.\n" +
                        "\n" +
                        "We may terminate access if a user violates these terms. Upon termination, all rights granted under these terms will cease immediately. These terms are governed by the laws of Azerbaijan. Any disputes will be subject to the jurisdiction of local courts.\n" +
                        "\n" +
                        "For questions, contact us at natig.mammadov.std@bhos.edu.az",
                style = EDoctorTypography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    withStyle(
                        style = EDoctorTypography.titleSmall.copy(color = MaterialTheme.colorScheme.onPrimary)
                            .toSpanStyle()
                    ) {
                        append(stringResource(stringR.app_version))
                    }
                    withStyle(
                        style = EDoctorTypography.bodyLarge.copy(color = MaterialTheme.colorScheme.outlineVariant)
                            .toSpanStyle()
                    ) {
                        append("\nv1.0.1")
                    }
                }
            )
            Text(
                text = stringResource(stringR.all_rights_reserved),
                style = EDoctorTypography.bodyLarge,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Preview
@Composable
private fun TermsConditionsScreenPrev() {
    TermsConditionsScreen(
        onUiEvent = {}
    )
}