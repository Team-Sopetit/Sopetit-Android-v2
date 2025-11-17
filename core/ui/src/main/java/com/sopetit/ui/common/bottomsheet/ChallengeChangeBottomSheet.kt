package com.sopetit.ui.common.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.sopetit.designsystem.ChallengeChangeBottomSheetSemiTitle
import com.sopetit.designsystem.ChallengeChangeBottomSheetTitle
import com.sopetit.designsystem.ChangeRoutine
import com.sopetit.designsystem.Gray0
import com.sopetit.designsystem.Gray650
import com.sopetit.designsystem.Gray700
import com.sopetit.designsystem.R
import com.sopetit.designsystem.Red200
import com.sopetit.designsystem.SelectedRoutineBottomSheetTitle
import com.sopetit.designsystem.SoftieTypo
import com.sopetit.domain.entity.response.memberchallenge.MemberChallengeModel
import com.sopetit.domain.entity.response.routine.ChallengeChangeModel
import com.sopetit.ui.common.button.BottomRectangleBtn
import com.sopetit.ui.common.content.ChallengeRoutineBox

@Composable
fun ChallengeChangeBottomSheet(
    challengeChangeModel: ChallengeChangeModel,
) {
    val interactionSource = remember { MutableInteractionSource() }

    ChallengeChangeContent(
        hasChallenge = challengeChangeModel.hasChallenge,
        changeChallenge = challengeChangeModel.changeChallenge,
    )
}

@Composable
fun ChallengeChangeContent(
    hasChallenge: MemberChallengeModel = MemberChallengeModel(),
    changeChallenge: MemberChallengeModel = MemberChallengeModel(),
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
            text = ChallengeChangeBottomSheetTitle,
            color = Gray700,
            style = SoftieTypo.head3,
            modifier =
                Modifier
                    .padding(top = 24.dp),
        )

        Text(
            text = ChallengeChangeBottomSheetSemiTitle,
            color = Red200,
            style = SoftieTypo.body2,
            modifier =
                Modifier
                    .padding(top = 4.dp),
        )

        Spacer(modifier = Modifier.height(32.dp))

        ChallengeContent(
            hasChallenge = hasChallenge,
            changeChallenge = changeChallenge,
        )

        Spacer(modifier = Modifier.height(32.dp))

        BottomRectangleBtn(
            btnTextContent = ChangeRoutine,
            isBtnActivated = true,
            onClickAction = {},
        )
    }
}

@Composable
fun ChallengeContent(
    hasChallenge: MemberChallengeModel = MemberChallengeModel(),
    changeChallenge: MemberChallengeModel = MemberChallengeModel(),
) {
    ChallengeRoutineBox(
        challengeModel = hasChallenge,
        isUsedForChange = true,
    )

    Box(
        modifier = Modifier,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_down),
            contentDescription = "arrow down",
            modifier =
                Modifier
                    .align(Alignment.TopCenter)
                    .padding(vertical = 2.dp)
                    .size(18.dp),
        )

        Box(
            modifier =
                Modifier
                    .padding(top = 22.dp),
        ) {
            ChallengeRoutineBox(
                challengeModel = changeChallenge,
                isUsedForChange = true,
            )
        }

        Box(
            modifier =
                Modifier
                    .align(Alignment.TopEnd)
                    .clip(RoundedCornerShape(99.dp))
                    .background(Gray650),
        ) {
            Text(
                text = SelectedRoutineBottomSheetTitle,
                color = Gray0,
                style = SoftieTypo.caption2,
                modifier =
                    Modifier
                        .padding(vertical = 6.dp, horizontal = 8.dp),
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChallengeChange() {
    ChallengeChangeContent()
}
