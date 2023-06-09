package pl.kcieslar.statusosp.screens.group.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseAuth
import com.valentinilk.shimmer.shimmer
import pl.kcieslar.statusosp.GROUP_DETAILS_SCREEN
import pl.kcieslar.statusosp.GROUP_LIST_SCREEN
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.common.compose.CustomTopAppBar
import pl.kcieslar.statusosp.model.objects.User
import pl.kcieslar.statusosp.model.objects.UserStatus
import pl.kcieslar.statusosp.model.service.enums.DataStatus
import pl.kcieslar.statusosp.ui.theme.PrimaryRed
import pl.kcieslar.statusosp.ui.theme.StatusOSPTheme
import kotlin.random.Random

@Composable
fun GroupDetailsScreen(
    openAndClear: (String) -> Unit,
    groupCode: String,
    viewModel: GroupDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.observeGroup(groupCode)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (topAppBar, groupDetails) = createRefs()

        CustomTopAppBar(
            modifier = Modifier.constrainAs(topAppBar) {
                top.linkTo(parent.top)
            },
            title = uiState.group?.name ?: "Ładowanie...",
            leftIcon = Icons.Default.ArrowBack,
            leftIconAction = { openAndClear(GROUP_LIST_SCREEN) }
        )

        when (uiState.dataStatus) {
            DataStatus.LOADING -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .constrainAs(groupDetails) {
                            top.linkTo(topAppBar.bottom)
                        }
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                ) {
                    items(10) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(colorResource(id = R.color.gray))
                                .height((40 + Random.nextInt(60)).dp)
                                .shimmer(),
                        )
                    }
                }
            }

            DataStatus.LOADED -> {
                uiState.group?.let { group ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .constrainAs(groupDetails) {
                                top.linkTo(topAppBar.bottom)
                            }
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                    ) {
                        items(group.getUserList()) {
                            UserItemView(it, group.adminKey == FirebaseAuth.getInstance().uid)
                        }
                    }
                }
            }

            DataStatus.ERROR -> {
                //TODO:  SHOW ERROR
            }
        }
    }
}

@Composable
fun UserItemView(user: User, isAdminOfGroup: Boolean = false) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundedStatusDot(user.status)
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    text = user.name,
                    color = colorResource(id = R.color.black), fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 17.sp
                )
                if (user.description.isNotEmpty()) {
                    Text(
                        text = user.description,
                        color = colorResource(id = R.color.darkGray), fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            if (isAdminOfGroup) {
                IconButton(
                    modifier = Modifier.padding(start = 5.dp),
                    onClick = { }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black, imageVector = Icons.Outlined.Delete, contentDescription = "DeleteIcon"
                    )
                }
            }
        }
        Divider(
            modifier = Modifier.padding(top = 13.dp),
            color = Color.LightGray,
            thickness = 1.dp
        )
    }
}

@Composable
private fun RoundedStatusDot(status: UserStatus) {
    val centerItemColor = when (status) {
        UserStatus.READY -> R.color.green
        UserStatus.BUSY -> R.color.red
    }

    val centerItemColorBorder = when (status) {
        UserStatus.READY -> R.color.darkGreen
        UserStatus.BUSY -> R.color.darkRed
    }

    Box(
        modifier = Modifier
            .size(17.dp)
            .clip(CircleShape)
            .border(1.dp, color = colorResource(centerItemColorBorder), CircleShape)
            .background(color = colorResource(centerItemColor))
    )
}

@Preview(showBackground = true)
@Composable
fun UserItemViewPreview() {
    StatusOSPTheme {
        UserItemView(User("Karol Cieślar adsdas dasads  dasdsadsadasdas", "Opis usera", UserStatus.BUSY))
    }
}