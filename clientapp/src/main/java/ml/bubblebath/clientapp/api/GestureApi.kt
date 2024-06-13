package ml.bubblebath.clientapp.api

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.receiveDeserialized
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.http.HttpMethod
import io.ktor.websocket.CloseReason
import io.ktor.websocket.close
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ml.bubblebath.clientapp.uuid
import ml.bubblebath.common.Gesture
import ml.bubblebath.common.ServiceFrame

class GestureApi(private val client: HttpClient) {
    companion object {
        private const val LOG_TAG = "GestureApi"
    }

    private lateinit var session: DefaultClientWebSocketSession
    suspend fun connect(host: String, port: Int) {
        session = client.webSocketSession(
            method = HttpMethod.Get,
            host = host,
            port = port,
            path = "/gesture"
        )
        session.sendSerialized<ServiceFrame>(ServiceFrame.Connect(uuid = uuid))
    }

    suspend fun startCollecting(onGestureReceiveCallback: (Gesture) -> Unit) {
        if (this::session.isInitialized) {
            while (session.closeReason.isActive) {
                try {
                    val gesture = session.receiveDeserialized<Gesture>()
                    onGestureReceiveCallback(gesture)
                    delay(3000)
                } catch (e: ClosedReceiveChannelException) {
                    Log.e(LOG_TAG, "Channel was closed. ${e.message}")
                }
            }
        }
    }

    suspend fun sendSuccess() {
        if (this::session.isInitialized) {
            session.sendSerialized<ServiceFrame>(ServiceFrame.Success(uuid = uuid))
        }
    }

    suspend fun disconnect() {
        if (this::session.isInitialized) {
            session.sendSerialized<ServiceFrame>(ServiceFrame.Disconnect(uuid = uuid))
            session.close()
        } else {
            Log.e(LOG_TAG, "Session in not initialized")
        }
    }
}
