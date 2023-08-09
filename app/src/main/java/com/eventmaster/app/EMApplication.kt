package com.eventmaster.app

import android.app.Application
import com.eventmaster.di.navigation.navigatorModule
import com.eventmaster.splash.impl.presentation.main.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EMApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EMApplication)
            modules(navigatorModule, splashModule)
        }
    }
}