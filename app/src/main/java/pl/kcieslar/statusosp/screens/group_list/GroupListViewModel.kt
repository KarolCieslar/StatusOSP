package pl.kcieslar.statusosp.screens.group_list

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.kcieslar.statusosp.common.ext.isValidUsername
import pl.kcieslar.statusosp.model.service.FirebaseLogService
import pl.kcieslar.statusosp.model.service.RealtimeDatabaseService
import pl.kcieslar.statusosp.screens.StatusOSPViewModel
import javax.inject.Inject

@HiltViewModel
class GroupListViewModel @Inject constructor(
    private val realtimeDatabaseService: RealtimeDatabaseService,
    logService: FirebaseLogService
) : StatusOSPViewModel(logService) {
    var uiState = mutableStateOf(GroupListUiState())
        private set

    private val username
        get() = uiState.value.groupName


    fun onUsernameChange(newValue: String) {
        uiState.value = uiState.value.copy(groupName = newValue)
    }
}