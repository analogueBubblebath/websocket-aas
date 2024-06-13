package ml.bubblebath.serverapp.ui.navigation

sealed class NavRoutes(val uri: String) {
    data object MainScreen : NavRoutes("MainScreen")
    data object ConfigScreen : NavRoutes("ConfigScreen")
    data object LogsScreen : NavRoutes("LogsScreen")
}