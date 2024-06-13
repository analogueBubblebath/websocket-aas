package ml.bubblebath.serverapp.model.repository

import ml.bubblebath.LogsDatabase
import ml.bubblebath.serverapp.model.logs.LogLevel

class LogsRepository(database: LogsDatabase) {
    private val logQueries = database.logQueries

    fun getAll() = logQueries.selectAll().executeAsList()

    fun insert(logLevel: LogLevel, message: String) {
        logQueries.insert(logLevel, message)
    }
}