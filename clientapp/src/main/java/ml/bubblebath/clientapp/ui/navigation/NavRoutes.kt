package ml.bubblebath.clientapp.ui.navigation

sealed class NavRoutes(val uri: String) {
    data object MainScreen : NavRoutes("MainScreen")
    data object ConfigScreen : NavRoutes("ConfigScreen")
}