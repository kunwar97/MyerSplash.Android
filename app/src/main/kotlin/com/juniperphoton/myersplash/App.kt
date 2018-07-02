package com.juniperphoton.myersplash

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.android.gms.ads.MobileAds
import com.juniperphoton.myersplash.utils.Pasteur
import com.onesignal.OneSignal

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Pasteur.init(BuildConfig.DEBUG)
        Fresco.initialize(this)
        RealmCache.init(this)
        OneSignal.startInit(this).inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification).init()
        MobileAds.initialize(this, getString(R.string.ad_app_id))

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}