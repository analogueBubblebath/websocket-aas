package ml.bubblebath.serverapp.model.repository.gesture

import ml.bubblebath.common.Gesture

interface GestureRepository {
    fun getNext(): Gesture
}
