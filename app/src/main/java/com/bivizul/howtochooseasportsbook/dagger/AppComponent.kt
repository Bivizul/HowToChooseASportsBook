package com.bivizul.howtochooseasportsbook.dagger

import com.bivizul.howtochooseasportsbook.ui.MainActivity
import com.bivizul.howtochooseasportsbook.ui.ScrollingActivity
import com.bivizul.howtochooseasportsbook.ui.fragment.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(scrollingActivity: ScrollingActivity)
    fun inject(criterionFragment: CriterionFragment)
    fun inject(finalFragment: FinalFragment)
    fun inject(introFragment: IntroFragment)
    fun inject(pitfallsFragment: PitfallsFragment)
    fun inject(stepsFragment: StepsFragment)
    fun inject(tipsFragment: TipsFragment)

}