package pl.kcieslar.statusosp.screens.firstopen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import pl.kcieslar.statusosp.ui.theme.StatusOSPTheme
import pl.kcieslar.statusosp.common.compose.StepView
import pl.kcieslar.statusosp.screens.firstopen.steps.StepFirstScreen
import pl.kcieslar.statusosp.screens.firstopen.steps.StepSecondScreen
import pl.kcieslar.statusosp.screens.firstopen.steps.StepThirdScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FirstOpenScreen() {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    BackHandler(enabled = true) {
        if (pagerState.currentPage > 0) {
            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        StepView(pagerState.currentPage)
        HorizontalPager(
            userScrollEnabled = false,
            state = pagerState,
            pageCount = firstOpenPages.size
        ) { pageIndex ->
            when (firstOpenPages[pageIndex]) {
                FirstOpenPage.StepFirst -> StepFirstScreen(onClickNextButton = {
                    scope.launch { pagerState.animateScrollToPage(pageIndex + 1) }
                })

                FirstOpenPage.StepSecond -> StepSecondScreen(onButtonClick = {
                    scope.launch { pagerState.animateScrollToPage(pageIndex + 1) }
                })

                FirstOpenPage.StepThird -> StepThirdScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstOpenScreenPreview() {
    StatusOSPTheme {
        FirstOpenScreen()
    }
}