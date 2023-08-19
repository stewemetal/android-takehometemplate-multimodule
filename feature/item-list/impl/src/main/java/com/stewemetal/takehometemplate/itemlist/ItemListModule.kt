package com.stewemetal.takehometemplate.itemlist

import com.stewemetal.takehometemplate.itemlist.contract.ItemListNavGraphFactory
import com.stewemetal.takehometemplate.itemlist.navigation.ItemListNavGraphFactoryImpl
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@ComponentScan
class ItemListModule {
    @Factory
    fun homeNavigationGraphFactory(): ItemListNavGraphFactory =
        ItemListNavGraphFactoryImpl()
}
