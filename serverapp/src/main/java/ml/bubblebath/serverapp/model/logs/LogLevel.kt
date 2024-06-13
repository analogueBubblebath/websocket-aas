package ml.bubblebath.serverapp.model.logs

import androidx.compose.ui.graphics.Color

enum class LogLevel {
    INFO,
    WARNING,
    ERROR;

    fun asColor() = when (this) {
        ml.bubblebath.serverapp.model.logs.LogLevel.INFO -> Color.Cyan
        ml.bubblebath.serverapp.model.logs.LogLevel.WARNING -> Color.Magenta
        ml.bubblebath.serverapp.model.logs.LogLevel.ERROR -> Color.Red
    }
}