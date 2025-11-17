package com.sopetit.achieve.routine

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.designsystem.AchieveCountContent
import com.sopetit.designsystem.AchieveEmptyAchieve
import com.sopetit.designsystem.AchieveRoutineStartedAt
import com.sopetit.designsystem.ChallengeRoutine
import com.sopetit.designsystem.CountContent
import com.sopetit.designsystem.DailyRoutine
import com.sopetit.designsystem.DailyTitle
import com.sopetit.designsystem.Gray0
import com.sopetit.designsystem.Gray200
import com.sopetit.designsystem.Gray300
import com.sopetit.designsystem.Gray50
import com.sopetit.designsystem.Gray500
import com.sopetit.designsystem.Gray650
import com.sopetit.designsystem.Gray700
import com.sopetit.designsystem.ProgressEmptyAddTitle
import com.sopetit.designsystem.ProgressEmptyTitle
import com.sopetit.designsystem.R
import com.sopetit.designsystem.SoftieTypo
import com.sopetit.designsystem.StatAchieveRoutineTitle
import com.sopetit.domain.entity.response.achieve.AchieveRoutineItem
import com.sopetit.domain.entity.response.achieve.AchieveRoutineModel
import com.sopetit.ui.common.content.EmptyRoutineScreen
import com.sopetit.ui.common.topbar.TopBarContent
import com.sopetit.ui.common.type.ThemeIconType
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun AchieveRoutineScreen(
    achieveThemeId: SharedFlow<Int>,
    goToAddRoutinePage: () -> Unit,
    goBackToAchievePage: () -> Unit,
) {
    val viewModel: AchieveRoutineViewModel = hiltViewModel()
    val uiState: AchieveRoutinePageState by viewModel.uiState.collectAsStateWithLifecycle()

    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(achieveThemeId) {
        achieveThemeId.collect {
            viewModel.getAchieveThemeRoutine(it)
        }
    }

    AchieveRoutineContent(
        achieveRoutine = uiState.achieveRoutine,
        achieveThemeId = uiState.achieveThemeId,
        onClickAddRoutine = { goToAddRoutinePage() },
        onClickBackBtn = { goBackToAchievePage() },
        interactionSource = interactionSource,
    )
}

@Composable
fun AchieveRoutineContent(
    achieveRoutine: AchieveRoutineModel = AchieveRoutineModel(),
    achieveThemeId: Int = 0,
    onClickAddRoutine: () -> Unit = {},
    onClickBackBtn: () -> Unit = {},
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Gray50)
                .verticalScroll(rememberScrollState()),
    ) {
        TopBarContent(
            content = StatAchieveRoutineTitle,
            onClickIcon = onClickBackBtn,
            interactionSource = interactionSource,
        )

        Divider(modifier = Modifier.border(2.dp, Gray200))

        if ((achieveRoutine.routineTotalCount + achieveRoutine.challengeTotalCount) == 0) {
            AddRoutineEmptyBox(
                onClickAddRoutine = onClickAddRoutine,
            )
        } else {
            AddRoutineBox(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                achieveRoutine = achieveRoutine,
                achieveThemeId = achieveThemeId,
            )
        }
    }
}

@Composable
fun AddRoutineBox(
    achieveRoutine: AchieveRoutineModel = AchieveRoutineModel(),
    achieveThemeId: Int = 0,
    modifier: Modifier,
) {
    AchieveThemeBox(
        achieveThemeId = achieveThemeId,
        achieveRoutine = achieveRoutine,
        modifier = modifier,
    )

    AchieveChallengeRoutine(
        achieveThemeId = achieveThemeId,
        achieveRoutine = achieveRoutine,
        challengeRoutine = achieveRoutine.challenges,
        modifier =
            Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
    )

    AchieveDailyRoutine(
        achieveRoutine = achieveRoutine,
        dailyRoutine = achieveRoutine.routines,
        modifier =
            Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
    )
}

@Composable
fun AddRoutineEmptyBox(
    onClickAddRoutine: () -> Unit,
) {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(top = 148.dp),
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
            btnHorizontalPadding = 16,
            onClickAddRoutine = onClickAddRoutine,
        )
    }
}

@Composable
fun AddRoutineEachEmptyBox(
    emptyTitle: String,
    modifier: Modifier,
) {
    Column(
        modifier =
            modifier
                .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_snackbar_caution),
            contentDescription = "notice empty",
            colorFilter = ColorFilter.tint(Gray300),
        )

        Text(
            text = String.format(AchieveEmptyAchieve, emptyTitle),
            style = SoftieTypo.body2,
            color = Gray500,
            modifier =
                Modifier
                    .padding(top = 8.dp),
        )
    }
}

