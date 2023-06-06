/*
Copyright 2022 Google LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package pl.kcieslar.statusosp.common.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.kcieslar.statusosp.GROUP_LIST_SCREEN
import pl.kcieslar.statusosp.R
import pl.kcieslar.statusosp.SETTINGS_SCREEN
import pl.kcieslar.statusosp.model.objects.GroupUserStatus
import pl.kcieslar.statusosp.ui.theme.StatusOSPTheme

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    currentScreen: String = GROUP_LIST_SCREEN,
    currentStatus: GroupUserStatus = GroupUserStatus.BUSY,
    leftItemAction: () -> Unit = {},
    rightItemAction: () -> Unit = {},
    centerItemAction: () -> Unit = {}
) {

    val centerItemColor = when (currentStatus) {
        GroupUserStatus.READY -> R.color.green
        GroupUserStatus.BUSY -> R.color.red
        GroupUserStatus.BUSY_READY -> R.color.orange
    }

    val centerItemColorBorder = when (currentStatus) {
        GroupUserStatus.READY -> R.color.darkGreen
        GroupUserStatus.BUSY -> R.color.darkRed
        GroupUserStatus.BUSY_READY -> R.color.darkOragne
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .align(Alignment.BottomCenter)
                .height(65.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomNavItem(
                "Twoje Grupy",
                Icons.Default.List,
                currentScreen == GROUP_LIST_SCREEN
            ) {
                leftItemAction()
            }

            Spacer(modifier = Modifier.width(120.dp))

            BottomNavItem(
                "Ustawienia",
                Icons.Default.Settings,
                currentScreen == SETTINGS_SCREEN
            )
            {
                rightItemAction()
            }
        }

        Column(
            modifier = Modifier
                .align(Center)
                .wrapContentSize(Center)
                .clickable { centerItemAction() },
            horizontalAlignment = CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(1.dp, color = colorResource(centerItemColorBorder), CircleShape)
                    .background(color = colorResource(centerItemColor))
            )
            Spacer(Modifier.height(5.dp))
            Text(
                textAlign = TextAlign.Center,
                lineHeight = 11.sp,
                text = "STATUS TWOJEJ\nGOTOWOŚCI BOJOWEJ",
                fontSize = 11.sp
            )
        }
    }
}

@Composable
private fun BottomNavItem(
    label: String,
    icon: ImageVector,
    isSelected: Boolean,
    leftItemAction: () -> Unit
) {
    Column(
        modifier = Modifier.clickable { leftItemAction() },
        horizontalAlignment = CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            tint = colorResource(id = if (isSelected) R.color.primary else R.color.black),
            imageVector = icon,
            contentDescription = "List Action"
        )
        Text(
            text = label,
            color = colorResource(id = if (isSelected) R.color.primary else R.color.black),
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    StatusOSPTheme {
        BottomNavBar()
    }
}