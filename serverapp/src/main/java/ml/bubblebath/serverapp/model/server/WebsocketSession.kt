package ml.bubblebath.serverapp.model.server

import io.ktor.server.websocket.DefaultWebSocketServerSession
import io.ktor.server.websocket.sendSerialized
import ml.bubblebath.common.Gesture

class WebsocketSession(private val session: DefaultWebSocketServerSession, val uuid: String) {
    suspend fun sendSerialized(gesture: Gesture) {
        session.sendSerialized(gesture)
    }
}