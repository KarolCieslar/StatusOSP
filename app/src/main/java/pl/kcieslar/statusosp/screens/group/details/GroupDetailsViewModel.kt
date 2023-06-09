package pl.kcieslar.statusosp.screens.group.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.kcieslar.statusosp.model.service.FirebaseLogService
import pl.kcieslar.statusosp.model.service.RealtimeDatabaseService
import pl.kcieslar.statusosp.model.service.enums.DataStatus
import pl.kcieslar.statusosp.screens.StatusOSPViewModel
import javax.inject.Inject

@HiltViewModel
class GroupDetailsViewModel @Inject constructor(
    private val realtimeDatabaseService: RealtimeDatabaseService,
    logService: FirebaseLogService
) : StatusOSPViewModel(logService) {
    var uiState = mutableStateOf(GroupDetailsUiState())
        private set

    fun observeGroup(code: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                realtimeDatabaseService.getGroupByCode(code).collect {
                    uiState.value = uiState.value.copy(
                        group = it.group,
                        exception = it.exception,
                        dataStatus = if (it.exception == null) DataStatus.LOADED else DataStatus.ERROR
                    )
                }
            }
        }
    }

}