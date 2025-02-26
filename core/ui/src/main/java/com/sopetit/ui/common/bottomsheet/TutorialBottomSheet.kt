package com.sopetit.ui.common.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.SoftieTypo
import com.sopetit.design_system.TutorialFirstTitle
import com.sopetit.design_system.TutorialNextBtn
import com.sopetit.ui.common.button.BottomRectangleBtn

@Composable
fun TutorialBottomSheet() {
    TutorialBottomSheetContent()
}

@Composable
fun TutorialBottomSheetContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray0),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(top = 24.dp)
                .wrapContentSize()
                .clip(RoundedCornerShape(99.dp))
                .background(Gray200)
        ) {
            Text(
                text = TutorialFirstTitle,
                color = Gray700,
                style = SoftieTypo.head3,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }

        Box(
            modifier = Modifier
                .size(320.dp)
                .padding(top = 24.dp)
        )

        BottomRectangleBtn(
            btnTextContent = TutorialNextBtn,
            isBtnActivated = true
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTutorialBottomSheet() {
    TutorialBottomSheetContent()
}