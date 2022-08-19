package com.bivizul.howtochooseasportsbook.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.bivizul.howtochooseasportsbook.R
import com.bivizul.howtochooseasportsbook.data.Constant.BACK_HEADER
import com.bivizul.howtochooseasportsbook.data.Constant.BACK_ACTIVITY
import com.bivizul.howtochooseasportsbook.databinding.ActivityScrollingBinding
import com.bivizul.howtochooseasportsbook.ui.support.PagerAdapter
import com.bivizul.howtochooseasportsbook.util.ZoomOutPageTransformer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScrollingActivity : AppCompatActivity(R.layout.activity_scrolling) {

    private val binding by viewBinding(ActivityScrollingBinding::bind)
    val viewPager by lazy { binding.contentScrolling.viewPager }
    val tabLayout by lazy { binding.coordinator.tabLayout }
//    private val pref by lazy {
//        this.getSharedPreferences(APP_PREF,Context.MODE_PRIVATE)
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_dowobata)
//
//        CoroutineScope(Dispatchers.Main).launch {
//            delay(3000)
//            setContentView(R.layout.activity_scrolling)
//        }

        binding.backActivity.load(BACK_ACTIVITY)
        binding.coordinator.backgroundHeader.load(BACK_HEADER)

        viewPager.adapter = PagerAdapter(this)
        viewPager.setPageTransformer(ZoomOutPageTransformer())
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Intro"
                1 -> tab.text = "Steps"
                2 -> tab.text = "Criterion"
                3 -> tab.text = "Pitfalls"
                4 -> tab.text = "Tips"
                else -> tab.text = "Final"
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

//    override fun onBackPressed() {
//        if (viewPager.currentItem == 0) {
//            super.onBackPressed()
//        } else {
//            viewPager.currentItem = viewPager.currentItem - 1
//        }
//    }

}