package ml.bubblebath.serverapp.model.logs

import androidx.compose.ui.graphics.Color

enum class LogLevel {
    INFO,
    WARNING,
    ERROR;

    fun asColor() = when (this) {
        INFO -> Color.Cyan
        WARNING -> Color.Magenta
        ERROR -> Color.Red
    }
}
