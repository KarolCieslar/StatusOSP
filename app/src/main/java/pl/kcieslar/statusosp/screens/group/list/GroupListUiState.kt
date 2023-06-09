package pl.kcieslar.statusosp.screens.group.list

import com.google.firebase.auth.FirebaseAuth
import pl.kcieslar.statusosp.model.objects.Group
import pl.kcieslar.statusosp.model.objects.UserStatus
import pl.kcieslar.statusosp.model.service.enums.DataStatus

data class GroupListUiState(
    private val auth: FirebaseAuth,
    val groupList: List<Group> = listOf(),
    val dataStatus: DataStatus = DataStatus.LOADING
) {
    fun getCurrentUserStatus(): UserStatus {
        val users = groupList.flatMap { it.users.entries }
        return if (users.isNotEmpty()) {
            users.first { (key, _) -> key == auth.uid }.value.status
        } else {
            UserStatus.BUSY
        }
    }

    fun getGroupByCode(code: String): Group {
        return groupList.first { it.code == code }
    }
}