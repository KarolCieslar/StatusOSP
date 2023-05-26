package pl.kcieslar.statusosp.screens.auth.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import pl.kcieslar.statusosp.REGISTER_SCREEN
import pl.kcieslar.statusosp.common.compose.EmailTextField
import pl.kcieslar.statusosp.ui.theme.StatusOSPTheme
import pl.kcieslar.statusosp.common.compose.PasswordTextField
import pl.kcieslar.statusosp.common.compose.PrimaryButton

@Composable
fun RegisterScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel()
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
            text = stringResource(R.string.register_screen_title),
            fontWeight = FontWeight.Bold,
            fontSize = 27.sp,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = stringResource(R.string.register_screen_description))
        Spacer(modifier = Modifier.height(15.dp))
        EmailTextField(
            value = uiState.email,
            onChange = { viewModel.onEmailChange(it) })
        Spacer(modifier = Modifier.height(8.dp))
        PasswordTextField(
            value = uiState.password,
            onChange = { viewModel.onPasswordChange(it) })
        Spacer(modifier = Modifier.height(8.dp))
        PasswordTextField(
            value = uiState.repeatPassword,
            onChange = { viewModel.onRepeatPasswordChange(it) })
        Spacer(modifier = Modifier.height(15.dp))
        PrimaryButton(
            text = stringResource(R.string.register_screen_register_button),
            showProgressBar = uiState.isCallInProgress
        ) { viewModel.onRegisterButtonClick(openAndPopUp) }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.clickable { openAndPopUp(LOGIN_SCREEN, REGISTER_SCREEN) },
            text = stringResource(R.string.register_screen_login)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    StatusOSPTheme {
        RegisterScreen(openAndPopUp = { _, _ -> })
    }
}