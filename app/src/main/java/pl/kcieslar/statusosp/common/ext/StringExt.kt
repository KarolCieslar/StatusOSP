package pl.kcieslar.statusosp.common.ext

import android.util.Patterns
import java.util.regex.Pattern

private const val MIN_PASS_LENGTH = 6
private const val MIN_USERNAME_LENGTH = 6
private const val MAX_USERNAME_LENGTH = 40
private const val MIN_GROUP_NAME_LENGTH = 6
private const val MAX_GROUP_NAME_LENGTH = 40
private const val MAX_GROUP_DESCRIPTION_LENGTH = 120

fun String.isValidEmail(): Boolean {
  return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidUsername(): Boolean {
  return this.isNotBlank() && this.length >= MIN_USERNAME_LENGTH && this.length <= MAX_USERNAME_LENGTH
}

fun String.isValidUsernameInputCheck(): Boolean {
  return this.isNotBlank() && this.length <= MAX_USERNAME_LENGTH
}

fun String.isValidPassword(): Boolean {
  return this.isNotBlank() && this.length >= MIN_PASS_LENGTH
}

fun String.passwordMatches(repeated: String): Boolean {
  return this == repeated
}

fun String.isValidGroupName(): Boolean {
  return this.isNotBlank() && this.length >= MIN_GROUP_NAME_LENGTH && this.length <= MAX_GROUP_NAME_LENGTH
}

fun String.isValidGroupNameInputCheck(): Boolean {
  return this.isNotBlank() && this.length <= MAX_GROUP_NAME_LENGTH
}

fun String.isValidGroupDescription(): Boolean {
  return this.length <= MAX_GROUP_DESCRIPTION_LENGTH
}

fun String.idFromParameter(): String {
  return this.substring(1, this.length - 1)
}
