package com.sopetit.ui.common.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray300
import com.sopetit.design_system.Gray500
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.R
import com.sopetit.design_system.Red200
import com.sopetit.design_system.SoftieTypo

@Composable
fun RoutineDetailBottomSheet() {
    RoutineDetailContent()
}

@Composable
fun RoutineDetailContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray0)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "챌린지",
            color = Gray700,
            style = SoftieTypo.head4,
            modifier = Modifier
                .padding(top = 24.dp)
        )

        RoutineDetailTitleBox()

        ChallengeRoutineDetail()

        RoutineDeleteBtn()
    }
}

@Composable
fun RoutineDetailTitleBox() {
    Box(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Gray200)
            .border(width = 1.dp, color = Gray300, shape = RoundedCornerShape(10.dp))
    ) {
        Text(
            text = "일어나면 5분 안에 이불 개기",
            color = Gray700,
            style = SoftieTypo.body1,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 20.dp)
        )
    }
}

@Composable
fun ChallengeRoutineDetail() {
    Column {
        Text(
            text = "평소에 바빠서 연락하지 못한 사람이 있다면 안부인사 개인톡을 보내 봐. 꼭 만나서 밥을 먹거나 하지 않아도 연락 한 통이 나와 그 사람을 연결하는 방법이 될 수 있어",
            color = Gray500,
            style = SoftieTypo.body2,
        )

        Row(
            modifier = Modifier.padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_routine_time),
                contentDescription = "routine time",
                modifier = Modifier
                    .size(18.dp)
            )


            Text(
                text = "5~10분",
                color = Gray500,
                style = SoftieTypo.caption1,
                modifier = Modifier.padding(start = 6.dp)
            )
        }

        Row(
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_routine_place),
                contentDescription = "routine place",
                modifier = Modifier
                    .size(18.dp)
            )

            Text(
                text = "회사 옥상, 점심식사 후 돌아가는 길",
                color = Gray500,
                style = SoftieTypo.caption1,
                modifier = Modifier.padding(start = 6.dp)
            )
        }

    }
}

@Composable
fun RoutineDeleteBtn() {
    Box(
        modifier = Modifier
            .padding(vertical = 32.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Red200),
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_trash),
                contentDescription = "routine delete",
                modifier = Modifier
                    .size(18.dp)
            )

            Text(
                text = "삭제하기",
                color = Gray0,
                style = SoftieTypo.body1,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRoutineDetail() {
    RoutineDetailContent()
}