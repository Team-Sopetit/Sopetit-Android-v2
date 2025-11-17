package com.sopetit.ui.common.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sopetit.designsystem.Gray500
import com.sopetit.designsystem.Gray700
import com.sopetit.designsystem.SoftieTypo
import com.sopetit.designsystem.White0
import com.sopetit.ui.common.button.BottomTwoBtn
import com.sopetit.ui.common.model.TwoBtnDialogModel

@Composable
fun TwoBtnDialog(
    twoBtnDialogModel: TwoBtnDialogModel,
    onDismiss: () -> Unit,
    onClickDoBtn: () -> Unit,
) {
    TwoBtnDialogContent(
        title = twoBtnDialogModel.title,
        semiTitle = twoBtnDialogModel.semiTitle,
        leftBtnText = twoBtnDialogModel.leftBtnText,
        leftBtnTextColor = twoBtnDialogModel.leftBtnTextColor,
        leftBtnColor = twoBtnDialogModel.leftBtnColor,
        rightBtnText = twoBtnDialogModel.rightBtnText,
        rightBtnColor = twoBtnDialogModel.rightBtnColor,
        rightBtnColorBrush = twoBtnDialogModel.rightBtnColorBrush,
        rightBtnTextColor = twoBtnDialogModel.rightBtnTextColor,
        onDismiss = onDismiss,
        onClickDoBtn = onClickDoBtn,
    )
}

@Composable
fun TwoBtnDialogContent(
    title: String,
    semiTitle: String,
    leftBtnText: String,
    leftBtnTextColor: Color,
    leftBtnColor: Color,
    rightBtnText: String,
    rightBtnTextColor: Color,
    rightBtnColor: Color,
    rightBtnColorBrush: Brush? = null,
    onDismiss: () -> Unit,
    onClickDoBtn: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier =
                Modifier
                    .wrapContentHeight()
                    .width(266.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = White0),
        ) {
            Column {
                Text(
                    text = title,
                    color = Gray700,
                    style = SoftieTypo.head3,
                    textAlign = TextAlign.Center,
                    modifier =
                        Modifier
                            .padding(top = 15.dp)
                            .align(Alignment.CenterHorizontally),
                )

                Text(
                    text = semiTitle,
                    color = Gray500,
                    style = SoftieTypo.body2,
                    textAlign = TextAlign.Center,
                    modifier =
                        Modifier
                            .padding(top = 7.dp, bottom = 16.dp)
                            .align(Alignment.CenterHorizontally),
                )

                BottomTwoBtn(
                    onClickLeftBtn = onDismiss,
                    onClickRightBtn = onClickDoBtn,
                    leftColor = leftBtnColor,
                    rightColor = rightBtnColor,
                    leftContent = leftBtnText,
                    rightContent = rightBtnText,
                    rightBtnColorBrush = rightBtnColorBrush,
                    leftBtnTextColor = leftBtnTextColor,
                    rightBtnTextColor = rightBtnTextColor,
                    twoBtnBottomPadding = 10,
                    twoBtnHorizontalPadding = 11,
                )
            }
        }
    }
}
