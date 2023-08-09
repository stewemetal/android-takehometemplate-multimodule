package com.stewemetal.takehometemplate.home

import com.stewemetal.takehometemplate.home.contract.HomeNavGraphFactory
import com.stewemetal.takehometemplate.home.navigation.HomeNavGraphFactoryImpl
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@ComponentScan
class HomeModule {
    @Factory
    fun homeNavigationGraphFactory(): HomeNavGraphFactory =
        HomeNavGraphFactoryImpl()
}
