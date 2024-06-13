package ml.bubblebath.serverapp.model.repository.gesture

import ml.bubblebath.common.Gesture

class GestureDatasourceMock : Datasource {
    private val gestures = listOf(
        Gesture(moveX = 500f, moveY = 1200f, lineX = 500f, lineY = 0f),
        Gesture(moveX = 500f, moveY = 1200f, lineX = 500f, lineY = 250f),
    )

    override fun getGesture() = gestures.random()
}