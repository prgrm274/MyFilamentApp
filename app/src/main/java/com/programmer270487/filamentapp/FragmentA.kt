package com.programmer270487.filamentapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.filament.utils.Utils
import com.programmer270487.filamentapp.databinding.FragmentABinding

class FragmentA : Fragment() {
    private lateinit var b: FragmentABinding

    companion object {
        init {
            Utils.init()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        b = FragmentABinding.inflate(layoutInflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val renderer1 = ModelRendererWithAnim4()
        renderer1.onSurfaceAvailable(b.surfaceView1, lifecycle)

        b.surfaceView1.setOnClickListener {
            renderer1.startAnimation()
        }
    }
}