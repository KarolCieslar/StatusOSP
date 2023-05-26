package pl.kcieslar.statusosp.screens.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.kcieslar.statusosp.LOGIN_SCREEN
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.RECOVERY_PASSWORD_SCREEN
import pl.kcieslar.statusosp.REGISTER_SCREEN
import pl.kcieslar.statusosp.STEP_FIRST_SCREEN
import pl.kcieslar.statusosp.common.compose.EmailTextField
import pl.kcieslar.statusosp.ui.theme.StatusOSPTheme
import pl.kcieslar.statusosp.common.compose.PasswordTextField
import pl.kcieslar.statusosp.common.compose.PrimaryButton

@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    openScreen: (String) -> Unit = {},
    emailValue: String = "",
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    if (emailValue.isNotEmpty()) {
        viewModel.onEmailChange(emailValue)
    }

    if (viewModel.isUserLogged()) {
        openAndPopUp(STEP_FIRST_SCREEN, LOGIN_SCREEN)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.login_screen_title),
            fontWeight = FontWeight.Bold,
            fontSize = 27.sp,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = stringResource(R.string.login_screen_description))
        Spacer(
            modifier = Modifier.height(15.dp)
        )
        EmailTextField(
            value = uiState.email,
            onChange = { viewModel.onEmailChange(it) })
        Spacer(modifier = Modifier.height(8.dp))
        PasswordTextField(
            value = uiState.password,
            onChange = { viewModel.onPasswordChange(it) })
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    openScreen(RECOVERY_PASSWORD_SCREEN)
                },
            text = stringResource(R.string.login_screen_forgot_password)
        )
        Spacer(modifier = Modifier.height(15.dp))
        PrimaryButton(
            text = stringResource(R.string.login_screen_login_button),
            showProgressBar = uiState.isCallInProgress
        ) { viewModel.onLoginButtonClick(openAndPopUp) }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.clickable {
                openScreen(REGISTER_SCREEN)
            },
            text = stringResource(R.string.login_screen_register)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    StatusOSPTheme {
        LoginScreen(openAndPopUp = { _, _ -> })
    }
}