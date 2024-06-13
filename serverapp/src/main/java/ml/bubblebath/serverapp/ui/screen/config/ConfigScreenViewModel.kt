package ml.bubblebath.serverapp.ui.screen.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ml.bubblebath.serverapp.model.server.Server.serverPort

class ConfigScreenViewModel(private val reducer: ConfigScreenStateReducer) : ViewModel() {
    private val _uiState =
        MutableStateFlow(ConfigScreenState(serverPort.toString()))
    val uiState: StateFlow<ConfigScreenState> = _uiState

    fun handleIntent(intent: ConfigScreenIntent) {
        viewModelScope.launch {
            _uiState.value = reducer.reduce(uiState.value, intent)
        }
    }
}