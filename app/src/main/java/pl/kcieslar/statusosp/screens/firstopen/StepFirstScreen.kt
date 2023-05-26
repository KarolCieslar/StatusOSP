package pl.kcieslar.statusosp.screens.firstopen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.ui.theme.StatusOSPTheme
import pl.kcieslar.statusosp.common.compose.PrimaryButton
import pl.kcieslar.statusosp.common.compose.StepView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepFirstScreen(
    openScreen: (String) -> Unit = {}
) {
    var text by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }

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
                    text = stringResource(R.string.step_first_screen_description)
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
                isError = isError,
                supportingText = {
                    if (isError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.step_first_screen_input_error),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                trailingIcon = {
                    if (isError)
                        Icon(Icons.Default.Warning, "error", tint = MaterialTheme.colorScheme.error)
                },
                onValueChange = { text = it },
                label = { Text(text = stringResource(R.string.step_first_screen_input_label)) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            PrimaryButton(text = stringResource(R.string.step_first_screen_button)) {

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