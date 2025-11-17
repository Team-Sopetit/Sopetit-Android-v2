package com.sopetit.ui.common.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray300
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.R
import com.sopetit.design_system.SoftieTypo
import com.sopetit.ui.common.button.BottomTwoBtn
import com.sopetit.ui.common.model.TwoBtnIconModel

@Composable
fun TwoBtnIconBottomSheet(
    twoBtnIconModel: TwoBtnIconModel,
    onClickLeftBtn: () -> Unit,
    onClickRightBtn: () -> Unit,
) {
    TwoBtnIconContent(
        title = twoBtnIconModel.title,
        semiTitle = twoBtnIconModel.semiTitle,
        leftBtnText = twoBtnIconModel.leftBtnText,
        rightBtnText = twoBtnIconModel.rightBtnText,
        leftBtnTextColor = twoBtnIconModel.leftBtnTextColor,
        rightBtnTextColor = twoBtnIconModel.rightBtnTextColor,
        leftBtnColor = twoBtnIconModel.leftBtnColor,
        rightBtnColor = twoBtnIconModel.rightBtnColor,
        onClickLeftBtn = onClickLeftBtn,
        onClickRightBtn = onClickRightBtn,
    )
}

@Composable
fun TwoBtnIconContent(
    title: String,
    semiTitle: String,
    leftBtnText: String,
    rightBtnText: String,
    leftBtnColor: Color,
    rightBtnColor: Color,
    leftBtnTextColor: Color,
    rightBtnTextColor: Color,
    onClickLeftBtn: () -> Unit,
    onClickRightBtn: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(Gray0),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_bear_face_crying),
            contentDescription = "crying",
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 42.dp)
                    .size(width = 66.dp, height = 61.dp),
        )

        Text(
            text = title,
            color = Gray700,
            style = SoftieTypo.head1,
            modifier =
                Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.CenterHorizontally),
        )

        Text(
            text = semiTitle,
            color = Gray300,
            style = SoftieTypo.body2,
            modifier =
                Modifier
                    .padding(top = 6.dp, bottom = 35.dp)
                    .align(Alignment.CenterHorizontally),
        )

        BottomTwoBtn(
            onClickLeftBtn = onClickLeftBtn,
            onClickRightBtn = onClickRightBtn,
            leftColor = leftBtnColor,
            rightColor = rightBtnColor,
            leftContent = leftBtnText,
            rightContent = rightBtnText,
            leftBtnTextColor = leftBtnTextColor,
            rightBtnTextColor = rightBtnTextColor,
            twoBtnBottomPadding = 32,
            twoBtnHorizontalPadding = 20,
        )
    }
}
