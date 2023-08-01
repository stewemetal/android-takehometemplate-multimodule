package com.stewemetal.takehometemplate

import com.stewemetal.takehometemplate.navigation.AppRouteResolver
import com.stewemetal.takehometemplate.shell.navigation.GlobalRouteResolver
import com.stewemetal.takehometemplate.shell.navigation.RouteResolverWrapper
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class AppModule {

    @Single
    fun globalRouteResolver(
        routeResolverWrapper: RouteResolverWrapper,
    ): GlobalRouteResolver =
        AppRouteResolver(
            routeResolverWrapper = routeResolverWrapper,
        )
}
