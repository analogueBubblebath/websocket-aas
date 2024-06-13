package ml.bubblebath.serverapp.ui.screen.config

class ConfigScreenStateReducer {
    fun reduce(state: ConfigScreenState, intent: ConfigScreenIntent): ConfigScreenState {
        return when (intent) {
            is ConfigScreenIntent.PortInput -> {
                when {
                    intent.newText.isEmpty() -> state.copy(port = intent.newText, isSaveButtonEnabled = false)
                    intent.newText.toUShortOrNull() == null -> state
                    else -> state.copy(port = intent.newText, isSaveButtonEnabled = true)
                }
            }

            ConfigScreenIntent.PortSave -> {
                ml.bubblebath.serverapp.model.server.Server.serverPort = state.port.toInt()
                state
            }
        }
    }
}