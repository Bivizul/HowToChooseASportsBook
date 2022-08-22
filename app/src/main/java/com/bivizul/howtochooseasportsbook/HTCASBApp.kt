package com.bivizul.howtochooseasportsbook

import android.app.Application
import android.content.Context
import com.appsflyer.AppsFlyerLib
import com.bivizul.howtochooseasportsbook.dagger.AppComponent
import com.bivizul.howtochooseasportsbook.dagger.DaggerAppComponent
import com.bivizul.howtochooseasportsbook.data.Constant.AF_DEV_KEY

class HTCASBApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()

        // TODO(OneSignal)
//        // Enable verbose OneSignal logging to debug issues if needed.
//        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
//
//        // OneSignal Initialization
//        OneSignal.initWithContext(this)
//        OneSignal.setAppId(ONESIGNAL_APP_ID)

        AppsFlyerLib.getInstance().init(AF_DEV_KEY, null, this)
        AppsFlyerLib.getInstance().start(this)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is HTCASBApp -> appComponent
        else -> applicationContext.appComponent
    }