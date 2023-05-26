package pl.kcieslar.statusosp.screens.firstopen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.ui.theme.StatusOSPTheme
import pl.kcieslar.statusosp.common.compose.PrimaryButton
import pl.kcieslar.statusosp.common.compose.SecondaryButton
import pl.kcieslar.statusosp.common.compose.StepView

@Composable
fun StepSecondScreen(
    openScreen: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        StepView(1)
        Column {
            Row(
                modifier = Modifier.padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(26.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.weight(0.9f),
                    painter = painterResource(id = R.drawable.fireman_cat),
                    contentDescription = "Strażak",
                    contentScale = ContentScale.Fit
                )
                Text(
                    modifier = Modifier.weight(1f),
                    fontSize = 16.sp,
                    text = stringResource(R.string.step_second_screen_description)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            SecondaryButton(modifier = Modifier.weight(1f), text = stringResource(R.string.step_second_screen_decline_button)) {
                // TODO: Send user to StepThirdScreen and set notification to false
            }
            PrimaryButton(modifier = Modifier.weight(1f), text = stringResource(R.string.step_second_screen_accept_button)) {
                // TODO: Send user to StepThirdScreen and set notification to true
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StepSecondScreenPreview() {
    StatusOSPTheme {
        StepSecondScreen(openScreen = {})
    }
}