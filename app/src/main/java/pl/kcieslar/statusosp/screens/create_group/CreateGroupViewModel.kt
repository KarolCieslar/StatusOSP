package pl.kcieslar.statusosp.screens.create_group

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import pl.kcieslar.statusosp.CREATE_GROUP_SCREEN
import pl.kcieslar.statusosp.GROUP_LIST_SCREEN
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.common.ext.isValidGroupDescription
import pl.kcieslar.statusosp.common.ext.isValidGroupNameInputCheck
import pl.kcieslar.statusosp.common.ext.isValidUsername
import pl.kcieslar.statusosp.common.snackbar.SnackbarManager
import pl.kcieslar.statusosp.model.objects.Group
import pl.kcieslar.statusosp.model.service.FirebaseLogService
import pl.kcieslar.statusosp.model.service.RealtimeDatabaseService
import pl.kcieslar.statusosp.screens.StatusOSPViewModel
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CreateGroupViewModel @Inject constructor(
    private val realtimeDatabaseService: RealtimeDatabaseService,
    private val auth: FirebaseAuth,
    logService: FirebaseLogService
) : StatusOSPViewModel(logService) {
    var uiState = mutableStateOf(CreateGroupUiState())
        private set

    private val groupName
        get() = uiState.value.groupName

    private val groupCode
        get() = uiState.value.groupCode

    private val groupDescription
        get() = uiState.value.groupDescription


    fun onUsernameChange(newValue: String) {
        if (newValue.isValidGroupNameInputCheck()) {
            uiState.value = uiState.value.copy(groupName = newValue)
        }
    }

    fun onDescriptionChange(newValue: String) {
        if (newValue.isValidGroupDescription()) {
            uiState.value = uiState.value.copy(groupDescription = newValue)
        }
    }

    fun onCreateGroupButtonClick(openAndPopUp: (String, String) -> Unit) {
        if (!groupName.isValidUsername()) {
            SnackbarManager.showMessage(R.string.create_group_bad_name)
            return
        }
        launchCatching {
            val group = Group(groupCode, groupName, groupDescription, auth.uid!!)
            if (realtimeDatabaseService.isGroupExist(groupCode)) {
                generateNewGroupCode()
                return@launchCatching
            }
            realtimeDatabaseService.createNewGroup(group)
            openAndPopUp(GROUP_LIST_SCREEN, CREATE_GROUP_SCREEN)
        }
    }

    fun generateNewGroupCode() {
        launchCatching {
            var generatedCode = generateCodeSixDigit()
            uiState.value = uiState.value.copy(groupCode = generatedCode)
            while (realtimeDatabaseService.isGroupExist(generatedCode)) {
                generatedCode = generateCodeSixDigit()
                uiState.value = uiState.value.copy(groupCode = generatedCode)
            }
        }
    }

    private fun generateCodeSixDigit(): String {
        return (100000 + Random.nextInt(900000)).toString()
    }
}