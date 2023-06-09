package pl.kcieslar.statusosp.screens.group.list

import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.kcieslar.statusosp.model.service.FirebaseLogService
import pl.kcieslar.statusosp.model.service.RealtimeDatabaseService
import pl.kcieslar.statusosp.model.service.enums.DataStatus
import pl.kcieslar.statusosp.screens.StatusOSPViewModel
import javax.inject.Inject

@HiltViewModel
class GroupListViewModel @Inject constructor(
    private val realtimeDatabaseService: RealtimeDatabaseService,
    private val auth: FirebaseAuth,
    logService: FirebaseLogService
) : StatusOSPViewModel(logService) {
    var uiState = mutableStateOf(GroupListUiState(auth))
        private set

    fun getUserGroups() {
        launchCatching {
            uiState.value = uiState.value.copy(groupList = realtimeDatabaseService.getUserGroups(), dataStatus = DataStatus.LOADED)
        }
    }
}