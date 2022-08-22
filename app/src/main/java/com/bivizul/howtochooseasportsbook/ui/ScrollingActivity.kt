package com.bivizul.howtochooseasportsbook.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.bivizul.howtochooseasportsbook.R
import com.bivizul.howtochooseasportsbook.appComponent
import com.bivizul.howtochooseasportsbook.data.Constant.BACK_ACTIVITY
import com.bivizul.howtochooseasportsbook.data.Constant.BACK_HEADER
import com.bivizul.howtochooseasportsbook.databinding.ActivityScrollingBinding
import com.bivizul.howtochooseasportsbook.ui.support.PagerAdapter
import com.bivizul.howtochooseasportsbook.util.ZoomOutPageTransformer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ScrollingActivity : AppCompatActivity(R.layout.activity_scrolling) {
    private val binding by viewBinding(ActivityScrollingBinding::bind)
    private val viewPager by lazy { binding.contentScrolling.viewPager }
    private val tabLayout by lazy { binding.coordinator.tabLayout }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        binding.backActivity.load(BACK_ACTIVITY)
        binding.coordinator.backgroundHeader.load(BACK_HEADER)

        viewPager.adapter = PagerAdapter(this)
        viewPager.setPageTransformer(ZoomOutPageTransformer())
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.intro)
                1 -> tab.text = getString(R.string.steps)
                2 -> tab.text = getString(R.string.criterion)
                3 -> tab.text = getString(R.string.pitfalls)
                4 -> tab.text = getString(R.string.tips)
                else -> tab.text = getString(R.string.finala)
            }
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.customView?.alpha = 1F
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.customView?.alpha = 0.1F
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}