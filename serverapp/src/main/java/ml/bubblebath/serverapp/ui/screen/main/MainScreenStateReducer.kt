package ml.bubblebath.serverapp.ui.screen.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ml.bubblebath.serverapp.model.server.Server

class MainScreenStateReducer {
    suspend fun reduce(state: MainScreenState, intent: MainScreenIntent): MainScreenState {
        return when (intent) {
            MainScreenIntent.StartServer -> {
                if (state.isServerStarted) {
                    withContext(Dispatchers.IO) {
                        Server.stop()
                        state.copy(isServerStarted = false)
                    }
                } else {
                    withContext(Dispatchers.IO) {
                        Server.start()
                        state.copy(isServerStarted = true)
                    }
                }
            }
        }
    }
}
