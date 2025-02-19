package com.sopetit.feature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sopetit.domain.entity.enums.DollType
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.navigation.NavRoutes
import com.sopetit.navigation.logInNavGraph
import com.sopetit.navigation.onBoardingNavGraph
import com.sopetit.navigation.splashNavGraph
import com.sopetit.ui.util.DismissKeyboardOnClick
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {

    val viewModel: MainViewModel = hiltViewModel()
    val uiState: MainPageState by viewModel.uiState.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

//    val selectedDollType: (DollType) -> Unit = {
//        scope.launch {
//            viewModel.selectedDollType.emit(it)
//        }
//    }
    val settingMemberModel: (CreateMemberModel) -> Unit = {
        scope.launch {
            viewModel.memberModel.emit(it)
        }
    }
    val selectedThemeIds: (List<Int>) -> Unit = {
        scope.launch {
            viewModel.selectedThemeIds.emit(it)
        }
    }

    DismissKeyboardOnClick {
        Scaffold(
            bottomBar = { BottomNavBar() }
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
//                        setSelectedDollType = selectedDollType,
//                        setSelectedDollType = { viewModel.setSelectedDollType(it) },
//                        selectedDollType = viewModel.selectedDollType,
                        setMemberModel = settingMemberModel,
                        memberModel = viewModel.memberModel,
//                        selectedDollType = uiState.selectedDollType,
//                        setSelectedThemeIds = selectedThemeIds,
//                        selectedThemeIds = viewModel.selectedThemeIds
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavBar() {
}