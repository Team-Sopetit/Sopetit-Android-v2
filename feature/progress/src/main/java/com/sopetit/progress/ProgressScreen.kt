package com.sopetit.progress

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray500
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.MemberChallengeAchieve
import com.sopetit.design_system.MemberDailyAchieve
import com.sopetit.design_system.MemberDailyAchieveCancel
import com.sopetit.design_system.MemberDailyAchieveHasSomFalse
import com.sopetit.design_system.ProgressChallengeEmptyTitle
import com.sopetit.design_system.ProgressChallengeTitle
import com.sopetit.design_system.ProgressDailyEmptyTitle
import com.sopetit.design_system.ProgressDailyTitle
import com.sopetit.design_system.ProgressEmptyAddTitle
import com.sopetit.design_system.ProgressEmptyTitle
import com.sopetit.design_system.ProgressTitleDate
import com.sopetit.design_system.Question
import com.sopetit.design_system.R
import com.sopetit.design_system.SoftieTypo
import com.sopetit.domain.entity.enums.RoutineType
import com.sopetit.domain.entity.response.memberchallenge.MemberChallengeModel
import com.sopetit.domain.entity.response.memberroutine.MemberDailyRoutineListModel
import com.sopetit.domain.entity.response.screen.RoutineDetailModel
import com.sopetit.ui.common.button.RoundCornerShapeBtn
import com.sopetit.ui.common.content.ChallengeRoutineBox
import com.sopetit.ui.common.content.EmptyRoutineScreen
import com.sopetit.ui.common.item.MemberDailyRoutineListItem
import com.sopetit.ui.common.type.ThemeIconType
import kotlinx.coroutines.flow.SharedFlow
import org.threeten.bp.LocalDate

@Composable
fun ProgressScreen(
    showRoutineBottomSheet: (RoutineDetailModel) -> Unit = {},
    deleteRoutineId: SharedFlow<RoutineDetailModel>,
    showChallengeAchieveSom: (Boolean) -> Unit = {},
    showChallengeDailySom: (Boolean) -> Unit = {},
    showSnackBar: (String, Int, Int) -> Unit,
    showTooltip: () -> Unit
) {

    val viewModel: ProgressViewModel = hiltViewModel()
    val uiState: ProgressPageState by viewModel.uiState.collectAsStateWithLifecycle()
    val interactionSource = remember { MutableInteractionSource() }

    val today = LocalDate.now()

    LaunchedEffect(deleteRoutineId) {
        deleteRoutineId.collect {
            viewModel.deleteRoutine(it.routineType, it.routineId)
        }
    }
    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is ProgressEvent.OnShowChallengeAchieveSom -> {
                    showChallengeAchieveSom(true)
                    showSnackBar(MemberChallengeAchieve, 24, R.drawable.ic_som_rainbow)
                }

                is ProgressEvent.OnShowDailyAchieveSom -> {
                    showChallengeDailySom(true)
                    showSnackBar(MemberDailyAchieve, 24, R.drawable.ic_som)
                }

                is ProgressEvent.OnShowDailyAchieveHasSomFalse -> {
                    showSnackBar(MemberDailyAchieveHasSomFalse, 24, R.drawable.ic_snackbar_caution)
                }

                is ProgressEvent.OnShowDailyAchieveCancel -> {
                    showSnackBar(MemberDailyAchieveCancel, 24, R.drawable.ic_toast_check)
                }
            }
        }
    }

    ProgressContent(
        todayYear = today.year,
        todayMonth = today.monthValue,
        todayDay = today.dayOfMonth,
        memberChallenge = uiState.memberChallenge,
        memberDailyRoutineList = uiState.memberDailyRoutineList,
        onClickRoutineDetail = { routine ->
            showRoutineBottomSheet(routine)
        },
        interactionSource = interactionSource,
        onClickChallengeAchieveBtn = {
            viewModel.achieveChallengeRoutine()
        },
        onClickDailyAchieve = { routineId ->
            viewModel.achieveDailyRoutine(routineId)
        },
        onClickTooltipBtn = { showTooltip() }
    )
}

