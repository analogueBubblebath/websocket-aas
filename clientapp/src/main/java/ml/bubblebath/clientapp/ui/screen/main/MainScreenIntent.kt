package ml.bubblebath.clientapp.ui.screen.main

sealed interface MainScreenIntent {
    data object ClientStart : MainScreenIntent
    data object ClientPause : MainScreenIntent
}