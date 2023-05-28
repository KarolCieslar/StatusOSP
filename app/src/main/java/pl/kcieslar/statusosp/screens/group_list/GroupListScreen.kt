package pl.kcieslar.statusosp.screens.group_list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import pl.kcieslar.statusosp.ui.theme.StatusOSPTheme

@Composable
fun GroupListScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: GroupListViewModel = hiltViewModel()
) {
    Text(modifier = Modifier.fillMaxSize(), text = "LISTA GRUP")
}

@Preview(showBackground = true)
@Composable
fun GroupListScreenPreview() {
    StatusOSPTheme {
        GroupListScreen(openAndPopUp = { _, _ -> })
    }
}