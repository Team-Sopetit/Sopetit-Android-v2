package com.sopetit.ui.common.bottomsheet

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
import com.sopetit.designsystem.Confirm
import com.sopetit.designsystem.Delete
import com.sopetit.designsystem.Gray0
import com.sopetit.designsystem.Gray200
import com.sopetit.designsystem.Gray300
import com.sopetit.designsystem.Gray500
import com.sopetit.designsystem.Gray650
import com.sopetit.designsystem.Gray700
import com.sopetit.design_system.R
import com.sopetit.designsystem.Red200
import com.sopetit.designsystem.SoftieTypo
import com.sopetit.domain.entity.enums.RoutineType
import com.sopetit.domain.entity.response.screen.RoutineDetailModel

@Composable
fun RoutineDetailBottomSheet(
    routine: RoutineDetailModel,
    onClickRoutineDeleteBtn: (RoutineDetailModel) -> Unit = {},
    onClickConfirmBtn: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }

    RoutineDetailContent(
        routine = routine,
        onClickDeleteBtnAction = onClickRoutineDeleteBtn,
        interactionSource = interactionSource,
        isJustDetailView = routine.isJustDetailView,
        onClickConfirmBtn = onClickConfirmBtn,
    )
}

@Composable
fun RoutineDetailContent(
    routine: RoutineDetailModel = RoutineDetailModel(),
    onClickDeleteBtnAction: (RoutineDetailModel) -> Unit = {},
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    isJustDetailView: Boolean = false,
    onClickConfirmBtn: () -> Unit = {},
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(Gray0)
                .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = routine.routineType.typeName,
            color = Gray700,
            style = SoftieTypo.head4,
            modifier =
                Modifier
                    .padding(top = 24.dp),
        )

        RoutineDetailTitleBox(
            content = routine.content,
        )

        if (routine.routineType == RoutineType.Challenge && routine.explainDetail.isNotEmpty()) {
            ChallengeRoutineDetail(
                explainDetail = routine.explainDetail,
                time = routine.time,
                place = routine.place,
            )
        }

        RoutineDeleteBtn(
            interactionSource = interactionSource,
            onClickBtnAction = { onClickDeleteBtnAction(routine) },
            isJustDetailView = isJustDetailView,
            onClickConfirmBtn = onClickConfirmBtn,
        )
    }
}

@Composable
fun RoutineDetailTitleBox(
    content: String,
) {
    Box(
        modifier =
            Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Gray200)
                .border(width = 1.dp, color = Gray300, shape = RoundedCornerShape(10.dp)),
    ) {
        Text(
            text = content,
            color = Gray700,
            style = SoftieTypo.body1,
            modifier =
                Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 20.dp),
        )
    }
}

@Composable
fun ChallengeRoutineDetail(
    explainDetail: String,
    time: String,
    place: String,
) {
    Column {
        Text(
            text = explainDetail,
            color = Gray500,
            style = SoftieTypo.body2,
        )

        Row(
            modifier = Modifier.padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_routine_time),
                contentDescription = "routine time",
                modifier =
                    Modifier
                        .size(18.dp),
            )

            Text(
                text = time,
                color = Gray500,
                style = SoftieTypo.caption1,
                modifier = Modifier.padding(start = 6.dp),
            )
        }

        Row(
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_routine_place),
                contentDescription = "routine place",
                modifier =
                    Modifier
                        .size(18.dp),
            )

            Text(
                text = place,
                color = Gray500,
                style = SoftieTypo.caption1,
                modifier = Modifier.padding(start = 6.dp),
            )
        }
    }
}

@Composable
fun RoutineDeleteBtn(
    onClickBtnAction: () -> Unit = {},
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    isJustDetailView: Boolean = false,
    onClickConfirmBtn: () -> Unit,
) {
    Box(
        modifier =
            Modifier
                .padding(vertical = 32.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(if (isJustDetailView) Gray650 else Red200)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        if (isJustDetailView) onClickConfirmBtn() else onClickBtnAction()
                    },
                ),
    ) {
        Row(
            modifier =
                Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (!isJustDetailView) {
                Image(
                    painter = painterResource(id = R.drawable.ic_trash),
                    contentDescription = "routine delete",
                    modifier =
                        Modifier
                            .size(18.dp),
                )
            }

            Text(
                text = if (isJustDetailView) Confirm else Delete,
                color = Gray0,
                style = SoftieTypo.body1,
                modifier = Modifier.padding(start = 4.dp),
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRoutineDetail() {
    RoutineDetailContent()
}
