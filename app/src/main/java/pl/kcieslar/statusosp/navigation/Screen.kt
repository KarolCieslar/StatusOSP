package pl.kcieslar.statusosp.navigation


sealed class Screen(var route: String) {

    object StepFirstScreen : Screen("step_first_screen")
    object StepSecondScreen : Screen("step_second_screen")
    object StepThirdScreen : Screen("step_third_screen")
}