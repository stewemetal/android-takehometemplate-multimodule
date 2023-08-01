package com.stewemetal.takehometemplate.shell.navigation.internal

import com.stewemetal.takehometemplate.shell.navigation.RouteResolver
import com.stewemetal.takehometemplate.shell.navigation.RouteResolverWrapper

internal class RouteResolverWrapperImpl : RouteResolverWrapper {

    override val routeResolversSet = mutableSetOf<RouteResolver>()

    override fun addRouteResolver(routeResolver: RouteResolver) {
        routeResolversSet.add(routeResolver)
    }
}
