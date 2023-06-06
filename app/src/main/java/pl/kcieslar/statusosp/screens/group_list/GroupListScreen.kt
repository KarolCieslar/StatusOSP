package pl.kcieslar.statusosp.screens.group_list

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
fun GroupListScreen(
    openScreen: (String) -> Unit,
    viewModel: GroupListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.getUserGroups()
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (topAppBar, groupList, bottomNav) = createRefs()

        CustomTopAppBar(
            modifier = Modifier.constrainAs(topAppBar) {
                top.linkTo(parent.top)
            },
            title = R.string.group_list_screen_toolbar_label
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(groupList) {
                    top.linkTo(topAppBar.bottom)
                }
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(uiState.groupList) {
                GroupCard(it)
            }
        }
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

@Composable
fun GroupCard(group: Group) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                text = group.name,
                color = colorResource(id = R.color.black), fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "Kod dostępu: ${group.code}",
                color = colorResource(id = R.color.darkGray), fontSize = 13.sp
            )
            Text(
                text = "Liczba członków ${group.getUserList().size}",
                color = colorResource(id = R.color.black), fontSize = 13.sp
            )
            Text(
                text = "Aktualnie w gotowości: ${group.getUsersWithReadyStatus().size}",
                color = colorResource(id = R.color.black), fontSize = 13.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupListScreenPreview() {
    StatusOSPTheme {
        GroupListScreen({})
    }
}