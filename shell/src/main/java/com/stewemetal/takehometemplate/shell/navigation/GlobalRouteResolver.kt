package com.stewemetal.takehometemplate.shell.navigation

import com.stewemetal.takehometemplate.shell.navigation.model.Destination
import com.stewemetal.takehometemplate.shell.navigation.model.Route

/**
 * The [GlobalRouteResolver] gathers all the [RouteResolver]s from the DI graph and generates [Route]s
 * from [Destination]s.
 *
 * This interface is implemented in the app module.
 */
interface GlobalRouteResolver {
    fun resolveRoute(destination: Destination): Route
}
