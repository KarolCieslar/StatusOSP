package pl.kcieslar.statusosp.screens.auth.password_recovery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.kcieslar.statusosp.LOGIN_SCREEN
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.RECOVERY_PASSWORD_SCREEN
import pl.kcieslar.statusosp.common.compose.EmailTextField
import pl.kcieslar.statusosp.common.compose.PrimaryButton
import pl.kcieslar.statusosp.common.compose.SecondaryButton
import pl.kcieslar.statusosp.ui.theme.StatusOSPTheme

@Composable
fun RecoveryPasswordScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RecoveryPasswordViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.password_recovery_screen_title),
            fontWeight = FontWeight.Bold,
            fontSize = 27.sp,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = stringResource(R.string.password_recovery_screen_description))
        Spacer(modifier = Modifier.height(15.dp))
        EmailTextField(
            value = uiState.email,
            onChange = { viewModel.onEmailChange(it) })
        Spacer(modifier = Modifier.height(15.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            SecondaryButton(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.password_recovery_screen_back_button)
            ) {
                openAndPopUp(LOGIN_SCREEN, RECOVERY_PASSWORD_SCREEN)
            }
            PrimaryButton(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.password_recovery_screen_recovery_button),
                showProgressBar = uiState.isCallInProgress
            ) { viewModel.onRegisterButtonClick() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecoveryPasswordScreenPreview() {
    StatusOSPTheme {
        RecoveryPasswordScreen(openAndPopUp = { _, _ -> })
    }
}