package com.programmer270487.filamentapp

import android.content.res.AssetManager
import android.util.Log
import android.view.Choreographer
import android.view.MotionEvent
import android.view.SurfaceView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.google.android.filament.View
import com.google.android.filament.android.UiHelper
import com.google.android.filament.utils.ModelViewer
import java.nio.ByteBuffer
import java.nio.ByteOrder

class ModelRendererWithAnim4 {
    private lateinit var surfaceView: SurfaceView
    private lateinit var lifecycle: Lifecycle

    private lateinit var choreographer: Choreographer
    private lateinit var uiHelper: UiHelper

    private lateinit var modelViewer: ModelViewer

    private val assets: AssetManager
        get() = surfaceView.context.assets

    private var isAnimating: Boolean = false // make it public to call this from activity
    private fun startAnimation(animationIndex: Int) {
        isAnimating = true
        choreographer.postFrameCallback(frameCallback)
        modelViewer.animator?.apply {
            if (animationCount > 0) {
                applyAnimation(animationIndex, 4f)
            }
            updateBoneMatrices()
        }
    }
    private fun stopAnimation() {
        isAnimating = false
        choreographer.removeFrameCallback(frameCallback)
    }

    private val lifecycleObserver = object : DefaultLifecycleObserver {
        override fun onResume(owner: LifecycleOwner) {
            choreographer.postFrameCallback(frameCallback)
        }

        override fun onPause(owner: LifecycleOwner) {
            choreographer.removeFrameCallback(frameCallback)
        }

        override fun onDestroy(owner: LifecycleOwner) {
            choreographer.removeFrameCallback(frameCallback)
            lifecycle.removeObserver(this)
        }
    }

    fun onSurfaceAvailable(surfaceView: SurfaceView, lifecycle: Lifecycle) {
        this.surfaceView = surfaceView
        this.lifecycle = lifecycle

        lifecycle.addObserver(lifecycleObserver)

        choreographer = Choreographer.getInstance()
        uiHelper = UiHelper(UiHelper.ContextErrorPolicy.DONT_CHECK).apply {
            // This is needed to make the background transparent
            isOpaque = false
        }

        modelViewer = ModelViewer(surfaceView = surfaceView, uiHelper = uiHelper)

        surfaceView.setOnTouchListener { _, event ->
            modelViewer.onTouchEvent(event) // to enable rotating model 360 degrees with touch
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (isAnimating) {
                    stopAnimation()
                } else {
                    startAnimation(2)
                }
            }
            true
        }

        modelViewer.scene.skybox = null
        modelViewer.view.blendMode = View.BlendMode.TRANSLUCENT
        modelViewer.renderer.clearOptions = modelViewer.renderer.clearOptions.apply {
            clear = true
        }

        modelViewer.view.apply {
            renderQuality = renderQuality.apply {
                hdrColorBuffer = View.QualityLevel.LOW
            }
        }

        createRenderables()
    }

    private fun createRenderables() {
        // assets/models = assets.open("models/sample.glb")
        val buffer = assets.open("models/anim_stegosaurs_SStenops.glb").use { input ->
            val bytes = ByteArray(input.available())
            input.read(bytes)
            ByteBuffer.allocateDirect(bytes.size).apply {
                order(ByteOrder.nativeOrder())
                put(bytes)
                rewind()
            }
        }

        modelViewer.loadModelGlb(buffer)
        modelViewer.transformToUnitCube()
    }

    private val frameCallback = object : Choreographer.FrameCallback {
        private val startTime = System.nanoTime()
        override fun doFrame(currentTime: Long) {
            choreographer.postFrameCallback(this) // default

            val seconds = (currentTime - startTime).toDouble() / 1_000_000_000
            modelViewer.animator?.apply {
                if (animationCount > 0) {
                    applyAnimation(3, seconds.toFloat())
                }
                updateBoneMatrices()
            }

            modelViewer.render(currentTime)
        }
    }
}