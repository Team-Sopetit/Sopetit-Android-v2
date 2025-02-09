package com.sopetit.ui.common.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray300
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.SoftieTypo

@Composable
fun BottomRectangleBtn(
    btnTextContent: String,
    isBtnActivated: Boolean = false,
    onClickAction: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }

    BottomRectangleBtnContent(
        interactionSource = interactionSource,
        btnTextContent = btnTextContent,
        isBtnActivated = isBtnActivated,
        onClickAction = onClickAction
    )
}

@Composable
fun BottomRectangleBtnContent(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    btnTextContent: String,
    isBtnActivated: Boolean = false,
    onClickAction: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 32.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(10))
                .background(if (isBtnActivated) Gray650 else Gray300)
                .wrapContentHeight()
                .clickable(
                    enabled = isBtnActivated,
                    indication = null,
                    interactionSource = interactionSource,
                    onClick = { onClickAction() }
                )
        ) {
            Text(
                text = btnTextContent,
                style = SoftieTypo.body1,
                color = Gray0,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 17.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBottomRectangle() {
    BottomRectangleBtnContent(btnTextContent = "test")
}