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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Delete
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.Modify
import com.sopetit.design_system.R
import com.sopetit.design_system.Red200
import com.sopetit.design_system.SoftieTypo

@Composable
fun BottomTwoBtn(
    onClickModBtn: () -> Unit,
    onClickDeleteBtn: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    BottomTwoBtnContent(
        interactionSource = interactionSource,
        onClickModBtn = onClickModBtn,
        onClickDeleteBtn = onClickDeleteBtn
    )
}

@Composable
fun BottomTwoBtnContent(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClickModBtn: () -> Unit,
    onClickDeleteBtn: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        TwoBtnBox(
            btnColor = Gray650,
            icon = R.drawable.ic_pen,
            btnText = Modify,
            modifier = Modifier.weight(1f),
            interactionSource = interactionSource,
            onClickBtnAction = onClickModBtn
        )

        Spacer(modifier = Modifier.width(7.dp))

        TwoBtnBox(
            btnColor = Red200,
            icon = R.drawable.ic_trash,
            btnText = Delete,
            modifier = Modifier.weight(1f),
            interactionSource = interactionSource,
            onClickBtnAction = onClickDeleteBtn
        )
    }
}

@Composable
fun TwoBtnBox(
    onClickBtnAction: () -> Unit = {},
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    btnColor: Color,
    icon: Int,
    btnText: String,
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(btnColor)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClickBtnAction
            ),
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "btn icon",
                modifier = Modifier
                    .size(18.dp)
            )

            Text(
                text = btnText,
                color = Gray0,
                style = SoftieTypo.body1,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}