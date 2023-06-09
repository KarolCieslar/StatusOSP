package pl.kcieslar.statusosp.screens.firstopen.container

sealed class FirstOpenPage {
    object StepFirst : FirstOpenPage()
    object StepSecond : FirstOpenPage()
    object StepThird : FirstOpenPage()
}

val firstOpenPages = listOf(
    FirstOpenPage.StepFirst, FirstOpenPage.StepSecond, FirstOpenPage.StepThird
)