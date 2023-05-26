package pl.kcieslar.statusosp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.kcieslar.statusosp.screens.firstopen.StepFirstScreen
import pl.kcieslar.statusosp.screens.firstopen.StepSecondScreen
import pl.kcieslar.statusosp.screens.firstopen.StepThirdScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.StepFirstScreen.route) {
        composable(Screen.StepFirstScreen.route) {
            StepFirstScreen(navController = navController)
        }
        composable(Screen.StepSecondScreen.route) {
            StepSecondScreen(navController = navController)
        }
        composable(Screen.StepThirdScreen.route) {
            StepThirdScreen(navController = navController)
        }
    }
}