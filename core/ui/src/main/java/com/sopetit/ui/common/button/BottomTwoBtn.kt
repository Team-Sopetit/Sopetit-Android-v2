package com.sopetit.ui.common.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sopetit.designsystem.SoftieTypo

@Composable
fun BottomTwoBtn(
    onClickLeftBtn: () -> Unit,
    onClickRightBtn: () -> Unit,
    leftIcon: Int = 0,
    rightIcon: Int = 0,
    iconVisible: Boolean = false,
    leftColor: Color,
    rightColor: Color,
    rightBtnColorBrush: Brush? = null,
    leftContent: String,
    rightContent: String,
    leftBtnTextColor: Color,
    rightBtnTextColor: Color,
    twoBtnHorizontalPadding: Int,
    twoBtnBottomPadding: Int,
) {
    val interactionSource = remember { MutableInteractionSource() }

    BottomTwoBtnContent(
        interactionSource = interactionSource,
        onClickLeftBtn = onClickLeftBtn,
        onClickRightBtn = onClickRightBtn,
        leftIcon = leftIcon,
        rightIcon = rightIcon,
        leftColor = leftColor,
        rightColor = rightColor,
        rightBtnColorBrush = rightBtnColorBrush,
        leftContent = leftContent,
        rightContent = rightContent,
        leftBtnTextColor = leftBtnTextColor,
        rightBtnTextColor = rightBtnTextColor,
        iconVisible = iconVisible,
        twoBtnBottomPadding = twoBtnBottomPadding,
        twoBtnHorizontalPadding = twoBtnHorizontalPadding,
    )
}

@Composable
fun BottomTwoBtnContent(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClickLeftBtn: () -> Unit,
    onClickRightBtn: () -> Unit,
    leftIcon: Int = 0,
    rightIcon: Int = 0,
    iconVisible: Boolean = false,
    leftColor: Color,
    rightColor: Color,
    rightBtnColorBrush: Brush? = null,
    leftContent: String,
    rightContent: String,
    leftBtnTextColor: Color,
    rightBtnTextColor: Color,
    twoBtnHorizontalPadding: Int,
    twoBtnBottomPadding: Int,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = twoBtnHorizontalPadding.dp),
    ) {
        TwoBtnBox(
            btnColor = leftColor,
            btnColorBrush = null,
            icon = leftIcon,
            btnText = leftContent,
            modifier = Modifier.weight(1f),
            interactionSource = interactionSource,
            onClickBtnAction = onClickLeftBtn,
            btnTextColor = leftBtnTextColor,
            iconVisible = iconVisible,
            twoBtnBottomPadding = twoBtnBottomPadding,
        )

        Spacer(modifier = Modifier.width(7.dp))

        TwoBtnBox(
            btnColor = rightColor,
            btnColorBrush = rightBtnColorBrush,
            icon = rightIcon,
            btnText = rightContent,
            modifier = Modifier.weight(1f),
            interactionSource = interactionSource,
            onClickBtnAction = onClickRightBtn,
            btnTextColor = rightBtnTextColor,
            iconVisible = iconVisible,
            twoBtnBottomPadding = twoBtnBottomPadding,
        )
    }
}

@Composable
fun TwoBtnBox(
    onClickBtnAction: () -> Unit = {},
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    btnColor: Color,
    btnColorBrush: Brush? = null,
    icon: Int,
    btnText: String,
    modifier: Modifier,
    btnTextColor: Color,
    iconVisible: Boolean = false,
    twoBtnBottomPadding: Int,
) {
    Box(
        modifier =
            modifier
                .padding(bottom = twoBtnBottomPadding.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(btnColorBrush ?: SolidColor(btnColor))
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClickBtnAction,
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
            if (iconVisible) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "btn icon",
                    modifier =
                        Modifier
                            .padding(end = 4.dp)
                            .size(18.dp),
                )
            }

            Text(
                text = btnText,
                color = btnTextColor,
                style = SoftieTypo.body1,
            )
        }
    }
}
