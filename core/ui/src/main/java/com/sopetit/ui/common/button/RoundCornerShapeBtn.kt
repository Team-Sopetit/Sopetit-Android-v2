package com.sopetit.ui.common.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray500
import com.sopetit.design_system.SoftieTypo

@Composable
fun RoundCornerShapeBtn(
    backgroundColor: Color,
    cornerShape: Int,
    borderColor: Color,
    textContent: String,
    textColor: Color,
    textStyle: TextStyle,
    verticalPadding: Int,
    horizontalPadding: Int,
    onClickAction: () -> Unit,
) {
    RoundCornerShapeBtnContent(
        backgroundColor = backgroundColor,
        cornerShape = cornerShape,
        borderColor = borderColor,
        textContent = textContent,
        textColor = textColor,
        textStyle = textStyle,
        verticalPadding = verticalPadding,
        horizontalPadding = horizontalPadding,
        onClickAction = onClickAction,
    )
}

@Composable
fun RoundCornerShapeBtnContent(
    backgroundColor: Color = Gray200,
    cornerShape: Int = 0,
    borderColor: Color = Gray400,
    textContent: String = "",
    textColor: Color = Gray500,
    textStyle: TextStyle = SoftieTypo.caption1,
    verticalPadding: Int = 0,
    horizontalPadding: Int = 0,
    onClickAction: () -> Unit = {},
) {
    Box(
        modifier =
            Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(cornerShape.dp))
                .background(backgroundColor)
                .border(1.dp, color = borderColor, RoundedCornerShape(cornerShape.dp))
                .clickable(
                    onClick = onClickAction,
                ),
    ) {
        Text(
            text = textContent,
            color = textColor,
            style = textStyle,
            modifier =
                Modifier
                    .padding(vertical = verticalPadding.dp, horizontal = horizontalPadding.dp),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRoundCoreShapeBtn() {
    RoundCornerShapeBtnContent()
}
