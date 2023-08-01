package com.stewemetal.takehometemplate.shell.navigation

/**
 * A wrapper used to store all the implementations of [RouteResolver].
 * This is required because Koin doesn't support multibinding.
 */
interface RouteResolverWrapper {

    val routeResolversSet: Set<RouteResolver>

    fun addRouteResolver(routeResolver: RouteResolver)
}
