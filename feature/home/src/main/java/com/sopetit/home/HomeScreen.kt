package com.sopetit.home

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.sopetit.design_system.Brown100
import com.sopetit.design_system.Brown200
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.HomeRainbowSomTitle
import com.sopetit.design_system.HomeSomCount
import com.sopetit.design_system.HomeSomTitle
import com.sopetit.design_system.R
import com.sopetit.design_system.SoftieTypo
import com.sopetit.domain.entity.response.screen.TutorialModel
import com.sopetit.ui.common.type.CottonType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    showTutorialBottomSheet: (List<TutorialModel>) -> Unit = {},
    isTutorialValid: SharedFlow<Boolean> = MutableSharedFlow(),
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState: HomePageState by viewModel.uiState.collectAsStateWithLifecycle()
    val interactionSource = remember { MutableInteractionSource() }

    val eatingLottieSpec = remember { mutableStateOf<LottieCompositionSpec?>(null) }
    val permissionState = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

    LaunchedEffect(Unit) {
        if (!permissionState.status.isGranted) {
            permissionState.launchPermissionRequest()
        }
    }

    LaunchedEffect(permissionState.status.isGranted) {
        if (permissionState.status.isGranted) {
            viewModel.postFCMToken()
        }
    }

    LaunchedEffect(isTutorialValid) {
        isTutorialValid.collect {
            if (it) {
                showTutorialBottomSheet(uiState.tutorialList)
            }
        }
    }

    HomeScreenContent(
        interactionSource = interactionSource,
        backGroundImg = uiState.homeMemberModel.frameImageUrl,
        conversation = uiState.randomSelectedConversation,
        dollName = uiState.homeMemberModel.name,
        dollHelloResource = uiState.dollHelloResource,
        dailyCottonCount = uiState.dailyCottonCount,
        happinessCottonCount = uiState.happinessCottonCount,
        onClickDoll = {
            viewModel.updateRandomConversation()
        },
        onClickCotton = {
            viewModel.patchCotton(it)
            eatingLottieSpec.value = viewModel.setEatingDollType(it)
        },
        eatingLottieSpec = eatingLottieSpec.value,
        onSetEatingLottieDefault = { eatingLottieSpec.value = null }
    )
}

@Composable
fun HomeScreenContent(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    backGroundImg: String = "",
    conversation: String = "",
    dollName: String = "",
    dollHelloResource: LottieCompositionSpec = LottieCompositionSpec.RawRes(R.raw.brown_hello),
    dailyCottonCount: Int = -1,
    happinessCottonCount: Int = -1,
    eatingLottieSpec: LottieCompositionSpec? = null,
    onClickDoll: () -> Unit = {},
    onClickCotton: (CottonType) -> Unit = {},
    onSetEatingLottieDefault: () -> Unit = {},
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

        Image(
            painter = painterResource(id = R.drawable.ic_splash_logo_black),
            contentDescription = "logo",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 11.dp, start = 20.dp)
                .size(width = 60.dp, height = 18.dp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .wrapContentSize()
                .padding(top = 1.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_home_clova),
                contentDescription = "home clova icon"
            )

            Spacer(modifier = Modifier.width(13.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_home_settings),
                contentDescription = "home setting icon"
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            HomeDollBoxContent(
                interactionSource = interactionSource,
                conversation = conversation,
                dollHelloResource = dollHelloResource,
                onClickDoll = onClickDoll,
                eatingLottieSpec = eatingLottieSpec,
                onSetEatingLottieDefault = onSetEatingLottieDefault
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(99.dp))
                    .border(1.dp, Brown200, RoundedCornerShape(99.dp))
                    .background(Brown100)
            ) {
                Text(
                    text = dollName,
                    color = Gray700,
                    style = SoftieTypo.bubble2,
                    modifier = Modifier
                        .padding(vertical = 9.dp, horizontal = 13.dp)
                )
            }

            Spacer(modifier = Modifier.height(62.dp))

            HomeCottonCount(
                dailyCottonCount = dailyCottonCount,
                happinessCottonCount = happinessCottonCount,
                onClickCotton = onClickCotton
            )
        }
    }
}

