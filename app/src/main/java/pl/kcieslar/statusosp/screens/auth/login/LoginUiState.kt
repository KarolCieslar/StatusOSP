package pl.kcieslar.statusosp.screens.auth.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isCallInProgress: Boolean = false
)