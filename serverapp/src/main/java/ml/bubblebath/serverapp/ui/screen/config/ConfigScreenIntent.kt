package ml.bubblebath.serverapp.ui.screen.config

sealed interface ConfigScreenIntent {
    data class PortInput(val newText: String) : ConfigScreenIntent
    data object PortSave : ConfigScreenIntent
}
