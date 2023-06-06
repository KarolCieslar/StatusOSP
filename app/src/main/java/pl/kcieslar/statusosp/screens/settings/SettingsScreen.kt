package pl.kcieslar.statusosp.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import pl.kcieslar.statusosp.GROUP_LIST_SCREEN
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.SETTINGS_SCREEN
import pl.kcieslar.statusosp.common.compose.BottomNavBar
import pl.kcieslar.statusosp.common.compose.CustomTopAppBar
import pl.kcieslar.statusosp.model.objects.Group
import pl.kcieslar.statusosp.ui.theme.StatusOSPTheme

@Composable
fun SettingsScreen(
    openScreen: (String) -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (topAppBar, Settings, bottomNav) = createRefs()

        CustomTopAppBar(
            modifier = Modifier.constrainAs(topAppBar) {
                top.linkTo(parent.top)
            },
            title = R.string.group_list_screen_toolbar_label
        )
        Text(text = "SETTINGS SCREEN")
        BottomNavBar(
            modifier = Modifier.constrainAs(bottomNav) {
                bottom.linkTo(parent.bottom)
            },
            leftItemAction = { openScreen(GROUP_LIST_SCREEN) },
            rightItemAction = { openScreen(SETTINGS_SCREEN) },
            centerItemAction = { },

        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    StatusOSPTheme {
        SettingsScreen({})
    }
}