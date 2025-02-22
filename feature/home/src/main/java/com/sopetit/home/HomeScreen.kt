package com.sopetit.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.design_system.Gray50

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState: HomePageState by viewModel.uiState.collectAsStateWithLifecycle()
    
    HomeScreenContent()
}

@Composable
fun HomeScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
    ) {
        //
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHome() {
    HomeScreenContent()
}