package com.sopetit.navigation

sealed class NavRoutes(val route: String) {

    // Splash Graph
    data object SplashGraph: NavRoutes("splash_graph")
    data object SplashScreen: NavRoutes("splash")

    // LogIn Graph
    data object LogInGraph: NavRoutes("login_graph")
    data object LogInScreen: NavRoutes("login")

    // OnBoarding Graph
    data object OnBoardingGraph: NavRoutes("onboarding_graph")
    data object StoryTellingFirstScreen: NavRoutes("story_telling_first")
    data object StoryTellingSecondScreen: NavRoutes("story_telling_second")
    data object StoryTellingThirdScreen: NavRoutes("story_telling_third")

    data object DollTypeChoiceScreen: NavRoutes("doll_type_choice")
    data object DollNamingScreen: NavRoutes("doll_naming")
    data object ThemeChoiceScreen: NavRoutes("theme_choice")
    data object RoutineChoiceScreen: NavRoutes("routine_choice")

    // Home Graph
    data object HomeGraph: NavRoutes("home_graph")
    data object HomeScreen: NavRoutes("home")

    // Achieve Graph
    data object AchieveGraph: NavRoutes("achieve_graph")
    data object AchieveScreen: NavRoutes("achieve")
    data object AchieveRoutineScreen: NavRoutes("achieve_routine")

    // Progress Graph
    data object ProgressGraph: NavRoutes("progress_graph")
    data object ProgressScreen: NavRoutes("progress")

    // Add Routine Graph
    data object AddRoutineGraph: NavRoutes("add_routine_graph")
    data object AddRoutineScreen: NavRoutes("add_routine")
    data object AddRoutineDetailScreen: NavRoutes("add_routine_detail")

    // Custom Routine Graph
    data object CustomRoutineGraph: NavRoutes("custom_routine_graph")
    data object CustomRoutineScreen: NavRoutes("custom_routine")
}