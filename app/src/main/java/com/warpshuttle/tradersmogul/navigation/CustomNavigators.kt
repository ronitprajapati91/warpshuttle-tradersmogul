package com.warpshuttle.tradersmogul.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.*
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi

@ExperimentalMaterialNavigationApi
@Composable
fun rememberCustomBottomSheetNavigator(
    animationSpec: AnimationSpec<Float> = androidx.compose.material.SwipeableDefaults.AnimationSpec
): BottomSheetNavigator {
    val sheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
        animationSpec = animationSpec
    )
    return remember(sheetState) {
        BottomSheetNavigator(sheetState = sheetState)
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberCustomAnimatedNavController(vararg navigators: Navigator<out NavDestination>): NavHostController =
    rememberNavController(*navigators).apply {
        navigatorProvider += remember(this) { ComposeNavigator() }
    }

const val NavigationDuration = 500

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.customAnimatedComposable(
    route: String,
    content: @Composable (NavBackStackEntry) -> Unit
) = composable(route, enterTransition = {
    slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(NavigationDuration)
    )
    /*when (initialState.destination.route) {
        Screen.Home.Tutorial.route ->
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        else -> null
    }*/
},
    exitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(NavigationDuration)
        )
        /*when (targetState.destination.route) {
            Screen.Home.Tutorial.route ->
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            else -> null
        }*/
    },
    popEnterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(NavigationDuration)
        )
        /*when (initialState.destination.route) {
            Screen.Home.Tutorial.route ->
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            else -> null
        }*/
    },
    popExitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(NavigationDuration)
        )
        /*when (targetState.destination.route) {
            Screen.Home.Tutorial.route ->
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            else -> null
        }*/
    }) {
    content(it)
}