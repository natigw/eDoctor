package nfv.consultation.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import nfv.consultation.presentation.model.MessageData
import nfv.ui_kit.components.buttons.icon.ActiveIconButton
import nfv.ui_kit.components.buttons.icon.model.IconButtonTypes
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.components.inputFields.CustomTextField
import nfv.ui_kit.theme.DefaultScreenPadding
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray200
import nfv.ui_kit.theme.Info500
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun ConsultationScreen(
    state: ConsultationState,
    onUiEvent: (ConsultationEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .systemBarsPadding()
            .imePadding(),
        topBar = {
            ConsultationTopBar()
        },
        bottomBar = {
            ConsultationBottomBar(
                state = state,
                onUiEvent = onUiEvent
            )
        }
    ) { innerPadding ->

        val listState = rememberLazyListState()

        LaunchedEffect(state.conversation.size) {
            if (state.conversation.isNotEmpty()) {
                listState.animateScrollToItem(state.conversation.lastIndex)
            }
        }

        LaunchedEffect(Unit) {
            if (state.questionText.isNotBlank()) {
                delay(500)
                onUiEvent(ConsultationEvent.OnQuestionAsked)
            }
        }

        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(innerPadding)
                .padding(DefaultScreenPadding)
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(state.conversation) {
                if (it.fromAi)
                    MessageBubbleAi(messageItem = it)
                else
                    MessageBubbleUser(messageItem = it)
            }
        }
    }
}

@Composable
fun ConsultationTopBar(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),
            painter = painterResource(drawableR.logo_edoctor),
            contentDescription = null
        )
        Text(
            text = "eDoctor",
            style = EDoctorTypography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.outline
            )
        )
        Text(
            text = "AI Consultation",
            style = EDoctorTypography.bodySmall.copy(
                color = MaterialTheme.colorScheme.outlineVariant
            )
        )
    }
}

@Composable
fun ConsultationBottomBar(
    modifier: Modifier = Modifier,
    state: ConsultationState,
    onUiEvent: (ConsultationEvent) -> Unit
) {
    Row(
        modifier = modifier
            .padding(vertical = 16.dp)
            .padding(DefaultScreenPadding),
        verticalAlignment = Alignment.Bottom
    ) {
        CustomTextField(
            modifier = Modifier.weight(1f),
            titleText = null,
            hintText = "Ask something...",
            text = state.questionText,
            keyboardOptions = KeyboardOptions(
                autoCorrectEnabled = true,
                imeAction = ImeAction.Send
            ),
            keyboardActions = KeyboardActions(
                onSend = {
                    onUiEvent(ConsultationEvent.OnQuestionAsked)
                }
            ),
            onTextChange = {
                onUiEvent(ConsultationEvent.OnQuestionTextChanged(it))
            },
            onTextClear = {
                onUiEvent(ConsultationEvent.OnQuestionTextChanged(""))
            }
        )
        Spacer(Modifier.width(12.dp))
        ActiveIconButton(
            iconRes = drawableR.ic_send,
            buttonType = IconButtonTypes.LARGE,
            state = state.sendButtonState,
            onClick = {
                onUiEvent(ConsultationEvent.OnQuestionAsked)
            }
        )
    }
}

@Composable
fun MessageBubbleUser(
    messageItem: MessageData
) {

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    Row {
        Spacer(Modifier.weight(1f))
        Box(
            modifier = Modifier
                .widthIn(max = (screenWidthDp * 0.7f).dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = 16.dp,
                        bottomEnd = 0.dp
                    )
                )
                .background(Info500)
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = messageItem.messageBody,
                    style = EDoctorTypography.bodyLarge,
                    color = MaterialTheme.colorScheme.onTertiary
                )
                Row {
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = messageItem.time,
                        style = EDoctorTypography.bodyMedium,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
        }
    }
}

@Composable
fun MessageBubbleAi(
    messageItem: MessageData
) {

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    Row {
        Box(
            modifier = Modifier
                .widthIn(max = (screenWidthDp * 0.75f).dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomEnd = 16.dp,
                        bottomStart = 0.dp
                    )
                )
                .background(Gray200)
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = messageItem.messageBody,
                    style = EDoctorTypography.bodyLarge,
                    color = MaterialTheme.colorScheme.outline
                )
                Row {
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = messageItem.time,
                        style = EDoctorTypography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
        Spacer(Modifier.weight(1f))
    }
}

@Preview
@Composable
private fun ConsultationScreenPrev() {
    ConsultationScreen(
        state = ConsultationState(
            questionText = "",
            conversation = emptyList(),
            sendButtonState = ButtonState.ENABLED
        ),
        onUiEvent = {}
    )
}