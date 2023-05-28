package pl.kcieslar.statusosp.screens.auth.login

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.kcieslar.statusosp.FIRST_OPEN_STEPS_SCREEN
import pl.kcieslar.statusosp.LOGIN_SCREEN
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.common.ext.isValidEmail
import pl.kcieslar.statusosp.common.snackbar.SnackbarManager
import pl.kcieslar.statusosp.model.service.AccountService
import pl.kcieslar.statusosp.model.service.FirebaseLogService
import pl.kcieslar.statusosp.screens.StatusOSPViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: FirebaseLogService
) : StatusOSPViewModel(logService) {
    var uiState = mutableStateOf(LoginUiState())
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

    private fun changeCallProcessStatus(newValue: Boolean) {
        uiState.value = uiState.value.copy(isCallInProgress = newValue)
    }

    fun onLoginButtonClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(R.string.input_error_bad_email)
            return
        }

        if (password.isBlank()) {
            SnackbarManager.showMessage(R.string.input_error_bad_password)
            return
        }

        launchCatching(invokeOnCompletion = {
            changeCallProcessStatus(false)
        }) {
            accountService.login(email, password)
            openAndPopUp(FIRST_OPEN_STEPS_SCREEN, LOGIN_SCREEN)
        }
    }

    fun isUserLogged() = accountService.hasUser
}