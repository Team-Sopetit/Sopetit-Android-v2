package com.sopetit.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState: HomePageState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        backGroundImg = uiState.homeMemberModel.frameImageUrl
    )
}

@Composable
fun HomeScreenContent(
    backGroundImg: String = "",
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = backGroundImg),
            contentDescription = "background frame",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHome() {
    HomeScreenContent()
}