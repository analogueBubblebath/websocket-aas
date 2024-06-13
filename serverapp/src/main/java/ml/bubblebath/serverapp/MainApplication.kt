package ml.bubblebath.serverapp

import android.app.Application
import ml.bubblebath.serverapp.di.appModule
import ml.bubblebath.serverapp.model.server.Server
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}