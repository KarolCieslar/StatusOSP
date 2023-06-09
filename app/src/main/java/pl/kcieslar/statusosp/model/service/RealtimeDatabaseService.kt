package pl.kcieslar.statusosp.model.service

import kotlinx.coroutines.flow.Flow
import pl.kcieslar.statusosp.model.objects.Group
import pl.kcieslar.statusosp.model.objects.UserStatus
import pl.kcieslar.statusosp.model.service.responses.GroupResponse

interface RealtimeDatabaseService {
    suspend fun savePushNotificationAccept(isAccepted: Boolean)
    suspend fun saveUsername(username: String)
    suspend fun createNewGroup(group: Group)
    suspend fun isGroupExist(code: String): Boolean
    suspend fun getUserGroups(): List<Group>
    suspend fun setUserStatus(userStatus: UserStatus)
    fun getGroupByCode(code: String): Flow<GroupResponse>
}
