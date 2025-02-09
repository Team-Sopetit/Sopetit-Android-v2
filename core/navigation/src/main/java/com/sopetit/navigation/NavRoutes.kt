package com.sopetit.navigation

sealed class NavRoutes(val route: String) {

    // OnBoarding Graph
    data object OnBoardingGraph: NavRoutes("onboarding_graph")
    data object StoryTellingFirstScreen: NavRoutes("story_telling_first")
    data object StoryTellingSecondScreen: NavRoutes("story_telling_second")
    data object StoryTellingThirdScreen: NavRoutes("story_telling_third")

    data object DollTypeChoiceScreen: NavRoutes("doll_type_choice")
    data object DollNamingScreen: NavRoutes("doll_naming")
    data object ThemeChoiceScreen: NavRoutes("theme_choice")
}