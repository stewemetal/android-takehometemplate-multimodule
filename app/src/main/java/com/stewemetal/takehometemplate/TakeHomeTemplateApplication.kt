package com.stewemetal.takehometemplate

import android.app.Application
import com.stewemetal.takehometemplate.itemdetails.ItemDetailsModule
import com.stewemetal.takehometemplate.itemlist.ItemListModule
import com.stewemetal.takehometemplate.login.LoginModule
import com.stewemetal.takehometemplate.shell.ShellModule
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
                ShellModule().module,
                LoginModule().module,
                ItemListModule().module,
                ItemDetailsModule().module,
            )
        }
    }
}
