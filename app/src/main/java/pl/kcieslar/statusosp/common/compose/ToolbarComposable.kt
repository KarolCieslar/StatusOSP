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

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.kcieslar.statusosp.R

@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    rightIcon: ImageVector? = null,
    rightIconAction: () -> Unit = {},
    leftIcon: ImageVector? = null,
    leftIconAction: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = colorResource(id = R.color.primary)),
        verticalAlignment = CenterVertically
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            leftIcon?.let {
                IconButton(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .align(CenterStart),
                    onClick = leftIconAction
                ) {
                    Icon(tint = Color.White, imageVector = leftIcon, contentDescription = "LeftAction")
                }
            }

            Text(
                modifier = Modifier.align(Center),
                text = stringResource(title),
                fontSize = 22.sp,
                color = Color.White
            )
            rightIcon?.let {
                IconButton(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .align(CenterEnd),
                    onClick = rightIconAction
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        tint = Color.White, imageVector = rightIcon, contentDescription = "RightAction"
                    )
                }
            }
        }
    }
}
