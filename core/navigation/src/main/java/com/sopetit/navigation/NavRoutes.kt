package com.sopetit.navigation

sealed class NavRoutes(val route: String) {

    // OnBoarding Graph
    data object OnBoardingGraph: NavRoutes("onboarding_graph")
    data object OnBoardingScreen: NavRoutes("onboarding")
}