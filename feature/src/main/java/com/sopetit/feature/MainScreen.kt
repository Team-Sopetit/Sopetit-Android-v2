package com.sopetit.feature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sopetit.navigation.NavRoutes
import com.sopetit.navigation.onBoardingNavGraph

@Composable
fun MainScreen() {

    val navController = rememberNavController()

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
                startDestination = NavRoutes.OnBoardingGraph.route
            ) {
                onBoardingNavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun BottomNavBar() {}