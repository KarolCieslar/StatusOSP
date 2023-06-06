package pl.kcieslar.statusosp.model.objects

import com.google.firebase.database.PropertyName

data class GroupUser(
    val name: String = "",
    val description: String = "",
    val status: GroupUserStatus = GroupUserStatus.BUSY,
)

enum class GroupUserStatus(val value: String) {
    @PropertyName("READY")
    READY("READY"),

    @PropertyName("BUSY")
    BUSY("BUSY"),

    @PropertyName("BUSY_READY")
    BUSY_READY("BUSY_READY")
}
