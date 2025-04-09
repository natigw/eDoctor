package nfv.consultation.presentation.model

data class MessageData(
    val fromAi: Boolean,
    val messageBody: String,
    val time: String
)