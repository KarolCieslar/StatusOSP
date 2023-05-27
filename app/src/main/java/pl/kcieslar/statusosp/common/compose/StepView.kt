package pl.kcieslar.statusosp.common.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.kcieslar.statusosp.ui.theme.PrimaryRed
import pl.kcieslar.statusosp.ui.theme.StatusOSPTheme

data class StepViewObject(
    val text: String = "",
    var selectStatus: StepViewSelectStatus = StepViewSelectStatus.NOT_SELECTED
)

enum class StepViewSelectStatus {
    NOT_SELECTED, SELECTED, DONE
}

@Composable
fun StepView(selectedOption: Int) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val stepViewsList = listOf(
            StepViewObject(text = "Twoje dane\nidentyfikujące"),
            StepViewObject(text = "Zgoda na\npowiadomienia"),
            StepViewObject(text = "Załóż lub dołącz\ndo grupy")
        )
        stepViewsList.forEachIndexed { index, it ->
            if (index == selectedOption) it.selectStatus = StepViewSelectStatus.SELECTED
            if (index < selectedOption) it.selectStatus = StepViewSelectStatus.DONE
            if (index > 0) {
                CustomDivider(modifier = Modifier.weight(1f), selectStatus = it.selectStatus == StepViewSelectStatus.SELECTED || it.selectStatus == StepViewSelectStatus.DONE)
            }
            OneStepView(
                modifier = Modifier.weight(1f),
                stepText = index.plus(1).toString(),
                text = it.text,
                selectStatus = it.selectStatus
            )
        }
    }
}

@Composable
fun OneStepView(
    modifier: Modifier,
    stepText: String,
    text: String,
    selectStatus: StepViewSelectStatus
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.CenterHorizontally)
                .border(
                    shape = CircleShape,
                    width = if (selectStatus == StepViewSelectStatus.SELECTED || selectStatus == StepViewSelectStatus.DONE) (-1).dp else 1.dp,
                    color = Color.Black
                )
                .clip(CircleShape)
                .background(if (selectStatus == StepViewSelectStatus.SELECTED || selectStatus == StepViewSelectStatus.DONE) PrimaryRed else Color.White)
        ) {
            if (selectStatus == StepViewSelectStatus.DONE) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(5.dp),
                    tint = Color.White,
                    imageVector = Icons.Default.Done,
                    contentDescription = "Done"
                )
            } else {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stepText,
                    fontSize = 15.sp,
                    color = if (selectStatus == StepViewSelectStatus.SELECTED) Color.White else Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = text,
            fontSize = 13.sp,
            fontWeight = if (selectStatus == StepViewSelectStatus.SELECTED) FontWeight.Bold else null,
            lineHeight = 14.sp,
            color = if (selectStatus == StepViewSelectStatus.SELECTED || selectStatus == StepViewSelectStatus.DONE) PrimaryRed else Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CustomDivider(modifier: Modifier, selectStatus: Boolean) {
    Divider(
        modifier = modifier.padding(top = 13.dp),
        color = if (selectStatus) PrimaryRed else Color.Black,
        thickness = 1.dp
    )
}

@Preview(showBackground = true)
@Composable
fun StepViewPreview() {
    StatusOSPTheme {
        StepView(1)
    }
}