package com.bivizul.howtochooseasportsbook.ui.support

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bivizul.howtochooseasportsbook.data.Constant.NUM_PAGES
import com.bivizul.howtochooseasportsbook.ui.fragment.*

class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> IntroFragment()
            1 -> StepsFragment()
            2 -> CriterionFragment()
            3 -> PitfallsFragment()
            4 -> TipsFragment()
            else -> FinalFragment()
        }
    }
}
