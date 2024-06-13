package ml.bubblebath.common

import kotlinx.serialization.Serializable

@Serializable
data class Gesture(val moveX: Float, val moveY: Float, val lineX: Float, val lineY: Float)
