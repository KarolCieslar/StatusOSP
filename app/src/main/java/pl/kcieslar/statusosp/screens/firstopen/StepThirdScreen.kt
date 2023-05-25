package pl.kcieslar.statusosp.screens.firstopen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.ui.theme.StatusOSPTheme
import pl.kcieslar.statusosp.views.PrimaryButton
import pl.kcieslar.statusosp.views.SecondaryButton
import pl.kcieslar.statusosp.views.StepView

@Composable
fun StepThirdScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        StepView(2)
        Column {
            Row(
                modifier = Modifier.padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(26.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.weight(0.6f),
                    painter = painterResource(id = R.drawable.fireman_ladder),
                    contentDescription = "Strażak",
                    contentScale = ContentScale.Fit
                )
                Text(
                    modifier = Modifier.weight(1f),
                    fontSize = 14.sp,
                    text = "Jeśli Twoja jednostka została już utworzona poproś jej administratora o podanie kodu a następnie dołącz do niej!\n\nMożesz również stworzyć nową grupę dla Twojej jednostki. Będziesz wtedy jej administratorem, który może zarządzać listą członków oraz modyfikować ustawienia grupy."
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            EnterGroupCodeFields()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PrimaryButton(modifier = Modifier.weight(1.2f), text = "Dołącz do grupy") {
                    // TODO: Start connect user to group
                }
                Text(text = "lub", textAlign = TextAlign.Center, modifier = Modifier.weight(0.3f))
                SecondaryButton(modifier = Modifier.weight(0.8f), text = "Stwórz nową") {
                    // TODO: Send user to create new group screen
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun EnterGroupCodeFields() {
    val textCode = remember { mutableStateListOf("", "", "", "", "", "") }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(6) { index ->
            Box(
                modifier = Modifier
                    .size(45.dp),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxSize(),
                    singleLine = true,
                    value = textCode[index],
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Transparent,
                        cursorColor = Color.Transparent
                    ),
                    onValueChange = { value ->
                        if (value.isEmpty() || value.matches(Regex("^\\d+\$"))) {
                            val newValue = value.replace(textCode[index], "")
                            textCode[index] = newValue
                            if (textCode.any { it.isEmpty() }) {
                                focusManager.moveFocus(focusDirection = FocusDirection.Next)
                            } else {
                                keyboardController?.hide()
                                focusManager.clearFocus()
                                // TODO: Connect user to specified group
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Next,
                    ),
                    textStyle = LocalTextStyle.current.copy(fontSize = 15.sp, textAlign = TextAlign.Center),
                )
                Text(
                    text = textCode[index],
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StepThirdScreenPreview() {
    StatusOSPTheme {
        StepThirdScreen()
    }
}