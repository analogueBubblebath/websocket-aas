package ml.bubblebath.clientapp.ui.screen.config

sealed interface ConfigScreenIntent {
    data class HostChanged(val newText: String) : ConfigScreenIntent
    data class PortChanged(val newText: String) : ConfigScreenIntent
    data object Save : ConfigScreenIntent
}