@Composable
fun ProgressContent(
    todayYear: Int = 0,
    todayMonth: Int = 0,
    todayDay: Int = 0,
    memberChallenge: MemberChallengeModel = MemberChallengeModel(),
    memberDailyRoutineList: List<MemberDailyRoutineListModel> = emptyList(),
    onClickRoutineDetail: (RoutineDetailModel) -> Unit = {},
    onClickChallengeAchieveBtn: () -> Unit = {},
    onClickDailyAchieve: (Int) -> Unit = {},
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClickTooltipBtn: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
    ) {
        Text(
            text = String.format(ProgressTitleDate, todayYear, todayMonth, todayDay),
            color = Gray700,
            style = SoftieTypo.head3,
            modifier = Modifier
                .padding(start = 20.dp)
                .padding(vertical = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ProgressRoutineContent(
                memberChallenge = memberChallenge,
                memberDailyRoutineList = memberDailyRoutineList,
                onClickRoutineDetail = onClickRoutineDetail,
                onClickChallengeAchieveBtn = onClickChallengeAchieveBtn,
                onClickDailyAchieve = onClickDailyAchieve,
                onClickTooltipBtn = onClickTooltipBtn
            )
        }
    }
}

@Composable
fun ProgressRoutineContent(
    memberChallenge: MemberChallengeModel = MemberChallengeModel(),
    memberDailyRoutineList: List<MemberDailyRoutineListModel> = emptyList(),
    onClickRoutineDetail: (RoutineDetailModel) -> Unit,
    onClickChallengeAchieveBtn: () -> Unit = {},
    onClickDailyAchieve: (Int) -> Unit,
    onClickTooltipBtn: () -> Unit
) {
    if (memberChallenge.memberChallengeId == -1 && memberDailyRoutineList.isEmpty()) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 150.dp)
        ) {
            EmptyRoutineScreen(
                titleContent = ProgressEmptyTitle,
                titleColor = Gray500,
                titleStyle = SoftieTypo.head3,
                btnBackgroundColor = Gray650,
                btnCornerShape = 100,
                btnBorderColor = Gray650,
                btnTextContent = ProgressEmptyAddTitle,
                btnTextColor = Gray0,
                btnTextStyle = SoftieTypo.caption1,
                btnVerticalPadding = 12,
                btnHorizontalPadding = 16
            )
        }
    } else {
        if (memberChallenge.memberChallengeId != -1) {
            ProgressChallenge(
                memberChallenge = memberChallenge,
                onClickRoutineDetail = onClickRoutineDetail,
                onClickAchievement = onClickChallengeAchieveBtn,
                onClickTooltipBtn = onClickTooltipBtn
            )
        } else {
            ProgressChallengeEmptyRoutine()
        }

        if (memberDailyRoutineList.isNotEmpty()) {
            ProgressDailyRoutine(
                memberDailyRoutineList = memberDailyRoutineList,
                onClickRoutineDetail = onClickRoutineDetail,
                onClickDailyAchieve = onClickDailyAchieve,
                onClickTooltipBtn = onClickTooltipBtn
            )
        } else {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 64.dp)
            ) {
                EmptyRoutineScreen(
                    titleContent = ProgressDailyEmptyTitle,
                    titleColor = Gray500,
                    titleStyle = SoftieTypo.body2,
                    btnBackgroundColor = Gray200,
                    btnCornerShape = 100,
                    btnBorderColor = Gray400,
                    btnTextContent = ProgressEmptyAddTitle,
                    btnTextColor = Gray500,
                    btnTextStyle = SoftieTypo.caption1,
                    btnVerticalPadding = 8,
                    btnHorizontalPadding = 12
                )
            }
        }
    }
}

