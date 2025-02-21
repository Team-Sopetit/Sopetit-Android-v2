package com.sopetit.feature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.navigation.NavRoutes
import com.sopetit.navigation.homeNavGraph
import com.sopetit.navigation.logInNavGraph
import com.sopetit.navigation.onBoardingNavGraph
import com.sopetit.navigation.splashNavGraph
import com.sopetit.ui.common.item.CommonSnackBar
import com.sopetit.ui.util.DismissKeyboardOnClick
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {

    val viewModel: MainViewModel = hiltViewModel()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val snackBarHost = remember { SnackbarHostState() }

    val showSnackBar: (String) -> Unit = { message ->
        scope.launch { snackBarHost.showSnackbar(message = message) }
    }

    val settingMemberModel: (CreateMemberModel) -> Unit = {
        scope.launch {
            viewModel.memberModel.emit(it)
        }
    }

    DismissKeyboardOnClick {
        Scaffold(
            bottomBar = { BottomNavBar() },
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
                }
            }
        }
    }
}

@Composable
fun BottomNavBar() {
}