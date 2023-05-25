package pl.kcieslar.statusosp.screens.firstopen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
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
            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Zanim przejdziemy dalej musisz się przedstawić. Wpisana przez Ciebie nazwa będzie wyświetlana na listach jednostek do której dołączysz\n\nSwoją nazwę będziesz mógł zmienić w każdej chwili w ustawieniach aplikacji."
                )
                Image(
                    modifier = Modifier.weight(0.6f),
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "Strażak",
                    contentScale = ContentScale.Crop
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            EnterGroupCodeFields()
            PrimaryButton(text = "Dołącz do grupy") {
                Toast.makeText(context, "Dołącz do grupy", Toast.LENGTH_SHORT).show()
            }
            Text(text = "lub", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            SecondaryButton(text = "Stwórz nową grupę") {
                Toast.makeText(context, "Zakładam nową grupę", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterGroupCodeFields() {
    var textCode = remember { mutableStateListOf("2", "1", "", "", "", "") }
    val focusManager = LocalFocusManager.current

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
                    onValueChange = {
                        if (it.isEmpty() || it.matches(Regex("^\\d+\$"))) {
                            Log.d("asdasdasd", "onValueChange: $it")
                            val newValue = it.replace(textCode[index], "")
                            textCode[index] = newValue
                            focusManager.moveFocus(
                                focusDirection = FocusDirection.Next,
                            )
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
fun FirstOpenStepThirdScreenPreview() {
    StatusOSPTheme {
        StepThirdScreen()
    }
}