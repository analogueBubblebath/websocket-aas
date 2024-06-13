package ml.bubblebath.clientapp.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import ml.bubblebath.clientapp.api.GestureApi
import ml.bubblebath.clientapp.ui.screen.config.ConfigScreenReducer
import ml.bubblebath.clientapp.ui.screen.config.ConfigScreenViewModel
import ml.bubblebath.clientapp.ui.screen.main.MainScreenReducer
import ml.bubblebath.clientapp.ui.screen.main.MainScreenViewModel
import ml.bubblebath.common.ServiceFrame
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { CoroutineScope(Dispatchers.Default) }
    single { GestureApi(client = createHttpClient()) }
    factory { MainScreenReducer() }
    factory { ConfigScreenReducer() }
    viewModel { MainScreenViewModel(reducer = get()) }
    viewModel { ConfigScreenViewModel(reducer = get()) }
}

fun createHttpClient() = HttpClient(CIO) {
    install(WebSockets) {
        contentConverter = KotlinxWebsocketSerializationConverter(
            Json {
                serializersModule = SerializersModule {
                    polymorphic(
                        ServiceFrame::class,
                        ServiceFrame.Success::class,
                        ServiceFrame.Success.serializer(),
                    )
                    polymorphic(
                        ServiceFrame::class,
                        ServiceFrame.Connect::class,
                        ServiceFrame.Connect.serializer(),
                    )
                    polymorphic(
                        ServiceFrame::class,
                        ServiceFrame.Disconnect::class,
                        ServiceFrame.Disconnect.serializer(),
                    )
                }
            }
        )
    }
}
