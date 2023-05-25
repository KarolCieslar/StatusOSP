package pl.kcieslar.statusosp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
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
    var isSelected: Boolean = false
)

@Composable
fun StepView(selectedOption: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val stepViewsList = listOf(
            StepViewObject(text = "Twoje dane\nidentyfikujące"),
            StepViewObject(text = "Zgoda na\npowiadomienia"),
            StepViewObject(text = "Załóż lub dołącz\ndo grupy")
        )
        stepViewsList.forEachIndexed { index, it ->
            if (index <= selectedOption) it.isSelected = true
            if (index > 0) {
                CustomDivider(modifier = Modifier.weight(1f), isSelected = it.isSelected)
            }
            OneStepView(
                modifier = Modifier.weight(1f),
                stepText = index.plus(1).toString(),
                text = it.text,
                isSelected = it.isSelected
            )
        }
    }
}

@Composable
fun OneStepView(
    modifier: Modifier,
    stepText: String,
    text: String,
    isSelected: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .size(35.dp)
                .align(Alignment.CenterHorizontally)
                .border(
                    shape = CircleShape,
                    width = if (isSelected) (-1).dp else 1.dp,
                    color = Color.Black
                )
                .clip(CircleShape)
                .background(if (isSelected) PrimaryRed else Color.White)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stepText,
                fontSize = 18.sp,
                color = if (isSelected) Color.White else Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = text,
            fontSize = 13.sp,
            fontWeight = if (isSelected) FontWeight.Bold else null,
            lineHeight = 14.sp,
            color = if (isSelected) PrimaryRed else Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CustomDivider(modifier: Modifier, isSelected: Boolean) {
    Divider(
        modifier = modifier.padding(top = 17.dp),
        color = if (isSelected) PrimaryRed else Color.Black,
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