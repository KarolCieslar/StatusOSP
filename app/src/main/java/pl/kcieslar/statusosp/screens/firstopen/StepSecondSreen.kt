package pl.kcieslar.statusosp.screens.firstopen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.ui.theme.StatusOSPTheme
import pl.kcieslar.statusosp.views.PrimaryButton
import pl.kcieslar.statusosp.views.SecondaryButton
import pl.kcieslar.statusosp.views.StepView

@Composable
fun StepSecondScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        StepView(1)
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            SecondaryButton(modifier = Modifier.weight(1f), text = "Nie, nie chcę tego") {
                Toast.makeText(context, "Nie zgadzam się!", Toast.LENGTH_SHORT).show()
            }
            PrimaryButton(modifier = Modifier.weight(1f), text = "Zgadzam się") {
                Toast.makeText(context, "Zgadzam się!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstOpenStepSecondScreenPreview() {
    StatusOSPTheme {
        StepSecondScreen()
    }
}