@Composable
fun ProgressChallenge(
    memberChallenge: MemberChallengeModel = MemberChallengeModel(),
    onClickRoutineDetail: (RoutineDetailModel) -> Unit,
    onClickAchievement: () -> Unit = {},
    onClickTooltipBtn: () -> Unit
) {
    Column {
        RoutineTitleContent(
            title = ProgressChallengeTitle,
            onClickTooltipBtn = onClickTooltipBtn
        )

        Box(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 20.dp)
        ) {
            ChallengeRoutineBox(
                challengeModel = memberChallenge,
                onClickDetailAction = {
                    onClickRoutineDetail(
                        RoutineDetailModel(
                            routineId = memberChallenge.memberChallengeId,
                            routineType = RoutineType.Challenge,
                            content = memberChallenge.content,
                            explainDetail = memberChallenge.description,
                            time = memberChallenge.timeTaken,
                            place = memberChallenge.place
                        )
                    )
                },
                onClickAchievement = onClickAchievement
            )
        }

        Divider(color = Gray200, thickness = 2.dp)
    }
}

@Composable
fun ProgressDailyRoutine(
    memberDailyRoutineList: List<MemberDailyRoutineListModel> = emptyList(),
    onClickRoutineDetail: (RoutineDetailModel) -> Unit,
    onClickDailyAchieve: (Int) -> Unit,
    onClickTooltipBtn: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        RoutineTitleContent(
            title = ProgressDailyTitle,
            onClickTooltipBtn = onClickTooltipBtn
        )

        LazyColumn(
            modifier = Modifier
                .padding(top = 12.dp)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(
                memberDailyRoutineList,
                key = { index, themeItem -> themeItem.themeId }) { index, themeItem ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = ThemeIconType.getThemeIcon(themeItem.themeId)),
                        contentDescription = "theme icon",
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = themeItem.themeName,
                        color = Gray500,
                        style = SoftieTypo.body2
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    themeItem.routines.forEach { routineItem ->
                        MemberDailyRoutineListItem(
                            isRoutineAchieve = routineItem.isAchieve,
                            routineContent = routineItem.content,
                            onClickDetailAction = {
                                onClickRoutineDetail(
                                    RoutineDetailModel(
                                        routineId = routineItem.routineId,
                                        routineType = RoutineType.Daily,
                                        content = routineItem.content
                                    )
                                )
                            },
                            onClickDailyAchieve = { onClickDailyAchieve(routineItem.routineId) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProgressChallengeEmptyRoutine() {
    Column(
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = ProgressChallengeEmptyTitle,
            color = Gray500,
            style = SoftieTypo.body2
        )

        Spacer(modifier = Modifier.height(8.dp))

        RoundCornerShapeBtn(
            backgroundColor = Gray200,
            cornerShape = 100,
            borderColor = Gray400,
            textContent = ProgressEmptyAddTitle,
            textColor = Gray500,
            textStyle = SoftieTypo.caption1,
            verticalPadding = 8,
            horizontalPadding = 12
        )

        Image(
            painter = painterResource(id = R.drawable.ic_challenge_empty),
            contentDescription = "empty challenge",
            modifier = Modifier
                .padding(top = 21.dp)
                .size(width = 78.dp, height = 56.dp)
        )

        Divider(color = Gray200, thickness = 2.dp)
    }
}

@Composable
fun RoutineTitleContent(
    title: String,
    onClickTooltipBtn: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, end = 11.dp)
    ) {
        Text(
            text = title,
            color = Gray700,
            style = SoftieTypo.head4,
            modifier = Modifier
                .padding(start = 20.dp)
                .padding(vertical = 9.dp)
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(9.dp)
        ) {

            Box(
                modifier = Modifier
                .align(Alignment.CenterEnd)
//                .padding(9.dp)
                    .size(20.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Gray200)
                    .clickable(
                        onClick = onClickTooltipBtn
                    )
            ) {

                Text(
                    text = Question,
                    color = Gray500,
                    style = SoftieTypo.caption2,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProgress() {
    ProgressContent()
}