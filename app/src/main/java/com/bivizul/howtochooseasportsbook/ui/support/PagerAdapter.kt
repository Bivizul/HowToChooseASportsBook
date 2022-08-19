package com.bivizul.howtochooseasportsbook.ui.support

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bivizul.howtochooseasportsbook.data.Constant
import com.bivizul.howtochooseasportsbook.data.Constant.KEY_INT
import com.bivizul.howtochooseasportsbook.data.Constant.NUM_PAGES
import com.bivizul.howtochooseasportsbook.ui.fragment.*

class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        /*when (position) {
            0 -> {
                pref.edit().clear().apply()
                pref.edit().putInt(KEY_INT, 5).apply()
            }
            1 -> {
                pref.edit().clear().apply()
                pref.edit().putInt(KEY_INT, 0).apply()
            }
            2 -> {
                pref.edit().clear().apply()
                pref.edit().putInt(KEY_INT, 1).apply()
            }
            3 -> {
                pref.edit().clear().apply()
                pref.edit().putInt(KEY_INT, 2).apply()
            }
            4 -> {
                pref.edit().clear().apply()
                pref.edit().putInt(KEY_INT, 3).apply()
            }
            else -> {
                pref.edit().clear().apply()
                pref.edit().putInt(KEY_INT, 4).apply()
            }
        }*/
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
