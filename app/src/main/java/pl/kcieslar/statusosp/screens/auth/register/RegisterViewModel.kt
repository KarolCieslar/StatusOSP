package pl.kcieslar.statusosp.screens.auth.register

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.REGISTER_SCREEN
import pl.kcieslar.statusosp.STEP_FIRST_SCREEN
import pl.kcieslar.statusosp.common.ext.isValidEmail
import pl.kcieslar.statusosp.common.ext.isValidPassword
import pl.kcieslar.statusosp.common.ext.passwordMatches
import pl.kcieslar.statusosp.common.snackbar.SnackbarManager
import pl.kcieslar.statusosp.model.service.AccountService
import pl.kcieslar.statusosp.model.service.FirebaseLogService
import pl.kcieslar.statusosp.screens.StatusOSPViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: FirebaseLogService
) : StatusOSPViewModel(logService) {
    var uiState = mutableStateOf(RegisterUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password


    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    private fun changeCallProcessStatus(newValue: Boolean) {
        uiState.value = uiState.value.copy(isCallInProgress = newValue)
    }

    fun onRegisterButtonClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(R.string.input_error_bad_email)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(R.string.input_error_bad_password)
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            SnackbarManager.showMessage(R.string.input_error_password_not_match)
            return
        }

        launchCatching(invokeOnCompletion = {
            changeCallProcessStatus(false)
        }) {
            changeCallProcessStatus(true)
            accountService.register(email, password)
            accountService.login(email, password)
            openAndPopUp(STEP_FIRST_SCREEN, REGISTER_SCREEN)
        }
    }
}