package ml.bubblebath.serverapp.ui.screen.logs

sealed interface LogsScreenIntent {
    data object LoadLogs : LogsScreenIntent
}