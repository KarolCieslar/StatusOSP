package pl.kcieslar.statusosp.screens.firstopen.step_first

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.kcieslar.statusosp.LOGIN_SCREEN
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.STEP_FIRST_SCREEN
import pl.kcieslar.statusosp.STEP_SECOND_SCREEN
import pl.kcieslar.statusosp.common.ext.isValidEmail
import pl.kcieslar.statusosp.common.ext.isValidUsername
import pl.kcieslar.statusosp.common.snackbar.SnackbarManager
import pl.kcieslar.statusosp.model.service.AccountService
import pl.kcieslar.statusosp.model.service.FirebaseLogService
import pl.kcieslar.statusosp.screens.StatusOSPViewModel
import javax.inject.Inject

@HiltViewModel
class StepFirstViewModel @Inject constructor(
    logService: FirebaseLogService
) : StatusOSPViewModel(logService) {
    var uiState = mutableStateOf(StepFirstUiState())
        private set

    private val username
        get() = uiState.value.username


    fun onUsernameChange(newValue: String) {
        uiState.value = uiState.value.copy(username = newValue)
    }

    fun onNextButtonClick(openScreen: (String) -> Unit) {
        uiState.value = uiState.value.copy(isError = !username.isValidUsername())
        if (!username.isValidUsername()) {
            return
        }

        launchCatching {
            openScreen(STEP_SECOND_SCREEN)
        }
    }
}