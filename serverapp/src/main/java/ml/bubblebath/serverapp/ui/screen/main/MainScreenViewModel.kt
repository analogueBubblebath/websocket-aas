package ml.bubblebath.serverapp.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(private val reducer: MainScreenStateReducer) : ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState: StateFlow<MainScreenState> = _uiState

    fun handleIntent(intent: MainScreenIntent) {
        viewModelScope.launch {
            _uiState.value = reducer.reduce(uiState.value, intent)
        }
    }
}
