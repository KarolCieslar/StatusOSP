package pl.kcieslar.statusosp.common.ext

import android.util.Patterns
import java.util.regex.Pattern

private const val MIN_PASS_LENGTH = 6
private const val MIN_USERNAME_LENGTH = 6
private const val MAX_USERNAME_LENGTH = 40

fun String.isValidEmail(): Boolean {
  return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidUsername(): Boolean {
  return this.isNotBlank() && this.length >= MIN_USERNAME_LENGTH && this.length <= MAX_USERNAME_LENGTH
}

fun String.isValidPassword(): Boolean {
  return this.isNotBlank() && this.length >= MIN_PASS_LENGTH
}

fun String.passwordMatches(repeated: String): Boolean {
  return this == repeated
}

fun String.idFromParameter(): String {
  return this.substring(1, this.length - 1)
}
