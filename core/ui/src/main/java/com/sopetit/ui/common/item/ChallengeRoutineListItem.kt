package com.sopetit.ui.common.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Detail
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.R
import com.sopetit.design_system.SoftieTypo

@Composable
fun ChallengeRoutineListItem(
    onClickAction: () -> Unit = {},
    routineContent: String,
    isRoutineSelected: Boolean = false,
    onClickDetail: () -> Unit
) {
    ChallengeRoutineListItemContent(
        onClickAction = onClickAction,
        routineContent = routineContent,
        isRoutineSelected = isRoutineSelected,
        onClickDetail = onClickDetail
    )
}

@Composable
fun ChallengeRoutineListItemContent(
    onClickAction: () -> Unit = {},
    routineContent: String = "",
    isRoutineSelected: Boolean = false,
    onClickDetail: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = Gray200, RoundedCornerShape(10.dp))
            .background(Gray0)
            .clickable { onClickAction() }
    ) {
        Text(
            text = routineContent,
            color = Gray700,
            style = SoftieTypo.body2,
            modifier = Modifier
                .padding(20.dp)
        )

        Divider(modifier = Modifier
            .border(1.dp, color = Gray200)
            .fillMaxWidth())

        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .padding(start = 20.dp)
                    .align(Alignment.CenterStart)
                    .clip(RoundedCornerShape(100.dp))
                    .background(Gray200)
                    .clickable(
                        onClick = onClickDetail
                    )
            ) {
                Text(
                    text = Detail,
                    color = Gray400,
                    style = SoftieTypo.caption1,
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .padding(start = 8.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_detail),
                    contentDescription = "detail",
                    modifier = Modifier
                        .padding(vertical = 7.dp)
                        .padding(start = 2.dp, end = 8.dp)
                        .size(16.dp)
                )
            }

            Image(
                painter = painterResource(id = if (isRoutineSelected) R.drawable.ic_check_on else R.drawable.ic_check_off),
                contentDescription = "check icon",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(5.dp)
                    .padding(end = 7.dp)
                    .size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChallengeRoutineListItem() {
    ChallengeRoutineListItemContent()
}