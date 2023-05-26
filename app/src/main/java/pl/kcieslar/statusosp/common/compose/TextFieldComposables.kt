package pl.kcieslar.statusosp.common.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(value: String, onChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true,
        value = value,
        onValueChange = {
            onChange(it)
        },
        label = { Text(text = "Adres email") },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(value: String, onChange: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }
    val icon = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = {
            onChange(it)
        },
        placeholder = { Text(text = "Hasło") },
        label = { Text(text = "Hasło") },
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility = !passwordVisibility
            }) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Visibility Icon"
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (passwordVisibility) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}