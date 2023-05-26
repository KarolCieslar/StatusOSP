package pl.kcieslar.statusosp.screens.auth.register

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isCallInProgress: Boolean = false
)