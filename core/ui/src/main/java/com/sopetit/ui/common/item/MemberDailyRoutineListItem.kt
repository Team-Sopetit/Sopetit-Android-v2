package com.sopetit.ui.common.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.designsystem.Gray0
import com.sopetit.designsystem.Gray200
import com.sopetit.designsystem.Gray500
import com.sopetit.designsystem.Gray700
import com.sopetit.designsystem.R
import com.sopetit.designsystem.SoftieTypo
import com.sopetit.ui.util.convertToAmPmFormat

@Composable
fun MemberDailyRoutineListItem(
    isRoutineAchieve: Boolean,
    routineContent: String,
    onClickDetailAction: () -> Unit,
    onClickDailyAchieve: () -> Unit,
    alarmTime: String = "",
) {
    val interactionSource = remember { MutableInteractionSource() }

    MemberDailyRoutineListItemContent(
        isRoutineAchieve = isRoutineAchieve,
        routineContent = routineContent,
        onClickRoutineDetail = { onClickDetailAction() },
        interactionSource = interactionSource,
        onClickDailyAchieve = { onClickDailyAchieve() },
        alarmTime = alarmTime,
    )
}

@Composable
fun MemberDailyRoutineListItemContent(
    isRoutineAchieve: Boolean = false,
    routineContent: String = "",
    onClickRoutineDetail: () -> Unit = {},
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClickDailyAchieve: () -> Unit = {},
    alarmTime: String = "",
) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(10.dp))
                .border(width = 1.dp, color = Gray200, RoundedCornerShape(10.dp))
                .background(Gray0),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp, end = 71.dp)
                    .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = if (isRoutineAchieve) R.drawable.ic_check_on else R.drawable.ic_check_off_routine),
                contentDescription = "check icon",
                modifier =
                    Modifier
                        .padding(9.dp)
                        .size(20.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = { onClickDailyAchieve() },
                        ),
            )

            Column(
                modifier =
                    Modifier
                        .weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = routineContent,
                    color = Gray700,
                    style = SoftieTypo.body2,
                    modifier =
                        Modifier
                            .padding(start = 2.dp),
                )

                if (alarmTime.isNotEmpty()) {
                    Row(
                        modifier =
                            Modifier
                                .padding(start = 2.dp, top = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_routine_time),
                            contentDescription = "alarm",
                            modifier =
                                Modifier
                                    .padding(end = 2.dp)
                                    .size(14.dp),
                        )

                        Text(
                            text = convertToAmPmFormat(alarmTime),
                            color = Gray500,
                            style = SoftieTypo.caption1,
                            modifier = Modifier,
                        )
                    }
                }
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_more_info),
            contentDescription = "more info",
            modifier =
                Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 8.dp)
                    .size(24.dp)
                    .padding(vertical = 10.dp, horizontal = 5.dp)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource,
                        onClick = { onClickRoutineDetail() },
                    ),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMemberDailyRoutineListItem() {
    MemberDailyRoutineListItemContent()
}
