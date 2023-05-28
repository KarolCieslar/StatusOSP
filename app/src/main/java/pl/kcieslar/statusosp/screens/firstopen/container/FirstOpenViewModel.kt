package pl.kcieslar.statusosp.screens.firstopen.container

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.kcieslar.statusosp.common.ext.isValidGroupName
import pl.kcieslar.statusosp.common.ext.isValidUsername
import pl.kcieslar.statusosp.common.ext.isValidUsernameInputCheck
import pl.kcieslar.statusosp.model.service.FirebaseLogService
import pl.kcieslar.statusosp.model.service.RealtimeDatabaseService
import pl.kcieslar.statusosp.screens.StatusOSPViewModel
import javax.inject.Inject

@HiltViewModel
class FirstOpenViewModel @Inject constructor(
    private val realtimeDatabaseService: RealtimeDatabaseService,
    logService: FirebaseLogService
) : StatusOSPViewModel(logService) {
    var uiState = mutableStateOf(FirstOpenUiState())
        private set

    private val username
        get() = uiState.value.username


    fun onUsernameChange(newValue: String) {
        if (newValue.isValidUsernameInputCheck()) {
            uiState.value = uiState.value.copy(username = newValue)
        }
    }

    fun onNextButtonClick(onClickAction: () -> Unit) {
        uiState.value = uiState.value.copy(isError = !username.isValidUsername())
        if (!username.isValidUsername()) {
            return
        }
        launchCatching {
            realtimeDatabaseService.saveUsername(username)
            onClickAction()
        }
    }

    fun onPushAcceptDeclineButtonClick(accepted: Boolean, onClickAction: () -> Unit) {
        launchCatching {
            realtimeDatabaseService.savePushNotificationAccept(accepted)
            onClickAction()
        }
    }
}