package nfv.consultation.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.consultation.domain.ConsultationRepository
import nfv.consultation.presentation.model.MessageData
import nfv.navigation.di.Navigator
import nfv.ui_kit.components.buttons.model.ButtonState
import nfv.ui_kit.utils.timeFormatter
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class ConsultationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navigator: Navigator,
    private val repository: ConsultationRepository
) : ViewModel() {

    private val initialQuestionText = savedStateHandle.get<String>("initialQuestion") ?: ""

    val uiState = MutableStateFlow(
        ConsultationState(
            questionText = initialQuestionText,
            conversation = listOf(
                MessageData(
                    fromAi = true,
                    messageBody = "Hello! How can I assist you with your general medical concerns today? Remember, if your issue is serious, please consult a doctor for professional medical help.",
                    time = timeFormatter(Instant.now())
                )
            ),
            sendButtonState = ButtonState.ENABLED
        )
    )

    fun handleEvent(event: ConsultationEvent) {

        when (event) {

            ConsultationEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        popBackStack()
                    }
                }
            }

            is ConsultationEvent.OnQuestionTextChanged -> {
                viewModelScope.launch {
                    uiState.update { old ->
                        old.copy(
                            questionText = event.newValue
                        )
                    }
                }
            }

            ConsultationEvent.OnQuestionAsked -> {
                viewModelScope.launch {

                    val question = uiState.value.questionText

                    if (question.isBlank()) return@launch

                    val newConversationQuestion = uiState.value.conversation + MessageData(
                        fromAi = false,
                        messageBody = question,
                        time = timeFormatter(Instant.now())
                    )
                    uiState.update { old ->
                        old.copy(
                            questionText = "",
                            conversation = newConversationQuestion,
                            sendButtonState = ButtonState.DISABLED
                        )
                    }

                    val answerResponse = repository.consultToAi(question)
                    if (answerResponse != null) {
                        val newConversationAnswer = uiState.value.conversation + MessageData(
                            fromAi = true,
                            messageBody = answerResponse.data,
                            time = timeFormatter(Instant.now())
                        )
                        uiState.update { old ->
                            old.copy(
                                conversation = newConversationAnswer,
                                sendButtonState = ButtonState.ENABLED
                            )
                        }
                    }
                    else {
                        uiState.update { old ->
                            old.copy(
                                sendButtonState = ButtonState.ENABLED
                            )
                        }
                    }
                }
            }
        }

    }

}