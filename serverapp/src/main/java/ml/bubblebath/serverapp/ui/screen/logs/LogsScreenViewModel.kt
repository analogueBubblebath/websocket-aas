package ml.bubblebath.serverapp.ui.screen.logs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LogsScreenViewModel(private val reducer: LogsScreenReducer) : ViewModel() {
    private val _uiState = MutableStateFlow(LogsScreenState())
    val uiState: StateFlow<LogsScreenState> = _uiState

    fun handleIntent(intent: LogsScreenIntent) {
        viewModelScope.launch {
            _uiState.value = reducer.reduce(uiState.value, intent)
        }
    }
}
