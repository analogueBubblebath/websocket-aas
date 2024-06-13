package ml.bubblebath.clientapp.ui.screen.main

import ml.bubblebath.clientapp.service.WebsocketGestureService
import org.koin.core.component.KoinComponent

class MainScreenReducer : KoinComponent {
    fun reduce(state: MainScreenState, intent: MainScreenIntent): MainScreenState {
        return when (intent) {
            MainScreenIntent.ClientStart -> {
                WebsocketGestureService.startService()
                state.copy(isClientRunning = true)
            }

            MainScreenIntent.ClientPause -> {
                WebsocketGestureService.pauseService()
                state.copy(isClientRunning = false)
            }
        }
    }
}
