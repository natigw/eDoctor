package nfv.consultation.presentation

import nfv.consultation.presentation.model.MessageData
import nfv.ui_kit.components.buttons.model.ButtonState

data class ConsultationState (
    val questionText: String,
    val conversation: List<MessageData>,
    val sendButtonState: ButtonState
)