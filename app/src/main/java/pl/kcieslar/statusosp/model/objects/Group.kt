package pl.kcieslar.statusosp.model.objects

data class Group(
    val code: String = "",
    val name: String = "",
    val description: String = "",
    val adminKey: String = "",
    val users: Map<String, User> = mapOf()
) {
    fun getUserList(): List<User> {
        return this.users.map { it.value }
    }

    fun getUsersWithReadyStatus(): List<User> {
        return this.users.map { it.value }.filter { it.status ==  UserStatus.READY}
    }
}
