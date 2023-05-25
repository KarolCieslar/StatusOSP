package pl.kcieslar.statusosp.screens.firstopen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.ui.theme.StatusOSPTheme
import pl.kcieslar.statusosp.views.PrimaryButton
import pl.kcieslar.statusosp.views.StepView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepFirstScreen() {
    var text by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        StepView(0)
        Column {
            Row(
                modifier = Modifier.padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(26.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    fontSize = 16.sp,
                    text = "Zanim przejdziemy dalej musisz się przedstawić. Wpisana przez Ciebie nazwa będzie wyświetlana na listach jednostek do której dołączysz\n\nSwoją nazwę będziesz mógł zmienić w każdej chwili w ustawieniach aplikacji."
                )
                Image(
                    modifier = Modifier.weight(0.5f),
                    painter = painterResource(id = R.drawable.fireman_megaphone),
                    contentDescription = "Strażak",
                    contentScale = ContentScale.Fit
                )
            }
        }
        Column {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                value = text,
                onValueChange = { text = it },
                label = { Text(text = "Imię i nazwisko lub pseudonim") }
            )
            Spacer(modifier = Modifier.height(10.dp))
            PrimaryButton(text = "PRZEJDŹ DALEJ") {
                // TODO: Verify username field and send user to StepSecondScreen + save to Firebase
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StepFirstScreenPreview() {
    StatusOSPTheme {
        StepFirstScreen()
    }
}