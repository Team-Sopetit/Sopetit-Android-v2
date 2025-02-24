package com.sopetit.feature

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sopetit.design_system.Gray0
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.feature.component.BottomNavBar
import com.sopetit.navigation.NavRoutes
import com.sopetit.navigation.achieveNavGraph
import com.sopetit.navigation.homeNavGraph
import com.sopetit.navigation.logInNavGraph
import com.sopetit.navigation.onBoardingNavGraph
import com.sopetit.navigation.progressNavGraph
import com.sopetit.navigation.splashNavGraph
import com.sopetit.ui.common.item.CommonSnackBar
import com.sopetit.ui.util.DismissKeyboardOnClick
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {

    val viewModel: MainViewModel = hiltViewModel()
    val uiState: MainPageState by viewModel.uiState.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val snackBarHost = remember { SnackbarHostState() }
    val interactionSource = remember { MutableInteractionSource() }

    val showSnackBar: (String) -> Unit = { message ->
        scope.launch {
            val job = scope.launch {
                snackBarHost.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Indefinite
                )
            }
            delay(1000L)
            job.cancel()
        }
    }

    val settingMemberModel: (CreateMemberModel) -> Unit = {
        scope.launch {
            viewModel.memberModel.emit(it)
        }
    }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow
            .distinctUntilChanged()
            .collect { backStackEntry ->
                viewModel.setBottomNavType(backStackEntry.destination.route)
            }
    }

    DismissKeyboardOnClick {
        Scaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = uiState.bottomNavType != BottomNavType.DEFAULT,
                    modifier = Modifier.background(Gray0),
                    enter = fadeIn() + slideIn { IntOffset(0, 0) },
                    exit = fadeOut() + slideOut { IntOffset(0, 0) }
                ) {
                    BottomNavBar(
                        modifier = Modifier.navigationBarsPadding(),
                        interactionSource = interactionSource,
                        type = uiState.bottomNavType,
                        onClick = { route: String ->
                            if (navController.currentDestination?.route != route) {
                                navController.navigate(route) {
                                    popUpTo(navController.currentDestination?.route!!) {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            }
                        }
                    )
                }
            },
            snackbarHost = { CommonSnackBar(hostState = snackBarHost) }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .statusBarsPadding()
            ) {
                NavHost(
                    navController = navController,
                    startDestination = NavRoutes.SplashGraph.route
                ) {
                    splashNavGraph(
                        navController = navController
                    )
                    logInNavGraph(
                        navController = navController
                    )
                    onBoardingNavGraph(
                        navController = navController,
                        setMemberModel = settingMemberModel,
                        showSnackBar = showSnackBar,
                        memberModel = viewModel.memberModel,
                    )
                    homeNavGraph(
                        navController = navController
                    )
                    progressNavGraph(
                        navController = navController
                    )
                    achieveNavGraph(
                        navController = navController
                    )
                }
            }
        }
    }
}