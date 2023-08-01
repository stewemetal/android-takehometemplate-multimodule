package com.stewemetal.takehometemplate.shell.navigation

import com.stewemetal.takehometemplate.shell.navigation.internal.Navigator
import com.stewemetal.takehometemplate.shell.navigation.internal.NavigatorImpl
import com.stewemetal.takehometemplate.shell.navigation.internal.RouteResolverWrapperImpl
import com.stewemetal.takehometemplate.shell.navigation.internal.RouterImpl
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class NavigationModule {

    @Single
    fun navigator(): Navigator =
        NavigatorImpl()

    @Single
    fun router(
        navigator: Navigator,
        globalRouteResolver: GlobalRouteResolver,
    ): Router =
        RouterImpl(
            navigator = navigator,
            globalRouterResolver = globalRouteResolver,
        )

    @Single
    fun routeResolverWrapper(): RouteResolverWrapper =
        RouteResolverWrapperImpl()
}
