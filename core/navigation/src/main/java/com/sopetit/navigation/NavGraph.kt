package com.sopetit.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopetit.onboarding.storytelling.StoryTellingFirstScreen
import com.sopetit.onboarding.storytelling.StoryTellingSecondScreen

fun NavGraphBuilder.onBoardingNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = NavRoutes.StoryTellingFirstScreen.route,
        route = NavRoutes.OnBoardingGraph.route
    ) {
        composable(NavRoutes.StoryTellingFirstScreen.route) {
            StoryTellingFirstScreen(
                goToSecondStoryPage = { navController.navigate(NavRoutes.StoryTellingSecondScreen.route) }
            )
        }

        composable(NavRoutes.StoryTellingSecondScreen.route) {
            StoryTellingSecondScreen()
        }
    }
}