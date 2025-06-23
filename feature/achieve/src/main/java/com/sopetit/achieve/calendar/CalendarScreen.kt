package com.sopetit.achieve.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.design_system.CalendarYearMonthText
import com.sopetit.design_system.Fri
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.Mon
import com.sopetit.design_system.Sat
import com.sopetit.design_system.SoftieTypo
import com.sopetit.design_system.Sun
import com.sopetit.design_system.Thu
import com.sopetit.design_system.Tue
import com.sopetit.design_system.Wed
import com.sopetit.ui.util.generateCalendarDays
import com.sopetit.ui.util.setBeforeYearMonth
import org.threeten.bp.LocalDate

@Composable
fun CalendarScreen() {

    val viewModel: CalendarViewModel = hiltViewModel()
    val uiState: CalendarPageState by viewModel.uiState.collectAsStateWithLifecycle()

    val today = LocalDate.now()
    var year by remember { mutableIntStateOf(today.year) }
    var month by remember { mutableIntStateOf(today.monthValue) }
    var days by remember { mutableStateOf(generateCalendarDays(year, month)) }
    val interactionSource = remember { MutableInteractionSource() }

    CalendarContent(
        todayYear = year,
        todayMonth = month,
        days = days,
        onClickMonthBefore = {
            year = setBeforeYearMonth(year, month)[0]
            month = setBeforeYearMonth(year, month)[1]
            days = generateCalendarDays(year, month)
        },
        interactionSource = interactionSource,
        selectedDate = uiState.selectedDate,
        onSelectDate = { viewModel.selectDate(it) },
    )
}

@Composable
fun CalendarContent(
    todayYear: Int = 0,
    todayMonth: Int = 0,
    days: List<LocalDate> = emptyList(),
    onClickMonthBefore: () -> Unit = {},
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    selectedDate: LocalDate = LocalDate.now(),
    onSelectDate: (LocalDate) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
    ) {
        CalendarYearMonth(
            todayYear = todayYear,
            todayMonth = todayMonth
        )

        CalendarWeekTitle()

        CalendarDayOfMonth(
            days = days,
            interactionSource = interactionSource
        )
    }
}

@Composable
fun CalendarYearMonth(
    todayYear: Int,
    todayMonth: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = String.format(CalendarYearMonthText, todayYear, todayMonth),
            color = Gray700,
            style = SoftieTypo.head3,
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )
    }
}

@Composable
fun CalendarWeekTitle() {
    val weeks: List<String> = listOf(Sun, Mon, Tue, Wed, Thu, Fri, Sat)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        weeks.forEach { week ->
            Text(
                text = week,
                color = Gray400,
                style = SoftieTypo.body2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp)
            )
        }
    }
}

@Composable
fun CalendarDayOfMonth(
    days: List<LocalDate>,
    interactionSource: MutableInteractionSource,
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 20.dp, start = 24.dp, end = 24.dp)
    ) {
        val cellWidth = maxWidth / 7

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(days) { day ->
                CalendarDateItem(
                    day = day,
                    modifier = Modifier
                        .width(cellWidth),
                    interactionSource = interactionSource,
                )
            }
        }
    }
}

@Composable
fun CalendarDateItem(
    day: LocalDate,
    modifier: Modifier,
    interactionSource: MutableInteractionSource,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Gray200)
        )

        Text(
            text = "${day.dayOfMonth}",
            color = Gray700,
            style = SoftieTypo.caption1,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCalendar() {
    CalendarContent()
}