package pl.kcieslar.statusosp.model.service

import kotlinx.coroutines.flow.Flow
import pl.kcieslar.statusosp.model.objects.Group
import pl.kcieslar.statusosp.model.service.responses.UserGroupsResponse

interface RealtimeDatabaseService {
    suspend fun savePushNotificationAccept(isAccepted: Boolean)
    suspend fun saveUsername(username: String)
    suspend fun createNewGroup(group: Group)
    suspend fun isGroupExist(code: String) : Boolean
    suspend fun getUserGroups(): List<Group>
}
