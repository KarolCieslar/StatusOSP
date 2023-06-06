package pl.kcieslar.statusosp.screens.group_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.kcieslar.statusosp.common.ext.isValidUsername
import pl.kcieslar.statusosp.model.objects.Group
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

    private val groupList
        get() = uiState.value.groupList

    fun getUserGroups() {
        launchCatching {
            uiState.value = uiState.value.copy(groupList = realtimeDatabaseService.getUserGroups())
        }
    }
}