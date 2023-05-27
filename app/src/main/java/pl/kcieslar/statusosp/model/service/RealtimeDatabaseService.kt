package pl.kcieslar.statusosp.model.service

interface RealtimeDatabaseService {
    suspend fun login(email: String, password: String)
}
