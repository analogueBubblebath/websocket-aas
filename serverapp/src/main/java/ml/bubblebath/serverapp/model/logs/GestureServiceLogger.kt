package ml.bubblebath.serverapp.model.logs

import ml.bubblebath.serverapp.model.repository.LogsRepository

class GestureServiceLogger(private val repository: LogsRepository) {
    fun info(message: String) {
        repository.insert(LogLevel.INFO, message)
    }

    fun warn(message: String) {
        repository.insert(LogLevel.WARNING, message)
    }

    fun error(message: String) {
        repository.insert(LogLevel.ERROR, message)
    }
}