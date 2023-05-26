package pl.kcieslar.statusosp.screens.auth.password_recovery

data class RecoveryPasswordUiState(
    val email: String = "",
    val isCallInProgress: Boolean = false
)