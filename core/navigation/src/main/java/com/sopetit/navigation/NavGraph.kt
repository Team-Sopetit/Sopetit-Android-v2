package com.sopetit.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopetit.onboarding.dollnaming.DollNamingScreen
import com.sopetit.onboarding.dolltype.DollTypeChoiceScreen
import com.sopetit.onboarding.storytelling.StoryTellingFirstScreen
import com.sopetit.onboarding.storytelling.StoryTellingSecondScreen
import com.sopetit.onboarding.storytelling.StoryTellingThirdScreen

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
            StoryTellingSecondScreen(
                goToThirdStoryPage = { navController.navigate(NavRoutes.StoryTellingThirdScreen.route) }
            )
        }

        composable(NavRoutes.StoryTellingThirdScreen.route) {
            StoryTellingThirdScreen(
                goToDollTypeChoicePage = { navController.navigate(NavRoutes.DollTypeChoiceScreen.route) }
            )
        }

        composable(NavRoutes.DollTypeChoiceScreen.route) {
            DollTypeChoiceScreen(
                goToDollNamingPage = { navController.navigate(NavRoutes.DollNamingScreen.route) }
            )
        }

        composable(NavRoutes.DollNamingScreen.route) {
            DollNamingScreen()
        }
    }
}