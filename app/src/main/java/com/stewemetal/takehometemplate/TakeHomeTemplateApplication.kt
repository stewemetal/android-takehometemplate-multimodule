package com.stewemetal.takehometemplate

import android.app.Application
import com.stewemetal.takehometemplate.shell.ShellModule
import com.stewemetal.takehometemplate.shell.navigation.NavigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class TakeHomeTemplateApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TakeHomeTemplateApplication)
            modules(
                AppModule().module,
                ShellModule().module,
                NavigationModule().module,
            )
        }
    }
}
