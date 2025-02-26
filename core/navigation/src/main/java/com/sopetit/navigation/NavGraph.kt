package com.sopetit.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopetit.achieve.AchieveScreen
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.home.HomeScreen
import com.sopetit.login.LogInScreen
import com.sopetit.onboarding.dollnaming.DollNamingScreen
import com.sopetit.onboarding.dolltype.DollTypeChoiceScreen
import com.sopetit.onboarding.routinechoice.RoutineChoiceScreen
import com.sopetit.onboarding.storytelling.StoryTellingFirstScreen
import com.sopetit.onboarding.storytelling.StoryTellingSecondScreen
import com.sopetit.onboarding.storytelling.StoryTellingThirdScreen
import com.sopetit.onboarding.themechoice.ThemeChoiceScreen
import com.sopetit.progress.ProgressScreen
import com.sopetit.splash.SplashScreen
import kotlinx.coroutines.flow.SharedFlow

fun NavGraphBuilder.splashNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = NavRoutes.SplashScreen.route,
        route = NavRoutes.SplashGraph.route
    ) {
        composable(NavRoutes.SplashScreen.route) {
            SplashScreen(
                goToKaKaoLogIn = { navController.navigate(NavRoutes.LogInScreen.route) }
            )
        }
    }
}

fun NavGraphBuilder.logInNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = NavRoutes.LogInScreen.route,
        route = NavRoutes.LogInGraph.route
    ) {
        composable(NavRoutes.LogInScreen.route) {
            LogInScreen(
                goToOnboarding = {
                    navController.navigate(NavRoutes.StoryTellingFirstScreen.route) {
                        popUpTo(0)
                    }
                },
                goToHome = {
                    navController.navigate(NavRoutes.HomeScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}

fun NavGraphBuilder.onBoardingNavGraph(
    navController: NavHostController,
    showSnackBar: (String) -> Unit,
    setMemberModel: (CreateMemberModel) -> Unit,
    memberModel: SharedFlow<CreateMemberModel>,
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
                goToDollNamingPage = {
                    setMemberModel(it)
                    navController.navigate(NavRoutes.DollNamingScreen.route)
                }
            )
        }

        composable(NavRoutes.DollNamingScreen.route) {
            DollNamingScreen(
                memberModel = memberModel,
                goToThemeChoicePage = {
                    setMemberModel(it)
                    navController.navigate(NavRoutes.ThemeChoiceScreen.route)
                },
                goBackToDollTypePage = { navController.popBackStack() }
            )
        }

        composable(NavRoutes.ThemeChoiceScreen.route) {
            ThemeChoiceScreen(
                memberModel = memberModel,
                goBackToDollNamingPage = { navController.popBackStack() },
                goToRoutineChoicePage = {
                    setMemberModel(it)
                    navController.navigate(NavRoutes.RoutineChoiceScreen.route)
                }
            )
        }

        composable(NavRoutes.RoutineChoiceScreen.route) {
            RoutineChoiceScreen(
                goBackToThemeChoicePage = { navController.popBackStack() },
                memberModel = memberModel,
                showSnackBar = showSnackBar,
                goToHomePage = {
                    navController.navigate(NavRoutes.HomeScreen.route) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    showTutorialBottomSheet: () -> Unit
) {
    navigation(
        startDestination = NavRoutes.HomeScreen.route,
        route = NavRoutes.HomeGraph.route
    ) {
        composable(NavRoutes.HomeScreen.route) {
            HomeScreen(
                showTutorialBottomSheet = showTutorialBottomSheet
            )
        }
    }
}

fun NavGraphBuilder.achieveNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = NavRoutes.AchieveScreen.route,
        route = NavRoutes.AchieveGraph.route
    ) {
        composable(NavRoutes.AchieveScreen.route) {
            AchieveScreen()
        }
    }
}

fun NavGraphBuilder.progressNavGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = NavRoutes.ProgressScreen.route,
        route = NavRoutes.ProgressGraph.route
    ) {
        composable(NavRoutes.ProgressScreen.route) {
            ProgressScreen()
        }
    }
}