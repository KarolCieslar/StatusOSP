package pl.kcieslar.statusosp.model.service

import pl.kcieslar.statusosp.model.objects.Group

interface RealtimeDatabaseService {
    suspend fun savePushNotificationAccept(isAccepted: Boolean)
    suspend fun saveUsername(username: String)
    suspend fun createNewGroup(group: Group)
    suspend fun isGroupExist(code: String) : Boolean
}
