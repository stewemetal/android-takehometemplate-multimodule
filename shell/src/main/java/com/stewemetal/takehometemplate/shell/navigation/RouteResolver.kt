package com.stewemetal.takehometemplate.shell.navigation

import com.stewemetal.takehometemplate.shell.navigation.model.Destination
import com.stewemetal.takehometemplate.shell.navigation.model.Route

/**
 * The [RouteResolver] transforms [Destination]s into [Route]s.
 * This interface should be implemented by every feature module and the implementation must be added to the DI graph
 * with the `routerResolver` extension function.
 */
interface RouteResolver {
    fun resolveRoute(destination: Destination): Route?
}
