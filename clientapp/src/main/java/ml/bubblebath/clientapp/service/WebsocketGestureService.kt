package ml.bubblebath.clientapp.service

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ml.bubblebath.clientapp.api.GestureApi
import ml.bubblebath.common.Gesture
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object WebsocketGestureService : KoinComponent {
    private const val LOG_TAG = "WebsocketGestureService"
    private var accessibilityService: GestureAccessibilityService? = null
    private val gestureApi by inject<GestureApi>()
    private val serviceScope by inject<CoroutineScope>()
    var host = "localhost"
    var port = 8080

    fun bindService(service: GestureAccessibilityService) {
        accessibilityService = service
    }

    fun unbindService() {
        pauseService()
        accessibilityService = null
    }

    fun pauseService() {
        serviceScope.launch {
            gestureApi.disconnect()
        }
    }

    fun startService() {
        serviceScope.launch {
            accessibilityService?.let {
                delay(2000)
                gestureApi.connect(host, port)
                gestureApi.startCollecting {
                    accessibilityService?.dispatchGesture(
                        it.toPlatformGesture(),
                        object : AccessibilityService.GestureResultCallback() {
                            override fun onCompleted(gestureDescription: GestureDescription?) {
                                super.onCompleted(gestureDescription)
                                serviceScope.launch {
                                    gestureApi.sendSuccess()
                                }
                            }

                            override fun onCancelled(gestureDescription: GestureDescription?) {
                                super.onCancelled(gestureDescription)
                            }
                        },
                        null
                    )
                }
            } ?: Log.e(LOG_TAG, "Accessibility service is not bound")
        }
    }

    private fun Gesture.toPlatformGesture(): GestureDescription {
        val path = Path()
        path.moveTo(moveX, moveY)
        path.lineTo(lineX, lineY)
        val gestureBuilder = GestureDescription.Builder()
        gestureBuilder.addStroke(GestureDescription.StrokeDescription(path, 0, 500))
        return gestureBuilder.build()
    }
}
