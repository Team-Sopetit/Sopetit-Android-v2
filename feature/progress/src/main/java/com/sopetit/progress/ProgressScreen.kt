package com.sopetit.progress

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray500
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.ProgressChallengeAddTitle
import com.sopetit.design_system.ProgressChallengeEmptyTitle
import com.sopetit.design_system.ProgressDailyTitle
import com.sopetit.design_system.ProgressTitleDate
import com.sopetit.design_system.Question
import com.sopetit.design_system.R
import com.sopetit.design_system.SoftieTypo
import org.threeten.bp.LocalDate

@Composable
fun ProgressScreen() {

    val viewModel: ProgressViewModel = hiltViewModel()
    val uiState: ProgressPageState by viewModel.uiState.collectAsStateWithLifecycle()

    val today = LocalDate.now()

    ProgressContent(
        todayYear = today.year,
        todayMonth = today.monthValue,
        todayDay = today.dayOfMonth
    )
}

@Composable
fun ProgressContent(
    todayYear: Int = 0,
    todayMonth: Int = 0,
    todayDay: Int = 0,
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
            ProgressRoutineContent()
        }
    }
}

@Composable
fun ProgressRoutineContent() {
    ProgressChallengeEmptyRoutine()

    ProgressDailyRoutine()
}

@Composable
fun ProgressDailyRoutine() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        RoutineTitleContent(title = ProgressDailyTitle)

        Column(
            modifier = Modifier
                .padding(top = 12.dp)
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "산뜻한 일상",
                color = Gray500,
                style = SoftieTypo.body2
            )
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

        RoutineAddBtnContent()

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
                .size(20.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Gray200)
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

@Composable
fun RoutineAddBtnContent() {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(100.dp))
            .background(Gray200)
            .border(1.dp, color = Gray400, RoundedCornerShape(100.dp))
    ) {
        Text(
            text = ProgressChallengeAddTitle,
            color = Gray500,
            style = SoftieTypo.caption1,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 12.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProgress() {
    ProgressContent()
}