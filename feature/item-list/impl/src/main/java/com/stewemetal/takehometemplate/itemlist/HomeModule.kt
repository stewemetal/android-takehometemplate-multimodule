package com.stewemetal.takehometemplate.itemlist

import com.stewemetal.takehometemplate.itemlist.contract.HomeNavGraphFactory
import com.stewemetal.takehometemplate.itemlist.navigation.HomeNavGraphFactoryImpl
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
