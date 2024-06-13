package ml.bubblebath.serverapp.ui.screen.main

sealed interface MainScreenIntent {
    data object StartServer : MainScreenIntent
}