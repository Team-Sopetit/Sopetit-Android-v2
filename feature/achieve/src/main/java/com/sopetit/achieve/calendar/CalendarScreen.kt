package com.sopetit.achieve.calendar

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.design_system.CalendarYearMonthText
import com.sopetit.design_system.DateContent
import com.sopetit.design_system.EmptyAchieveRoutine
import com.sopetit.design_system.Fri
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray300
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray500
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.Mon
import com.sopetit.design_system.Num
import com.sopetit.design_system.Pink50
import com.sopetit.design_system.R
import com.sopetit.design_system.Red200
import com.sopetit.design_system.Sat
import com.sopetit.design_system.SoftieTypo
import com.sopetit.design_system.Sun
import com.sopetit.design_system.Thu
import com.sopetit.design_system.Tue
import com.sopetit.design_system.Wed
import com.sopetit.domain.entity.enums.RoutineType
import com.sopetit.domain.entity.response.calendar.CalendarHistoryItemModel
import com.sopetit.domain.entity.response.calendar.CalendarHistoryModel
import com.sopetit.domain.entity.response.calendar.CalendarModel
import com.sopetit.domain.entity.response.memo.MemoActionModel
import com.sopetit.domain.entity.response.screen.RoutineDetailModel
import com.sopetit.ui.common.content.DashedDivider
import com.sopetit.ui.common.type.BearType
import com.sopetit.ui.common.type.ThemeIconType
import com.sopetit.ui.util.generateCalendarDays
import com.sopetit.ui.util.setAfterYearMonth
import com.sopetit.ui.util.setBeforeYearMonth
import kotlinx.coroutines.flow.SharedFlow
import org.threeten.bp.LocalDate

@Composable
fun CalendarScreen(
    showRoutineDeleteBottomSheet: (RoutineDetailModel) -> Unit,
    deleteRoutine: SharedFlow<RoutineDetailModel>,
    showMemoWriteBottomSheet: () -> Unit,
    writtenMemo: SharedFlow<String>,
    showMemoDetailBottomSheet: (MemoActionModel) -> Unit,
    memoActionModel: SharedFlow<MemoActionModel>,

    ) {

    val viewModel: CalendarViewModel = hiltViewModel()
    val uiState: CalendarPageState by viewModel.uiState.collectAsStateWithLifecycle()

    val today = LocalDate.now()
    var year by remember { mutableIntStateOf(today.year) }
    var month by remember { mutableIntStateOf(today.monthValue) }
    var days by remember { mutableStateOf(generateCalendarDays(year, month)) }
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(deleteRoutine) {
        deleteRoutine.collect {
            viewModel.deleteRoutine(it)
        }
    }

    LaunchedEffect(writtenMemo) {
        writtenMemo.collect {
            viewModel.writeMemo(it)
        }
    }

    LaunchedEffect(memoActionModel) {
        memoActionModel.collect {
            viewModel.setMemoAction(it)
        }
    }

    CalendarContent(
        todayYear = year,
        todayMonth = month,
        todayDate = today,
        days = days,
        onClickMonthBefore = {
            year = setBeforeYearMonth(year, month)[0]
            month = setBeforeYearMonth(year, month)[1]
            days = generateCalendarDays(year, month)
        },
        onClickMonthAfter = {
            year = setAfterYearMonth(year, month)[0]
            month = setAfterYearMonth(year, month)[1]
            days = generateCalendarDays(year, month)
        },
        isClickAfterMonthEnabled = (!days.contains(today)),
        interactionSource = interactionSource,
        selectedDate = uiState.selectedDate,
        onSelectDate = { viewModel.selectDate(it) },
        calendarList = uiState.calendarList,
        dateItem = uiState.calendarList[uiState.selectedDate.toString()],
        onClickRoutineDelete = { type, item ->
            showRoutineDeleteBottomSheet(
                viewModel.setRoutineDetail(
                    type,
                    item
                )
            )
        },
        onClickMemoBtn = { showMemoWriteBottomSheet() },
        onClickMemo = { showMemoDetailBottomSheet(it) },
        dollImg = BearType.getDollFace(uiState.dollType)
    )
}

