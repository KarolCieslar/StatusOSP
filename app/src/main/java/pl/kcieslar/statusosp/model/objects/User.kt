package pl.kcieslar.statusosp.model.objects

import com.google.firebase.database.PropertyName

data class User(
    val name: String = "",
    val description: String = "",
    val status: UserStatus = UserStatus.BUSY,
)

enum class UserStatus(val value: String) {
    @PropertyName("READY")
    READY("READY"),

    @PropertyName("BUSY")
    BUSY("BUSY")
}
