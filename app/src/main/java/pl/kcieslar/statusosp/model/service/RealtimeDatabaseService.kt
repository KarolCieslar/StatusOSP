package pl.kcieslar.statusosp.model.service

interface RealtimeDatabaseService {
    suspend fun savePushNotificationAccept(isAccepted: Boolean)
    suspend fun saveUsername(username: String)
}
