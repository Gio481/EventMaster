package com.eventmaster.app

import android.app.Application
import com.eventmaster.core.presentation.events.navigationEventModule
import com.eventmaster.di.navigation.navigatorModule
import com.eventmaster.features.authentication.impl.presentation.auth.main.di.authModule
import com.eventmaster.features.authentication.impl.presentation.login.main.di.logInModule
import com.eventmaster.features.authentication.impl.presentation.signup.birthdate.di.birthdateModule
import com.eventmaster.features.authentication.impl.presentation.signup.mail.di.mailModule
import com.eventmaster.features.authentication.impl.presentation.signup.password.di.passwordModule
import com.eventmaster.features.authentication.impl.presentation.signup.profile.di.profileModule
import com.eventmaster.features.authentication.impl.presentation.signup.username.di.userNameModule
import com.eventmaster.features.eveny.impl.presentation.create.cover.di.coverModule
import com.eventmaster.features.eveny.impl.presentation.create.date.di.dateModule
import com.eventmaster.features.eveny.impl.presentation.create.friends_list.di.friendsListModule
import com.eventmaster.features.group.impl.presentation.create.cover.di.groupCoverModule
import com.eventmaster.features.group.impl.presentation.groups.details.di.groupDetailsModule
import com.eventmaster.features.group.impl.presentation.groups.multi_groups.di.multiGroupModule
import com.eventmaster.features.home.impl.presentation.home.di.homeModule
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
                passwordModule,
                birthdateModule,
                profileModule,
                logInModule,
                userNameModule,
                navigationEventModule,
                coverModule,
                dateModule,
                friendsListModule,
                homeModule,
                groupCoverModule,
                multiGroupModule,
                groupDetailsModule
            )
        }
    }
}