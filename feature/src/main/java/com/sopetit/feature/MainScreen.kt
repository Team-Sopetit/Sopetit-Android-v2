package com.sopetit.feature

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray1000
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.R
import com.sopetit.design_system.SoftieTypo
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.domain.entity.response.memo.MemoActionModel
import com.sopetit.domain.entity.response.routine.ChallengeChangeModel
import com.sopetit.domain.entity.response.screen.RoutineDetailModel
import com.sopetit.domain.entity.response.screen.TutorialModel
import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.feature.component.BottomNavBar
import com.sopetit.navigation.NavRoutes
import com.sopetit.navigation.achieveNavGraph
import com.sopetit.navigation.addRoutineNavGraph
import com.sopetit.navigation.customRoutineNavGraph
import com.sopetit.navigation.homeNavGraph
import com.sopetit.navigation.logInNavGraph
import com.sopetit.navigation.onBoardingNavGraph
import com.sopetit.navigation.progressNavGraph
import com.sopetit.navigation.settingNavGraph
import com.sopetit.navigation.splashNavGraph
import com.sopetit.ui.common.bottomsheet.ChallengeChangeBottomSheet
import com.sopetit.ui.common.bottomsheet.MemoWriteBottomSheet
import com.sopetit.ui.common.bottomsheet.RoutineDetailBottomSheet
import com.sopetit.ui.common.bottomsheet.TutorialBottomSheet
import com.sopetit.ui.common.bottomsheet.TwoBtnDetailBottomSheet
import com.sopetit.ui.common.bottomsheet.TwoBtnIconBottomSheet
import com.sopetit.ui.common.dialog.TwoBtnDialog
import com.sopetit.ui.common.item.CommonSnackBar
import com.sopetit.ui.common.model.TwoBtnDialogModel
import com.sopetit.ui.common.model.TwoBtnIconModel
import com.sopetit.ui.common.type.BottomSheetType
import com.sopetit.ui.common.type.IntentNavigationType
import com.sopetit.ui.common.type.TwoBtnBottomSheetType
import com.sopetit.ui.util.CommonEventManager
import com.sopetit.ui.util.DismissKeyboardOnClick
import com.sopetit.ui.util.intentToUrl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = hiltViewModel()
    val uiState: MainPageState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val snackBarHost = remember { SnackbarHostState() }
    val interactionSource = remember { MutableInteractionSource() }
    val sheetState =
        rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true,
        )

    val isShowDialog = remember { mutableStateOf(false) }
    val snackBarPadding = remember { mutableStateOf(0) }
    val snackBarIcon = remember { mutableStateOf(R.drawable.ic_snackbar_caution) }
    val showSnackBar: (String, Int, Int) -> Unit = { message, paddingBottom, icon ->
        scope.launch {
            val job =
                scope.launch {
                    snackBarPadding.value = paddingBottom
                    snackBarIcon.value = icon
                    snackBarHost.showSnackbar(
                        message = message,
                        duration = SnackbarDuration.Indefinite,
                    )
                }
            delay(1000L)
            job.cancel()
        }
    }
    val showTwoBtnDialog: (TwoBtnDialogModel) -> Unit = {
        viewModel.onSetTwoBtnDialog(it)
        isShowDialog.value = true
    }
    val showTutorialBottomSheet: (List<TutorialModel>) -> Unit = { tutorials ->
        viewModel.setTutorials(tutorials)
        scope.launch { sheetState.show() }
    }
    val showRoutineBottomSheetOneBtn: (RoutineDetailModel) -> Unit = { routine ->
        viewModel.setRoutineDetail(routine, BottomSheetType.ROUTINE)
        scope.launch { sheetState.show() }
    }
    val showRoutineBottomSheetTwoBtn: (RoutineDetailModel) -> Unit = { routine ->
        viewModel.setRoutineDetail(routine, BottomSheetType.BOTTOMTWOBTN)
        scope.launch { sheetState.show() }
    }
    val showChallengeChangeBottomSheet: (ChallengeChangeModel) -> Unit = { challenge ->
        viewModel.setChallengeChange(challenge)
        scope.launch { sheetState.show() }
    }
    val showRoutineMemoWriteBottomSheet: (MemoActionModel) -> Unit = {
        viewModel.setRoutineMemoBottomSheet(it)
        scope.launch { sheetState.show() }
    }
    val showMemoDetailBottomSheet: (MemoActionModel) -> Unit = {
        viewModel.setMemoDetail(it)
        scope.launch { sheetState.show() }
    }
    val showTwoBtnIconBottomSheet: (TwoBtnIconModel) -> Unit = {
        viewModel.setTwoBtnIconBottomSheet(it)
        scope.launch { sheetState.show() }
    }

    val settingMemberModel: (CreateMemberModel) -> Unit = {
        scope.launch {
            viewModel.memberModel.emit(it)
        }
    }
    val setTutorialValid: (Boolean) -> Unit = {
        scope.launch {
            viewModel.isTutorialValid.emit(it)
        }
    }
    val setSelectedTheme: (ThemeListItemModel) -> Unit = {
        scope.launch {
            viewModel.selectedTheme.emit(it)
        }
    }
    val setAchieveThemeId: (Int) -> Unit = {
        scope.launch {
            viewModel.achieveThemeId.emit(it)
        }
    }

    LaunchedEffect(Unit) {
        CommonEventManager.logoutTriggerFlow.collect {
            navController.navigate(NavRoutes.LogInGraph.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow
            .distinctUntilChanged()
            .collect { backStackEntry ->
                viewModel.setBottomNavType(backStackEntry.destination.route)
            }
    }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .windowInsetsPadding(
                    WindowInsets.navigationBars.only(WindowInsetsSides.Bottom),
                ),
    ) {
        DismissKeyboardOnClick {
            if (isShowDialog.value) {
                TwoBtnDialog(
                    twoBtnDialogModel = uiState.twoBtnDialogModel,
                    onDismiss = { isShowDialog.value = false },
                    onClickDoBtn = {
                        isShowDialog.value = false
                        intentToUrl(context, IntentNavigationType.FEEDBACK)
                    },
                )
            }

            ModalBottomSheetLayout(
                sheetState = sheetState,
                sheetContent = {
                    AnimatedContent(
                        targetState = uiState.bottomSheetType,
                        transitionSpec = {
                            fadeIn(animationSpec = tween(500)) togetherWith
                                fadeOut(
                                    animationSpec =
                                        tween(
                                            500,
                                        ),
                                )
                        },
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .navigationBarsPadding(),
                        label = "",
                    ) { currentSheet ->
                        when (currentSheet) {
                            BottomSheetType.TUTORIAL -> {
                                TutorialBottomSheet(
                                    tutorials = uiState.tutorials,
                                    closeTutorials = {
                                        scope.launch {
                                            sheetState.hide()
                                            viewModel.isTutorialValid.emit(false)
                                        }
                                    },
                                )
                            }

                            BottomSheetType.ROUTINE -> {
                                RoutineDetailBottomSheet(
                                    routine = uiState.routineDetail,
                                    onClickRoutineDeleteBtn = {
                                        scope.launch {
                                            viewModel.deleteRoutineId.emit(it)
                                            sheetState.hide()
                                        }
                                    },
                                    onClickConfirmBtn = {
                                        scope.launch {
                                            sheetState.hide()
                                        }
                                    },
                                )
                            }

                            BottomSheetType.CHALLENGECHANGE -> {
                                ChallengeChangeBottomSheet(
                                    challengeChangeModel = uiState.challengeChangeModel,
                                )
                            }

                            BottomSheetType.MEMO -> {
                                MemoWriteBottomSheet(
                                    onClickConfirmBtn = {
                                        scope.launch {
                                            viewModel.writtenMemo.emit(it)
                                            sheetState.hide()
                                        }
                                    },
                                    memoActionModel = uiState.memoActionModel,
                                    onClickModifyBtn = {
                                        scope.launch {
                                            viewModel.setMemoDetail(it)
                                            sheetState.hide()
                                        }
                                    },
                                )
                            }

                            BottomSheetType.BOTTOMTWOBTN -> {
                                TwoBtnDetailBottomSheet(
                                    memoActionModel = uiState.memoActionModel,
                                    onClickMemoDeleteBtn = {
                                        scope.launch {
                                            viewModel.setMemoDetail(it)
                                            sheetState.hide()
                                        }
                                    },
                                    onClickRoutineDeleteBtn = {
                                        scope.launch {
                                            viewModel.deleteRoutineId.emit(it)
                                            sheetState.hide()
                                        }
                                    },
                                    onClickModBtn = {
                                        scope.launch {
                                            sheetState.hide()

                                            if (uiState.twoBtnType == TwoBtnBottomSheetType.MemoWrite) {
                                                showRoutineMemoWriteBottomSheet(uiState.memoActionModel)
                                            } else {
                                                viewModel.modRoutine.emit(uiState.routineDetail)
                                            }
                                        }
                                    },
                                    type = uiState.twoBtnType,
                                    routine = uiState.routineDetail,
                                )
                            }

                            BottomSheetType.BOTTOMTWOBTNICON -> {
                                TwoBtnIconBottomSheet(
                                    twoBtnIconModel = uiState.twoBtnIconModel,
                                    onClickLeftBtn = {
                                        scope.launch {
                                            sheetState.hide()
                                        }
                                    },
                                    onClickRightBtn = {
                                        scope.launch {
                                            sheetState.hide()
                                            viewModel.isSelectedLogOut.emit(true)
                                        }
                                    },
                                )
                            }

                            BottomSheetType.DEFAULT -> {}
                        }
                    }
                },
                sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                scrimColor = Color(0, 0, 0, 128),
            ) {
                Scaffold(
                    bottomBar = {
                        AnimatedVisibility(
                            visible = uiState.bottomNavType != BottomNavType.DEFAULT,
                            modifier = Modifier.background(Gray0),
                            enter = fadeIn() + slideIn { IntOffset(0, 0) },
                            exit = fadeOut() + slideOut { IntOffset(0, 0) },
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
                                },
                            )
                        }
                    },
                ) { innerPadding ->
                    Box(
                        modifier =
                            Modifier
                                .padding(innerPadding)
                                .statusBarsPadding(),
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = NavRoutes.SplashGraph.route,
                        ) {
                            splashNavGraph(
                                navController = navController,
                                setTutorialValid = setTutorialValid,
                            )
                            logInNavGraph(
                                navController = navController,
                                setTutorialValid = setTutorialValid,
                            )
                            onBoardingNavGraph(
                                navController = navController,
                                setMemberModel = settingMemberModel,
                                showSnackBar = showSnackBar,
                                memberModel = viewModel.memberModel,
                            )
                            homeNavGraph(
                                navController = navController,
                                showTutorialBottomSheet = showTutorialBottomSheet,
                                isTutorialValid = viewModel.isTutorialValid,
                                showFeedbackDialog = showTwoBtnDialog,
                            )
                            progressNavGraph(
                                navController = navController,
                                showChallengeRoutineBottomSheet = showRoutineBottomSheetOneBtn,
                                showDailyRoutineBottomSheet = showRoutineBottomSheetTwoBtn,
                                deleteRoutineId = viewModel.deleteRoutineId,
                                modRoutine = viewModel.modRoutine,
                                showChallengeAchieveSom = { viewModel.updateChallengeAchieve(it) },
                                showChallengeDailySom = { viewModel.updateDailyAchieve(it) },
                                showSnackBar = showSnackBar,
                                showToolTip = { offset, title, content ->
                                    viewModel.initSetTooltip(true, offset, title, content)
                                },
                            )
                            achieveNavGraph(
                                navController = navController,
                                showRoutineBottomSheet = showRoutineBottomSheetOneBtn,
                                deleteRoutineId = viewModel.deleteRoutineId,
                                showMemoWriteBottomSheet = {
                                    showRoutineMemoWriteBottomSheet(
                                        MemoActionModel(),
                                    )
                                },
                                writtenMemo = viewModel.writtenMemo,
                                showMemoDetailBottomSheet = showMemoDetailBottomSheet,
                                memoActionModel = viewModel.memoActionModel,
                                setAchieveThemeId = setAchieveThemeId,
                                achieveThemeId = viewModel.achieveThemeId,
                            )
                            addRoutineNavGraph(
                                navController = navController,
                                setSelectedThemeId = setSelectedTheme,
                                selectedThemeId = viewModel.selectedTheme,
                                showChallengeDetailBottomSheet = showRoutineBottomSheetOneBtn,
                                showSnackBar = showSnackBar,
                                showChallengeChangeBottomSheet = showChallengeChangeBottomSheet,
                            )
                            customRoutineNavGraph(
                                navController = navController,
                            )
                            settingNavGraph(
                                navController = navController,
                                showLogOutBottomSheet = showTwoBtnIconBottomSheet,
                                isSelectedLogOut = viewModel.isSelectedLogOut,
                            )
                        }
                    }
                }
            }

            if (uiState.isChallengeAchieveShowValid) {
                val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.happy_complete_som))
                var dismissed by remember { mutableStateOf(false) }
                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    iterations = 1,
                    isPlaying = !dismissed,
                )

                fun dismiss() {
                    if (!dismissed) {
                        dismissed = true
                        viewModel.updateChallengeAchieve(false)
                    }
                }

                LaunchedEffect(progress) {
                    if (progress >= 1.0f) dismiss()
                }

                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(Gray1000)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = { dismiss() },
                            )
                            .zIndex(1f),
                ) {
                    LottieAnimation(
                        composition = composition,
                        progress = { progress },
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }

            if (uiState.isDailyAchieveShowValid) {
                val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.daily_complete_som))
                var dismissed by remember { mutableStateOf(false) }
                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    iterations = 1,
                    isPlaying = !dismissed,
                )

                fun dismiss() {
                    if (!dismissed) {
                        dismissed = true
                        viewModel.updateDailyAchieve(false)
                    }
                }

                LaunchedEffect(progress) {
                    if (progress >= 1.0f) dismiss()
                }

                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(Gray1000)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = { dismiss() },
                            )
                            .zIndex(1f),
                ) {
                    LottieAnimation(
                        composition = composition,
                        progress = { progress },
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }

            if (uiState.isTooltipShowValid) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(Gray1000)
                            .clickable(
                                onClick = { viewModel.updateTooltipState(false) },
                            ),
                ) {
                    Popup(
                        alignment = Alignment.TopEnd,
                        offset = uiState.tooltipOffSet,
                    ) {
                        Column(
                            modifier =
                                Modifier
                                    .padding(end = 20.dp)
                                    .background(Gray0, RoundedCornerShape(10.dp))
                                    .width(272.dp),
                        ) {
                            Row(
                                modifier =
                                    Modifier
                                        .padding(start = 16.dp, end = 12.dp, top = 12.dp)
                                        .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = uiState.tooltipTitle,
                                    color = Gray700,
                                    style = SoftieTypo.head4,
                                    modifier =
                                        Modifier
                                            .weight(1f),
                                )

                                Image(
                                    painter = painterResource(id = R.drawable.ic_close),
                                    contentDescription = "close tooltip",
                                    modifier =
                                        Modifier
                                            .size(18.dp)
                                            .clickable(
                                                onClick = { viewModel.updateTooltipState(false) },
                                                interactionSource = interactionSource,
                                                indication = null,
                                            ),
                                )
                            }

                            Text(
                                text = uiState.tooltipContent,
                                color = Gray650,
                                style = SoftieTypo.caption1,
                                modifier =
                                    Modifier
                                        .padding(top = 6.dp, bottom = 16.dp)
                                        .padding(horizontal = 16.dp)
                                        .fillMaxWidth(),
                            )
                        }
                    }
                }
            }

            CommonSnackBar(
                hostState = snackBarHost,
                paddingBottom = snackBarPadding.value,
                iconResource = snackBarIcon.value,
                modifier =
                    Modifier
                        .align(Alignment.BottomCenter)
                        .zIndex(2f),
            )
        }
    }
}
