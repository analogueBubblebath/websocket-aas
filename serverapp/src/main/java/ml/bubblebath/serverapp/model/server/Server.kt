package ml.bubblebath.serverapp.model.server

import android.util.Log
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.server.application.install
import io.ktor.server.cio.CIO
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.routing.routing
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.receiveDeserialized
import io.ktor.server.websocket.webSocket
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import ml.bubblebath.common.ServiceFrame
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object Server : KoinComponent {
    private const val LOG_TAG = "Server"
    private val service by inject<GestureService>()
    var serverPort: Int = 8080

    private var server: ApplicationEngine? = null

    private fun createServer() = embeddedServer(CIO, port = serverPort, host = "0.0.0.0") {
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

        routing {
            webSocket("/gesture") {
                while (closeReason.isActive) {
                    try {
                        when (val frame = receiveDeserialized<ServiceFrame>()) {
                            is ServiceFrame.Connect -> {
                                Log.i(LOG_TAG, "Connected ${frame.uuid}")
                                service.registerSession(this, frame.uuid)
                                service.publishGesture(frame.uuid)
                            }

                            is ServiceFrame.Disconnect -> {
                                Log.i(LOG_TAG, "Disconnected ${frame.uuid}")
                                service.disposeSession(frame.uuid)
                            }

                            is ServiceFrame.Success -> {
                                Log.i(LOG_TAG, "Success ${frame.uuid}")
                                service.logSuccess(frame.uuid)
                                service.publishGesture(frame.uuid)
                            }
                        }
                    } catch (_: ClosedReceiveChannelException) {
                    } catch (e: Throwable) {
                        service.logError(e.message ?: "Unknown error")
                    }
                }
            }
        }
    }

    //TODO: https://youtrack.jetbrains.com/issue/KTOR-3928/Ktor-Server-on-Android-java.nio.file.ClosedWatchServiceException
    fun start() {
        server = createServer()
        server!!.start()
    }

    fun stop() {
        server?.stop()
    }
}
