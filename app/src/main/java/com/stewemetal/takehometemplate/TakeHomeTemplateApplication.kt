package com.stewemetal.takehometemplate

import android.app.Application
import com.stewemetal.takehometemplate.home.HomeModule
import com.stewemetal.takehometemplate.login.LoginModule
import com.stewemetal.takehometemplate.shell.BuildConfig
import com.stewemetal.takehometemplate.shell.ShellModule
import com.stewemetal.takehometemplate.shell.navigation.NavigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant

class TakeHomeTemplateApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        }

        startKoin {
            androidContext(this@TakeHomeTemplateApplication)
            modules(
                AppModule().module,
                ShellModule().module,
                NavigationModule().module,
                LoginModule().module,
                HomeModule().module,
            )
        }
    }
}
