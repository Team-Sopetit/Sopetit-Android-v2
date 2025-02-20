package com.sopetit.ui.common.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.SoftieTypo

@Composable
fun DailyRoutineListItem(
    routineContent: String
) {
    DailyRoutineListItemContent(
        routineContent = routineContent
    )
}

@Composable
fun DailyRoutineListItemContent(
    routineContent: String = ""
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = Gray200, RoundedCornerShape(10.dp))
            .background(Gray0)
    ) {
        Text(
            text = routineContent,
            color = Gray700,
            style = SoftieTypo.body2,
            modifier = Modifier
                .padding(start = 16.dp)
                .padding(vertical = 18.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDailyRoutineListItem() {
    DailyRoutineListItemContent()
}