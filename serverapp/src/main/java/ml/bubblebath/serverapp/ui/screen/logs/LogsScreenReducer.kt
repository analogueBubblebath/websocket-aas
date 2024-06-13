package ml.bubblebath.serverapp.ui.screen.logs

class LogsScreenReducer(private val repository: ml.bubblebath.serverapp.model.repository.LogsRepository) {
    fun reduce(state: LogsScreenState, intent: LogsScreenIntent): LogsScreenState {
        return when (intent) {
            LogsScreenIntent.LoadLogs -> {
                val logs = repository.getAll()
                state.copy(logs = logs)
            }
        }
    }
}
