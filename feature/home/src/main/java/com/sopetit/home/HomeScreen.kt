package com.sopetit.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sopetit.design_system.R

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState: HomePageState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        backGroundImg = uiState.homeMemberModel.frameImageUrl,
        dollHelloResource = uiState.dollHelloResource
    )
}

@Composable
fun HomeScreenContent(
    backGroundImg: String = "",
    dollHelloResource: LottieCompositionSpec = LottieCompositionSpec.RawRes(R.raw.brown_hello)
) {

    val composition by rememberLottieComposition(spec = dollHelloResource)
//    val progress by animateLottieCompositionAsState(
//        composition = composition,
//        iterations = LottieConstants.IterateForever
//    )

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

        Image(
            painter = painterResource(id = R.drawable.ic_splash_logo_black),
            contentDescription = "logo",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 11.dp, start = 20.dp)
                .size(width = 60.dp, height = 18.dp)
        )

        LottieAnimation(
            composition = composition,
//            progress = { progress },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHome() {
    HomeScreenContent()
}