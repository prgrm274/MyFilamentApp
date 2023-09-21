package com.programmer270487.filamentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.programmer270487.filamentapp.databinding.ActivityWithFragmentsBinding

class WithFragmentsActivity : AppCompatActivity() {
    private val b: ActivityWithFragmentsBinding by lazy { ActivityWithFragmentsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        val pagerAdapter = MyPagerAdapter(supportFragmentManager)
        b.viewPager.adapter = pagerAdapter
        b.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private inner class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int = 2
        override fun getItem(position: Int): Fragment {
            return if (position == 0) {
                FragmentA()
            } else {
                FragmentB()
            }
        }
    }
}