package com.stewemetal.takehometemplate.itemdetails

import com.stewemetal.takehometemplate.itemdetails.contract.ItemDetailsNavGraphFactory
import com.stewemetal.takehometemplate.itemdetails.navigation.ItemDetailsNavGraphFactoryImpl
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@ComponentScan
class ItemDetailsModule {
    @Factory
    fun navigationGraphFactory(): ItemDetailsNavGraphFactory =
        ItemDetailsNavGraphFactoryImpl()
}
