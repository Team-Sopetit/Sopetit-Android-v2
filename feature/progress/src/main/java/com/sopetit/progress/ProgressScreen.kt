package com.sopetit.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.ProgressTitleDate
import com.sopetit.design_system.SoftieTypo
import org.threeten.bp.LocalDate

@Composable
fun ProgressScreen() {

    val today = LocalDate.now()

    ProgressContent(
        todayYear = today.year,
        todayMonth = today.monthValue,
        todayDay = today.dayOfMonth
    )
}

@Composable
fun ProgressContent(
    todayYear: Int = 0,
    todayMonth: Int = 0,
    todayDay: Int = 0
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
    ) {
        Text(
            text = String.format(ProgressTitleDate, todayYear, todayMonth, todayDay),
            color = Gray700,
            style = SoftieTypo.head3,
            modifier = Modifier
                .padding(start = 20.dp)
                .padding(vertical = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ProgressRoutineContent()
        }
    }
}

@Composable
fun ProgressRoutineContent() {
    //
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProgress() {
    ProgressContent()
}