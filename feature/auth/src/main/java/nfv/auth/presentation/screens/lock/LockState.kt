package nfv.auth.presentation.screens.lock

data class LockState(
    val username: String,
    val pinList: List<Int>
)