@Composable
fun AchieveThemeBox(
    achieveThemeId: Int,
    achieveRoutine: AchieveRoutineModel,
    modifier: Modifier,
) {
    Image(
        painter = painterResource(id = ThemeIconType.getThemeIcon(achieveThemeId)),
        contentDescription = "theme",
        modifier =
            modifier
                .padding(top = 20.dp, bottom = 10.dp)
                .size(40.dp),
    )

    Text(
        text = achieveRoutine.name,
        style = SoftieTypo.body2,
        color = ThemeIconType.getThemeGraphColor(achieveThemeId),
        modifier = modifier,
    )

    Text(
        text =
            String.format(
                CountContent,
                achieveRoutine.routineTotalCount + achieveRoutine.challengeTotalCount,
            ),
        style = SoftieTypo.head1,
        color = Gray700,
        modifier =
            modifier
                .padding(top = 10.dp, bottom = 20.dp),
    )
}

@Composable
fun AchieveChallengeRoutine(
    achieveRoutine: AchieveRoutineModel,
    achieveThemeId: Int,
    challengeRoutine: List<AchieveRoutineItem>,
    modifier: Modifier,
) {
    Row(
        modifier = modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = ChallengeRoutine,
            style = SoftieTypo.head3,
            color = Gray700,
        )

        Text(
            text = String.format(CountContent, achieveRoutine.challengeTotalCount),
            style = SoftieTypo.body2,
            color = Gray500,
            modifier =
                Modifier
                    .padding(start = 4.dp),
        )
    }

    if (achieveRoutine.challengeTotalCount == 0) {
        AddRoutineEachEmptyBox(
            emptyTitle = ChallengeRoutine,
            modifier = modifier,
        )
    } else {
        challengeRoutine.forEach { challenge ->
            AchieveRoutineItemBox(
                routine = challenge,
                color = ThemeIconType.getThemeColor(achieveThemeId),
            )
        }
    }
}

@Composable
fun AchieveDailyRoutine(
    achieveRoutine: AchieveRoutineModel,
    dailyRoutine: List<AchieveRoutineItem>,
    modifier: Modifier,
) {
    Spacer(modifier = Modifier.padding(top = 12.dp))

    Row(
        modifier = modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = DailyRoutine,
            style = SoftieTypo.head3,
            color = Gray700,
        )

        Text(
            text = String.format(CountContent, achieveRoutine.challengeTotalCount),
            style = SoftieTypo.body2,
            color = Gray500,
            modifier =
                Modifier
                    .padding(start = 4.dp),
        )
    }

    if (achieveRoutine.routineTotalCount == 0) {
        AddRoutineEachEmptyBox(
            emptyTitle = DailyTitle,
            modifier = modifier,
        )
    } else {
        dailyRoutine.forEach { daily ->
            AchieveRoutineItemBox(
                routine = daily,
                color = Gray0,
            )
        }
    }

    Spacer(modifier = Modifier.padding(top = 40.dp))
}

@Composable
fun AchieveRoutineItemBox(
    routine: AchieveRoutineItem,
    color: Color,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 2.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color),
    ) {
        Text(
            text = routine.content,
            style = SoftieTypo.body2,
            color = Gray700,
            modifier =
                Modifier
                    .padding(top = 16.dp, bottom = 10.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
        )

        Divider(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp)
                    .border(1.dp, Gray300),
        )

        Row(
            modifier =
                Modifier
                    .padding(start = 16.dp, top = 10.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_routine_time),
                contentDescription = "routine time",
                colorFilter = ColorFilter.tint(Gray650),
            )

            Text(
                text = String.format(AchieveCountContent, routine.achievedCount),
                style = SoftieTypo.body2,
                color = Gray700,
                modifier =
                    Modifier
                        .padding(start = 6.dp)
                        .fillMaxWidth(),
            )
        }

        Text(
            text = String.format(AchieveRoutineStartedAt, routine.startedAt),
            style = SoftieTypo.body2,
            color = Gray500,
            modifier =
                Modifier
                    .padding(bottom = 16.dp, start = 40.dp)
                    .fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAchieveRoutine() {
    AchieveRoutineContent()
}
