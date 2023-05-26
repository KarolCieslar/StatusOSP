package pl.kcieslar.statusosp.common.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.ui.theme.StatusOSPTheme

@Composable
fun PrimaryButton(modifier: Modifier = Modifier, text: String, showProgressBar: Boolean = false, clickAction: () -> Unit) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, colorResource(id = R.color.darkRed), RoundedCornerShape(5.dp))
            .height(48.dp),
        shape = RoundedCornerShape(5.dp),
        onClick = { clickAction() },
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primary))
    ) {
        if (showProgressBar) {
            CircularProgressIndicator()
        } else {
            Text(text = text)
        }
    }
}

@Composable
fun SecondaryButton(modifier: Modifier = Modifier, text: String, clickAction: () -> Unit) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, colorResource(id = R.color.gray), RoundedCornerShape(5.dp))
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.white)),
        shape = RoundedCornerShape(5.dp),
        onClick = { clickAction() },
    ) {
        Text(text = text, color = colorResource(id = R.color.black))
    }
}


@Preview(showBackground = true)
@Composable
fun PrimaryButtonPreview() {
    StatusOSPTheme {
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            PrimaryButton(text = "PRZEJDŹ DALEJ") {}
            SecondaryButton(text = "ANULUJ") {}
        }
    }
}