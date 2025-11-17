package com.sopetit.ui.common.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray500
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.R
import com.sopetit.design_system.SoftieTypo
import com.sopetit.ui.common.button.RoundCornerShapeBtn

@Composable
fun EmptyRoutineScreen(
    titleContent: String,
    titleColor: Color,
    titleStyle: TextStyle,
    btnBackgroundColor: Color,
    btnCornerShape: Int,
    btnBorderColor: Color,
    btnTextContent: String,
    btnTextColor: Color,
    btnTextStyle: TextStyle,
    btnVerticalPadding: Int,
    btnHorizontalPadding: Int,
    onClickAddRoutine: () -> Unit,
) {
    EmptyRoutineContent(
        titleContent = titleContent,
        titleColor = titleColor,
        titleStyle = titleStyle,
        btnBackgroundColor = btnBackgroundColor,
        btnCornerShape = btnCornerShape,
        btnBorderColor = btnBorderColor,
        btnTextContent = btnTextContent,
        btnTextColor = btnTextColor,
        btnTextStyle = btnTextStyle,
        btnVerticalPadding = btnVerticalPadding,
        btnHorizontalPadding = btnHorizontalPadding,
        onClickAddRoutine = onClickAddRoutine,
    )
}

@Composable
fun EmptyRoutineContent(
    titleContent: String = "",
    titleColor: Color = Gray500,
    titleStyle: TextStyle = SoftieTypo.head3,
    btnBackgroundColor: Color = Gray650,
    btnCornerShape: Int = 0,
    btnBorderColor: Color = Gray650,
    btnTextContent: String = "",
    btnTextColor: Color = Gray0,
    btnTextStyle: TextStyle = SoftieTypo.caption1,
    btnVerticalPadding: Int = 0,
    btnHorizontalPadding: Int = 0,
    onClickAddRoutine: () -> Unit = {},
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_empty_routine),
            contentDescription = "empty bear",
            modifier =
                Modifier
                    .size(width = 100.dp, height = 120.dp),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = titleContent,
            color = titleColor,
            style = titleStyle,
        )

        Spacer(modifier = Modifier.height(12.dp))

        RoundCornerShapeBtn(
            backgroundColor = btnBackgroundColor,
            cornerShape = btnCornerShape,
            borderColor = btnBorderColor,
            textContent = btnTextContent,
            textColor = btnTextColor,
            textStyle = btnTextStyle,
            verticalPadding = btnVerticalPadding,
            horizontalPadding = btnHorizontalPadding,
            onClickAction = onClickAddRoutine,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewEmptyRoutine() {
    EmptyRoutineContent()
}
