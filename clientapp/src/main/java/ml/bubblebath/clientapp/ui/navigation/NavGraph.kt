package ml.bubblebath.clientapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ml.bubblebath.clientapp.ui.screen.config.ConfigScreen
import ml.bubblebath.clientapp.ui.screen.main.MainScreen

fun NavGraphBuilder.buildNavGraph(navController: NavController) {
    composable(route = NavRoutes.MainScreen.uri) {
        MainScreen(modifier = Modifier.fillMaxSize(), onConfigClicked = {
            navController.navigate(NavRoutes.ConfigScreen.uri)
        })
    }
    composable(route = NavRoutes.ConfigScreen.uri) {
        ConfigScreen(modifier = Modifier.fillMaxSize())
    }
}