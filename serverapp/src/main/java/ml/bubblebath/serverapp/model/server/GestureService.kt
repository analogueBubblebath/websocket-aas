package ml.bubblebath.serverapp.model.server

import io.ktor.server.websocket.DefaultWebSocketServerSession
import ml.bubblebath.serverapp.model.logs.GestureServiceLogger
import ml.bubblebath.serverapp.model.repository.gesture.GestureRepository
import java.util.Collections

class GestureService(
    private val logger: GestureServiceLogger,
    private val repository: GestureRepository
) {
    private val sessionPool = Collections.synchronizedSet<WebsocketSession>(LinkedHashSet())

    fun registerSession(session: DefaultWebSocketServerSession, uuid: String) {
        val wsSession = WebsocketSession(session, uuid)
        val isNewSession = sessionPool.add(wsSession)
        if (isNewSession) {
            logger.info("New session added with id: $uuid")
        }
    }

    suspend fun publishGesture(uuid: String) {
        sessionPool.find { it.uuid == uuid }?.let {
            it.sendSerialized(repository.getNext())
            logger.info("Send gesture task to session ${it.uuid}")
        }
    }

    fun logSuccess(uuid: String) {
        logger.info("$uuid finished execution")
    }

    fun disposeSession(uuid: String) {
        val session = sessionPool.find { it.uuid == uuid }
        session?.let {
            sessionPool -= it
            logger.info("Session disposed: $uuid")
        }
    }

    fun logError(message: String) {
        logger.error(message)
    }
}
