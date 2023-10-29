package com.eventmaster.app

import android.app.Application
import com.eventmaster.di.navigation.navigatorModule
import com.eventmaster.features.authentication.impl.presentation.auth.main.di.authModule
import com.eventmaster.features.authentication.impl.presentation.signup.mail.di.mailModule
import com.eventmaster.features.authentication.impl.presentation.signup.password.di.passwordModule
import com.eventmaster.features.splash.impl.presentation.main.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EMApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EMApplication)
            modules(
                navigatorModule,
                splashModule,
                authModule,
                mailModule,
                passwordModule
            )
        }
    }
}