package pl.kcieslar.statusosp.screens.create_group

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.kcieslar.statusosp.CREATE_GROUP_SCREEN
import pl.kcieslar.statusosp.GROUP_LIST_SCREEN
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.common.compose.CustomTopAppBar
import pl.kcieslar.statusosp.common.compose.PrimaryButton
import pl.kcieslar.statusosp.ui.theme.StatusOSPTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGroupScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: CreateGroupViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.generateGroupCode()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomTopAppBar(
            title = R.string.create_group_screen_create_group_button,
            leftIcon = Icons.Default.Close,
            leftIconAction = { openAndPopUp(GROUP_LIST_SCREEN, CREATE_GROUP_SCREEN) }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    fontSize = 23.sp,
                    letterSpacing = 3.sp,
                    text = stringResource(R.string.create_group_screen_your_group_code)
                )
                Text(
                    fontSize = 32.sp,
                    letterSpacing = 6.sp,
                    text = uiState.groupCode,
                    fontWeight = FontWeight.ExtraBold
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    color = Color.Gray,
                    thickness = 1.dp
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    fontSize = 14.sp,
                    text = stringResource(R.string.create_group_screen_description)
                )
            }
            Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true,
                    value = uiState.groupName,
                    onValueChange = { viewModel.onUsernameChange(it) },
                    label = { Text(text = stringResource(R.string.create_group_name_input_label)) }
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    maxLines = 3,
                    value = uiState.groupDescription,
                    onValueChange = { viewModel.onDescriptionChange(it) },
                    label = { Text(text = stringResource(R.string.create_group_description_input_label)) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.create_group_screen_create_group_button)
                ) {
                    viewModel.onCreateGroupButtonClick(openAndPopUp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateGroupScreenPreview() {
    StatusOSPTheme {
        CreateGroupScreen(openAndPopUp = { _, _ -> })
    }
}