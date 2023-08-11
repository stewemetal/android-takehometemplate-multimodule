package com.stewemetal.takehometemplate.shell.navigation.compose

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Left
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Right
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun TakeHomeTemplateNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    builder: NavGraphBuilder.() -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = defaultEnterTransition,
        exitTransition = defaultExitTransition,
        popEnterTransition = defaultPopEnterTransition,
        popExitTransition = defaultPopExitTransition,
        builder = builder,
    )
}

val defaultEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
    {
        slideIntoContainer(
            animationSpec = tween(500),
            towards = Left,
        )
    }

val defaultExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
    {
        slideOutOfContainer(
            animationSpec = tween(500),
            towards = Left,
        )
    }

val defaultPopEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
    {
        slideIntoContainer(
            animationSpec = tween(500),
            towards = Right,
        )
    }

val defaultPopExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
    {
        slideOutOfContainer(
            animationSpec = tween(500),
            towards = Right,
        )
    }
