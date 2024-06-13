package ml.bubblebath.clientapp.ui.screen.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ml.bubblebath.clientapp.service.WebsocketGestureService

class ConfigScreenViewModel(private val reducer: ConfigScreenReducer) : ViewModel() {
    private val _uiState = MutableStateFlow(
        ConfigScreenState(
            host = WebsocketGestureService.host,
            port = WebsocketGestureService.port.toString()
        )
    )
    val uiState: StateFlow<ConfigScreenState> = _uiState

    fun handleIntent(intent: ConfigScreenIntent) {
        viewModelScope.launch {
            _uiState.value = reducer.reduce(uiState.value, intent)
        }
    }
}
