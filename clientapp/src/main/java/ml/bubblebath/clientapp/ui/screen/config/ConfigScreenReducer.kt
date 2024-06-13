package ml.bubblebath.clientapp.ui.screen.config

import ml.bubblebath.clientapp.service.WebsocketGestureService

class ConfigScreenReducer {
    fun reduce(state: ConfigScreenState, intent: ConfigScreenIntent): ConfigScreenState {
        return when(intent) {
            is ConfigScreenIntent.HostChanged -> {
                state.copy(host = intent.newText)
            }
            is ConfigScreenIntent.PortChanged -> {
                state.copy(port = intent.newText)
            }
            ConfigScreenIntent.Save -> {
                WebsocketGestureService.host = state.host
                WebsocketGestureService.port = state.port.toInt()
                state
            }
        }
    }
}