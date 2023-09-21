package com.programmer270487.filamentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.filament.Engine
import com.google.android.filament.utils.Utils
import com.programmer270487.filamentapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val b: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    companion object {
        init {
            Utils.init()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        val renderer1 = ModelRendererWithAnim4()
        val renderer2 = ModelRendererWithAnim4()
        val renderer3 = ModelRendererWithAnim4()
        val renderer4 = ModelRendererWithAnim4()
        renderer1.onSurfaceAvailable(b.surfaceView1, lifecycle)
        renderer2.onSurfaceAvailable(b.surfaceView2, lifecycle)
        renderer3.onSurfaceAvailable(b.surfaceView3, lifecycle)
        renderer4.onSurfaceAvailable(b.surfaceView4, lifecycle)

        // Trigger dance animation on tap
        /*! jd ga bisa digerakin pake jari b.surfaceView1.setOnTouchListener { _, _ ->
            renderer1.triggerDanceAnimation()
            true
        }*/
        b.surfaceView1.setOnClickListener {
//            renderer1.triggerDanceAnimation() //ModelRendererWithAnim2

        }
        b.surfaceView1.setOnTouchListener { v, event ->
            startActivity(Intent(this, WithFragmentsActivity::class.java))
            true
        }
        b.surfaceView1.setOnClickListener {
            /*if (renderer3.isAnimating) {
                renderer3.stopAnimation()
            } else {
                renderer3.startAnimation()
            }*/
        }
        b.surfaceView1.setOnClickListener {
            /*if (renderer4.isAnimating) {
                renderer4.stopAnimation()
            } else {
                renderer4.startAnimation()
            }*/
        }
    }
}