package ml.bubblebath.serverapp.model.repository.gesture

import ml.bubblebath.common.Gesture

interface Datasource {
    fun getGesture(): Gesture
}