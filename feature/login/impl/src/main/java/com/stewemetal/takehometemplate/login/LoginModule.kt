package com.stewemetal.takehometemplate.login

import com.stewemetal.takehometemplate.login.contract.LoginNavGraphFactory
import com.stewemetal.takehometemplate.login.navigation.LoginNavGraphFactoryImpl
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@ComponentScan
class LoginModule {
    @Factory
    fun loginNavigationGraphFactory(): LoginNavGraphFactory =
        LoginNavGraphFactoryImpl()
}
