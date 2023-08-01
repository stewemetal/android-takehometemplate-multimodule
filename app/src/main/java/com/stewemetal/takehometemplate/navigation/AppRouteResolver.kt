package com.stewemetal.takehometemplate.navigation

import com.stewemetal.takehometemplate.shell.navigation.GlobalRouteResolver
import com.stewemetal.takehometemplate.shell.navigation.RouteResolverWrapper
import com.stewemetal.takehometemplate.shell.navigation.model.Destination
import com.stewemetal.takehometemplate.shell.navigation.model.Route

class AppRouteResolver(
    private val routeResolverWrapper: RouteResolverWrapper,
) : GlobalRouteResolver {

    override fun resolveRoute(destination: Destination): Route =
        routeResolverWrapper.routeResolversSet.firstNotNullOfOrNull {
            it.resolveRoute(destination)
        }
            ?: throw IllegalArgumentException("Router resolver not implemented for destination: $destination")
}
