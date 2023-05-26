package pl.kcieslar.statusosp.screens.auth.password_recovery

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.RECOVERY_PASSWORD_SCREEN
import pl.kcieslar.statusosp.REGISTER_SCREEN
import pl.kcieslar.statusosp.STEP_FIRST_SCREEN
import pl.kcieslar.statusosp.common.ext.isValidEmail
import pl.kcieslar.statusosp.common.ext.passwordMatches
import pl.kcieslar.statusosp.common.snackbar.SnackbarManager
import pl.kcieslar.statusosp.common.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import pl.kcieslar.statusosp.model.service.AccountService
import pl.kcieslar.statusosp.model.service.FirebaseLogService
import pl.kcieslar.statusosp.screens.StatusOSPViewModel
import javax.inject.Inject

@HiltViewModel
class RecoveryPasswordViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: FirebaseLogService
) : StatusOSPViewModel(logService) {
    var uiState = mutableStateOf(RecoveryPasswordUiState())
        private set

    private val email
        get() = uiState.value.email


    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    private fun changeCallProcessStatus(newValue: Boolean) {
        uiState.value = uiState.value.copy(isCallInProgress = newValue)
    }

    fun onRegisterButtonClick() {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(R.string.input_error_bad_email)
            return
        }

        launchCatching(invokeOnCompletion = {
            changeCallProcessStatus(false)
        }) {
            changeCallProcessStatus(true)
            accountService.sendRecoveryEmail(email)
            SnackbarManager.showMessage("Wysłano email z linkiem do zmiany hasła")
        }
    }
}