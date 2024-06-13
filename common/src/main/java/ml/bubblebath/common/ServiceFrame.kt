package ml.bubblebath.common

import kotlinx.serialization.Serializable

sealed interface ServiceFrame {
    @Serializable
    data class Success(val uuid: String) : ServiceFrame

    @Serializable
    data class Connect(val uuid: String) : ServiceFrame

    @Serializable
    data class Disconnect(val uuid: String) : ServiceFrame
}
