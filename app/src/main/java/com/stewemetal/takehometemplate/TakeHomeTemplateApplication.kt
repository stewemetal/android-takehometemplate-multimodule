package com.stewemetal.takehometemplate

import android.app.Application
import com.stewemetal.takehometemplate.shell.ShellModule
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class TakeHomeTemplateApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                ShellModule().module,
            )
        }
    }
}
