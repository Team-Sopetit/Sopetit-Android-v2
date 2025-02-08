package com.sopetit.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopetit.onboarding.storytelling.StoryTellingScreen

fun NavGraphBuilder.onBoardingNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = NavRoutes.StoryTellingScreen.route,
        route = NavRoutes.OnBoardingGraph.route
    ) {
        composable(NavRoutes.StoryTellingScreen.route) {
            StoryTellingScreen()
        }
    }
}