@Composable
fun HomeDollBoxContent(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    conversation: String = "",
    dollHelloResource: LottieCompositionSpec = LottieCompositionSpec.RawRes(R.raw.brown_hello),
    onClickDoll: () -> Unit = {},
    eatingLottieSpec: LottieCompositionSpec?,
    onSetEatingLottieDefault: () -> Unit,
) {

    var isPlaying by remember { mutableStateOf(true) }
    var isClickedReplay by remember { mutableStateOf(false) }

    var currentLottieSpec by remember { mutableStateOf(dollHelloResource) }
    var pendingRestore by remember { mutableStateOf(false) }
    val composition by rememberLottieComposition(spec = currentLottieSpec)

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying,
        iterations = 1,
        restartOnPlay = true,
    )

    LaunchedEffect(dollHelloResource) {
        if (!pendingRestore && eatingLottieSpec == null) {
            currentLottieSpec = dollHelloResource
        }
    }

    LaunchedEffect(progress) {
        if (progress >= 1.0f && pendingRestore) {
            currentLottieSpec = dollHelloResource
            isPlaying = true
            pendingRestore = false
            onSetEatingLottieDefault()
        }
    }

    LaunchedEffect(eatingLottieSpec) {
        eatingLottieSpec?.let {
            currentLottieSpec = it
            isPlaying = true
            pendingRestore = true
        }
    }

    Box(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .wrapContentSize()
                .offset(y = (-510).dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_home_speech),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )
            Text(
                text = conversation,
                color = Gray700,
                style = SoftieTypo.bubble1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 21.dp, bottom = 29.dp, start = 32.dp, end = 33.dp)
            )
        }

        LottieAnimation(
            composition = composition,
            progress = {
                if (progress >= 1.0f) isPlaying = false

                if (isClickedReplay) {
                    isClickedReplay = false
                    isPlaying = true
                }

                progress
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .clickable(
                    indication = null,
                    interactionSource = interactionSource,
                    onClick = {
                        if (!isPlaying) {
                            onClickDoll()
                            isPlaying = true
                            isClickedReplay = true
                        }
                    }
                )
        )
    }
}

@Composable
fun HomeCottonCount(
    dailyCottonCount: Int = -1,
    happinessCottonCount: Int = -1,
    onClickCotton: (CottonType) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(bottom = 22.dp)
            .padding(horizontal = 22.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        HomeCottonCountItem(
            cottonCountTitle = HomeSomTitle,
            cottonCountImg = R.drawable.ic_som,
            cottonCount = dailyCottonCount,
            onClickCotton = { onClickCotton(CottonType.DAILY) }
        )

        Spacer(modifier = Modifier.width(12.dp))

        HomeCottonCountItem(
            cottonCountTitle = HomeRainbowSomTitle,
            cottonCountImg = R.drawable.ic_som_rainbow,
            cottonCount = happinessCottonCount,
            onClickCotton = { onClickCotton(CottonType.HAPPINESS) }
        )
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun HomeCottonCountItem(
    cottonCountTitle: String = "",
    cottonCountImg: Int = -1,
    cottonCount: Int = -1,
    onClickCotton: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Gray0)
            .clickable(
                onClick = onClickCotton
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(top = 13.dp)
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = cottonCountImg),
                contentDescription = "cotton img",
                modifier = Modifier
                    .size(30.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = String.format(HomeSomCount, cottonCount),
                color = Gray400,
                style = SoftieTypo.body2
            )
        }

        Text(
            text = cottonCountTitle,
            color = Gray700,
            style = SoftieTypo.body2,
            modifier = Modifier
                .padding(top = 4.dp, bottom = 13.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHome() {
    HomeScreenContent()
}