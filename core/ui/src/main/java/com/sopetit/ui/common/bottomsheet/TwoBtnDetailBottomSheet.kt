package com.sopetit.ui.common.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import com.sopetit.design_system.DailyRoutine
import com.sopetit.design_system.Delete
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray500
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.MemoTitle
import com.sopetit.design_system.Modify
import com.sopetit.design_system.R
import com.sopetit.design_system.Red200
import com.sopetit.design_system.SoftieTypo
import com.sopetit.domain.entity.enums.BottomSheetActionType
import com.sopetit.domain.entity.response.memo.MemoActionModel
import com.sopetit.domain.entity.response.screen.RoutineDetailModel
import com.sopetit.ui.common.button.BottomTwoBtn
import com.sopetit.ui.common.type.TwoBtnBottomSheetType
import com.sopetit.ui.util.convertToAmPmFormat

@Composable
fun TwoBtnDetailBottomSheet(
    onClickMemoDeleteBtn: (MemoActionModel) -> Unit,
    onClickRoutineDeleteBtn: (RoutineDetailModel) -> Unit,
    onClickModBtn: () -> Unit,
    memoActionModel: MemoActionModel = MemoActionModel(),
    type: TwoBtnBottomSheetType,
    routine: RoutineDetailModel = RoutineDetailModel(),
) {
    val interactionSource = remember { MutableInteractionSource() }

    TwoBtnDetailContent(
        onClickModBtn = { onClickModBtn() },
        onClickMemoDeleteBtn = { onClickMemoDeleteBtn(memoActionModel.copy(type = BottomSheetActionType.Delete)) },
        onClickRoutineDeleteBtn = { onClickRoutineDeleteBtn(routine) },
        memoActionModel = memoActionModel,
        type = type,
        routine = routine
    )
}

@Composable
fun TwoBtnDetailContent(
    onClickMemoDeleteBtn: () -> Unit = {},
    onClickRoutineDeleteBtn: () -> Unit = {},
    onClickModBtn: () -> Unit = {},
    memoActionModel: MemoActionModel = MemoActionModel(),
    type: TwoBtnBottomSheetType = TwoBtnBottomSheetType.MemoWrite,
    routine: RoutineDetailModel = RoutineDetailModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray0),
    ) {
        Text(
            text = if (type == TwoBtnBottomSheetType.MemoWrite) MemoTitle else DailyRoutine,
            color = Gray700,
            style = SoftieTypo.head4,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp)
        )

        Box(
            modifier = Modifier
                .padding(top = 16.dp, start = 20.dp, end = 20.dp)
                .padding(bottom = if (routine.alarmTime.isNotEmpty()) 0.dp else 32.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Gray200)
        ) {
            Text(
                text = if (type == TwoBtnBottomSheetType.MemoWrite) memoActionModel.content else routine.content,
                color = Gray700,
                style = SoftieTypo.body2,
                modifier = Modifier
                    .padding(horizontal = 27.dp, vertical = 20.dp)
            )
        }

        if (routine.alarmTime.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, top = 12.dp, bottom = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_routine_time),
                    contentDescription = "alarm",
                    modifier = Modifier
                        .padding(end = 2.dp)
                        .size(14.dp)
                )

                Text(
                    text = convertToAmPmFormat(routine.alarmTime),
                    color = Gray500,
                    style = SoftieTypo.caption1,
                    modifier = Modifier
                )
            }
        }

        BottomTwoBtn(
            onClickLeftBtn = onClickModBtn,
            onClickRightBtn = { if (type == TwoBtnBottomSheetType.MemoWrite) onClickMemoDeleteBtn() else onClickRoutineDeleteBtn() },
            leftIcon = R.drawable.ic_pen,
            rightIcon = R.drawable.ic_trash,
            leftColor = Gray650,
            rightColor = Red200,
            iconVisible = true,
            leftContent = Modify,
            rightContent = Delete,
            leftBtnTextColor = Gray0,
            rightBtnTextColor = Gray0,
            twoBtnBottomPadding = 32,
            twoBtnHorizontalPadding = 20
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTwoBtnDetail() {
    TwoBtnDetailContent()
}