package com.sopetit.ui.common.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import com.sopetit.design_system.Complete
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray500
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.R
import com.sopetit.design_system.SoftieTypo
import com.sopetit.domain.entity.response.memberchallenge.MemberChallengeModel
import com.sopetit.ui.common.type.ThemeIconType

@Composable
fun ChallengeRoutineBox(
    challengeModel: MemberChallengeModel,
    onClickDetailAction: () -> Unit,
    onClickAchievement: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    ChallengeRoutineContent(
        challengeModel = challengeModel,
        onClickRoutineDetail = { onClickDetailAction() },
        interactionSource = interactionSource,
        onClickAchievement = onClickAchievement
    )
}

@Composable
fun ChallengeRoutineContent(
    challengeModel: MemberChallengeModel = MemberChallengeModel(),
    onClickRoutineDetail: () -> Unit = {},
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClickAchievement: () -> Unit = {},
) {
    val themeType: ThemeIconType = ThemeIconType.mapThemeIconType(challengeModel.theme.themeId)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(10.dp))
            .background(themeType.themeColor)
            .border(width = 1.dp, color = Gray200, RoundedCornerShape(10.dp))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp),
            ) {
                Image(
                    painter = painterResource(id = themeType.themeIcon),
                    contentDescription = "theme icon",
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .align(Alignment.CenterStart)
                        .size(16.dp)
                )

                Text(
                    text = challengeModel.theme.themeName,
                    color = Gray500,
                    style = SoftieTypo.body2,
                    modifier = Modifier
                        .padding(start = 38.dp)
                        .align(Alignment.CenterStart)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_more_info),
                    contentDescription = "more info",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 20.dp)
                        .size(24.dp)
                        .padding(vertical = 10.dp, horizontal = 5.dp)
                        .clickable(
                            indication = null,
                            interactionSource = interactionSource,
                            onClick = { onClickRoutineDetail() }
                        )
                )
            }

            Text(
                text = challengeModel.content,
                color = Gray700,
                style = SoftieTypo.body2,
                modifier = Modifier
                    .padding(top = 6.dp, start = 20.dp)
            )

            Box(
                modifier = Modifier
                    .padding(start = 20.dp, top = 10.dp, bottom = 12.dp)
                    .wrapContentSize()
                    .clip(RoundedCornerShape(100.dp))
                    .background(Gray650)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { onClickAchievement() }
                    )
            ) {
                Text(
                    text = Complete,
                    color = Gray0,
                    style = SoftieTypo.caption1,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
                )
            }
        }

        Image(
            painter = painterResource(id = themeType.themeBackgroundImg),
            contentDescription = "theme background",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 12.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewChallengeRoutine() {
    ChallengeRoutineContent()
}