@Composable
fun CalendarContent(
    todayYear: Int = 0,
    todayMonth: Int = 0,
    todayDate: LocalDate = LocalDate.now(),
    days: List<LocalDate> = emptyList(),
    onClickMonthBefore: () -> Unit = {},
    onClickMonthAfter: () -> Unit = {},
    isClickAfterMonthEnabled: Boolean = false,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    selectedDate: LocalDate = LocalDate.now(),
    onSelectDate: (LocalDate) -> Unit = {},
    calendarList: Map<String, CalendarModel> = emptyMap(),
    dateItem: CalendarModel? = null,
    onClickRoutineDelete: (RoutineType, CalendarHistoryItemModel) -> Unit = { type, item -> },
    onClickMemoBtn: () -> Unit = {},
    onClickMemo: (MemoActionModel) -> Unit = {},
    dollImg: Int = R.drawable.ic_brown_face,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
            .verticalScroll(rememberScrollState())
    ) {
        CalendarYearMonth(
            todayYear = todayYear,
            todayMonth = todayMonth,
            onClickMonthBefore = onClickMonthBefore,
            onClickMonthAfter = onClickMonthAfter,
            isClickAfterMonthEnabled = isClickAfterMonthEnabled,
            interactionSource = interactionSource
        )

        CalendarWeekTitle()

        CalendarDayOfMonth(
            days = days,
            interactionSource = interactionSource,
            todayDate = todayDate,
            onSelectDate = onSelectDate,
            selectedDate = selectedDate,
            calendarList = calendarList
        )

        CalendarDateDetailInfo(
            todayDate = todayDate,
            dateItem = dateItem,
            onClickRoutineDelete = onClickRoutineDelete,
            interactionSource = interactionSource,
            onClickMemoBtn = onClickMemoBtn,
            onClickMemo = onClickMemo,
            dollImg = dollImg
        )
    }
}

@Composable
fun CalendarYearMonth(
    interactionSource: MutableInteractionSource,
    todayYear: Int,
    todayMonth: Int,
    onClickMonthBefore: () -> Unit,
    onClickMonthAfter: () -> Unit,
    isClickAfterMonthEnabled: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 28.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = "arrow before",
            modifier = Modifier
                .clickable(
                    onClick = { onClickMonthBefore() },
                    indication = null,
                    interactionSource = interactionSource
                )
        )

        Text(
            text = String.format(CalendarYearMonthText, todayYear, todayMonth),
            color = Gray700,
            style = SoftieTypo.head3,
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "arrow before",
            colorFilter = ColorFilter.tint(if (isClickAfterMonthEnabled) Gray650 else Gray300),
            modifier = Modifier
                .clickable(
                    enabled = isClickAfterMonthEnabled,
                    onClick = { onClickMonthAfter() },
                    indication = null,
                    interactionSource = interactionSource
                )
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
    todayDate: LocalDate,
    onSelectDate: (LocalDate) -> Unit,
    selectedDate: LocalDate,
    calendarList: Map<String, CalendarModel>,
) {
    Column(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 20.dp, start = 24.dp, end = 24.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        days.chunked(7).forEach { week ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                week.forEach { day ->
                    BoxWithConstraints(
                        modifier = Modifier.weight(1f)
                    ) {
                        CalendarDateItem(
                            day = day,
                            modifier = Modifier.width(maxWidth),
                            interactionSource = interactionSource,
                            isToday = (todayDate == day),
                            isDayAfter = (day > todayDate),
                            onSelect = { onSelectDate(day) },
                            isSelectedDate = (selectedDate == day),
                            dateIcon = setCalendarDateItemIcon(calendarList[day.toString()])
                        )
                    }
                }

                if (week.size < 7) {
                    repeat(7 - week.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

fun setCalendarDateItemIcon(
    dateItem: CalendarModel?,
): Int {

    val dateIcon: Int =
        if (dateItem == null) 0
        else if (dateItem.memoContent.isNotEmpty()) R.drawable.ic_som_rainbow
        else R.drawable.ic_som

    return dateIcon
}

@Composable
fun CalendarDateItem(
    day: LocalDate,
    modifier: Modifier,
    interactionSource: MutableInteractionSource,
    isDayAfter: Boolean,
    isToday: Boolean,
    onSelect: () -> Unit,
    isSelectedDate: Boolean,
    dateIcon: Int = 0,
) {
    Column(
        modifier = modifier
            .clickable(
                enabled = !isDayAfter,
                interactionSource = interactionSource,
                indication = null,
                onClick = onSelect
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (dateIcon != 0) {
            Image(
                painter = painterResource(id = dateIcon),
                contentDescription = "som",
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .size(40.dp)
            )
        } else {
            Box(
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Gray200)
            )
        }

        if (isToday || isSelectedDate) {
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .clip(RoundedCornerShape(99.dp))
                    .background(if (isToday) Gray400 else Gray650)
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${day.dayOfMonth}",
                    color = Gray0,
                    style = SoftieTypo.caption1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(vertical = 1.dp)
                )
            }
        } else {
            Text(
                text = "${day.dayOfMonth}",
                color = if (isDayAfter) Gray300 else Gray700,
                style = SoftieTypo.caption1,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 1.dp)
            )
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun CalendarDateDetailInfo(
    todayDate: LocalDate,
    dateItem: CalendarModel?,
    onClickRoutineDelete: (RoutineType, CalendarHistoryItemModel) -> Unit,
    interactionSource: MutableInteractionSource,
    onClickMemoBtn: () -> Unit,
    onClickMemo: (MemoActionModel) -> Unit,
    dollImg: Int,
) {
    Divider(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .border(2.dp, Gray200)
    )

    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .height(32.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = String.format(DateContent, todayDate.dayOfMonth),
            style = SoftieTypo.head3,
            color = Gray700
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Red200)) {
                    append(
                        (dateItem?.histories?.size ?: 0).toString()
                    )
                }
                append(Num)
            },
            style = SoftieTypo.body2,
            color = Gray500,
            modifier = Modifier
                .padding(start = 4.dp)
                .weight(1f)
        )

        if (dateItem != null) {
            CalendarDateMemoBtn(
                onClickAction = onClickMemoBtn,
                interactionSource = interactionSource
            )
        }
    }

    if (dateItem == null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 96.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_empty_routine),
                contentDescription = "empty routine",
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .size(width = 100.dp, height = 120.dp)
            )

            Text(
                text = EmptyAchieveRoutine,
                style = SoftieTypo.head3,
                color = Gray500
            )
        }
    } else {
        CalendarDateRoutineAchieve(
            dateItem = dateItem,
            onClickRoutineDelete = onClickRoutineDelete,
            interactionSource = interactionSource,
            onClickMemo = onClickMemo,
            dollImg = dollImg
        )
    }
}

