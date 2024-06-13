package ml.bubblebath.serverapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ml.bubblebath.serverapp.ui.screen.config.ConfigScreen
import ml.bubblebath.serverapp.ui.screen.logs.LogsScreen
import ml.bubblebath.serverapp.ui.screen.main.MainScreen

fun NavGraphBuilder.buildNavGraph(navController: NavController) {
    composable(NavRoutes.MainScreen.uri) {
        MainScreen(
            modifier = Modifier.fillMaxSize(),
            onConfigClicked = { navController.navigate(NavRoutes.ConfigScreen.uri) },
            onLogsClicked = { navController.navigate(NavRoutes.LogsScreen.uri) })
    }
    composable(NavRoutes.ConfigScreen.uri) {
        ConfigScreen(modifier = Modifier.fillMaxSize())
    }
    composable(NavRoutes.LogsScreen.uri) {
        LogsScreen(modifier = Modifier.fillMaxSize())
    }
}
