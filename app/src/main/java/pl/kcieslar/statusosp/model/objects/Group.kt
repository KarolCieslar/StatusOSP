package pl.kcieslar.statusosp.model.objects

data class Group(
    val code: String = "",
    val name: String = "",
    val description: String = "",
    val adminUID: String = "",
    val users: Map<String, GroupUser> = java.util.HashMap()
) {
    fun getUserList(): List<GroupUser> {
        return this.users.map { it.value }
    }

    fun getUsersWithReadyStatus(): List<GroupUser> {
        return this.users.map { it.value }.filter { it.status ==  GroupUserStatus.READY}
    }
}