@Composable
fun CalendarDateRoutineAchieve(
    dateItem: CalendarModel,
    onClickRoutineDelete: (RoutineType, CalendarHistoryItemModel) -> Unit,
    interactionSource: MutableInteractionSource,
    onClickMemo: (MemoActionModel) -> Unit,
    dollImg: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp, start = 20.dp, end = 20.dp)
    ) {
        if (dateItem.memoContent.isNotEmpty()) {
            CalendarDateMemoBox(
                memo = dateItem.memoContent,
                interactionSource = interactionSource,
                onClickAction = {
                    onClickMemo(
                        MemoActionModel(
                            memoId = dateItem.memoId,
                            content = dateItem.memoContent
                        )
                    )
                },
                dollImg = dollImg
            )
        }

        dateItem.histories.forEach { history ->
            CalendarDateRoutineHistory(
                history = history,
                onClickAction = onClickRoutineDelete,
                interactionSource = interactionSource
            )
        }
    }
}

@Composable
fun CalendarDateMemoBox(
    memo: String,
    onClickAction: () -> Unit,
    interactionSource: MutableInteractionSource,
    dollImg: Int,
) {
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp, start = 20.dp, end = 20.dp)
            .clickable(
                onClick = onClickAction,
                interactionSource = interactionSource,
                indication = null
            )
    ) {
        DashedDivider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Gray200)
            ) {
                Image(
                    painter = painterResource(id = dollImg),
                    contentDescription = "doll img",
                    modifier = Modifier
                        .size(29.dp)
                        .align(Alignment.Center)
                )
            }

            Text(
                text = memo,
                style = SoftieTypo.body2,
                color = Gray500,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .weight(1f)
            )
        }

        DashedDivider()
    }
}

@Composable
fun CalendarDateRoutineHistory(
    history: CalendarHistoryModel,
    onClickAction: (RoutineType, CalendarHistoryItemModel) -> Unit,
    interactionSource: MutableInteractionSource,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = ThemeIconType.getThemeIcon(history.themeId)),
            contentDescription = "theme icon",
            modifier = Modifier
                .size(16.dp)
        )

        Text(
            text = history.themeName,
            color = Gray500,
            style = SoftieTypo.body2,
            modifier = Modifier
                .padding(start = 2.dp)
        )
    }

    history.histories.forEach { historyItem ->
        Row(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(if (historyItem.isChallenge) Pink50 else Gray0)
                .border(1.dp, Gray200, RoundedCornerShape(10.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = historyItem.content,
                color = Gray700,
                style = SoftieTypo.body2,
                modifier = Modifier
                    .padding(top = 18.dp, bottom = 18.dp, start = 16.dp)
                    .weight(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_more_info),
                contentDescription = "more",
                modifier = Modifier
                    .padding(start = 23.dp, end = 16.dp, top = 24.dp, bottom = 24.dp)
                    .size(24.dp)
                    .clickable(
                        onClick = {
                            onClickAction(
                                RoutineType.getType(historyItem.isChallenge),
                                historyItem
                            )
                        },
                        interactionSource = interactionSource,
                        indication = null
                    )
            )
        }
    }

    Spacer(modifier = Modifier.padding(bottom = 12.dp))
}

@Composable
fun CalendarDateMemoBtn(
    onClickAction: () -> Unit,
    interactionSource: MutableInteractionSource,
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(Gray650)
            .clickable(
                onClick = onClickAction,
                indication = null,
                interactionSource = interactionSource
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_pen),
            contentDescription = "pen",
            modifier = Modifier
                .padding(7.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCalendar() {
    CalendarContent()
}