package ml.bubblebath.serverapp.di

import android.content.Context
import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import ml.bubblebath.Log
import ml.bubblebath.LogsDatabase
import ml.bubblebath.serverapp.model.logs.GestureServiceLogger
import ml.bubblebath.serverapp.model.repository.LogsRepository
import ml.bubblebath.serverapp.model.repository.gesture.Datasource
import ml.bubblebath.serverapp.model.repository.gesture.GestureDatasourceMock
import ml.bubblebath.serverapp.model.repository.gesture.GestureRepository
import ml.bubblebath.serverapp.model.repository.gesture.GestureRepositoryMock
import ml.bubblebath.serverapp.model.server.GestureService
import ml.bubblebath.serverapp.ui.screen.config.ConfigScreenStateReducer
import ml.bubblebath.serverapp.ui.screen.config.ConfigScreenViewModel
import ml.bubblebath.serverapp.ui.screen.logs.LogsScreenReducer
import ml.bubblebath.serverapp.ui.screen.logs.LogsScreenViewModel
import ml.bubblebath.serverapp.ui.screen.main.MainScreenStateReducer
import ml.bubblebath.serverapp.ui.screen.main.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        LogsRepository(
            LogsDatabase(
                driver = prepareSqlDelightDriver(get()),
                Log.Adapter(logLevelAdapter = EnumColumnAdapter())
            )
        )
    }
    single<Datasource> { GestureDatasourceMock() }
    single<GestureRepository> { GestureRepositoryMock(datasource = get()) }
    factory { GestureServiceLogger(repository = get()) }
    single { GestureService(logger = get(), repository = get()) }
    factory { MainScreenStateReducer() }
    factory { ConfigScreenStateReducer() }
    factory { LogsScreenReducer(repository = get()) }
    viewModel { MainScreenViewModel(reducer = get()) }
    viewModel { ConfigScreenViewModel(reducer = get()) }
    viewModel { LogsScreenViewModel(reducer = get()) }
}

fun prepareSqlDelightDriver(context: Context): SqlDriver =
    AndroidSqliteDriver(LogsDatabase.Schema, context, "logs.db")
