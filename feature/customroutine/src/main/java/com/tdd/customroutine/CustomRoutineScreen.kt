package com.tdd.customroutine

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.design_system.AlarmTitle
import com.sopetit.design_system.FinishContent
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray300
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.R
import com.sopetit.design_system.Red200
import com.sopetit.design_system.RoutineTitle
import com.sopetit.design_system.RoutineWriteHint
import com.sopetit.design_system.RoutineWriteLengthOver
import com.sopetit.design_system.SoftieTypo
import com.sopetit.design_system.Switch
import com.sopetit.design_system.ThemeTitle
import com.sopetit.design_system.ZeroString
import com.sopetit.ui.common.item.ThemeListItem
import com.sopetit.ui.common.type.ThemeIconType
import com.sopetit.ui.common.type.TimeDayType
import com.sopetit.ui.common.type.TimeMinuteType
import com.sopetit.ui.util.convertTo24HourFormat
import com.sopetit.ui.util.fadingEdge
import timber.log.Timber
import java.sql.Time

@Composable
fun CustomRoutineScreen() {

    val viewModel: CustomRoutineViewModel = hiltViewModel()
    val uiState: CustomRoutinePageState by viewModel.uiState.collectAsStateWithLifecycle()

    val interactionSource = remember { MutableInteractionSource() }
    val hourState = rememberLazyListState(initialFirstVisibleItemIndex = 7)
    val minuteState = rememberLazyListState(initialFirstVisibleItemIndex = 3)
    val timeState = rememberLazyListState(initialFirstVisibleItemIndex = 2)

    CustomRoutineContent(
        interactionSource = interactionSource,
        onSelectThemeId = { viewModel.setSelectedTheme(it) },
        selectedThemeId = uiState.selectedThemeId,
        routineWriteInput = uiState.routineWriteInput,
        onRoutineValueChange = { viewModel.onRoutineValueChange(it) },
        onClickFinishBtn = {
            Timber.d(
                convertTo24HourFormat(hourState.firstVisibleItemIndex + 1, TimeMinuteType.getMinuteData(minuteState.firstVisibleItemIndex), TimeDayType.getDayData(timeState.firstVisibleItemIndex))
            )
        },
        onActivateAlarm = { viewModel.updateAlarmActivated() },
        isAlarmActivated = uiState.isAlarmActivated,
        hourState = hourState,
        minuteState = minuteState,
        timeState = timeState
    )
}

@Composable
fun CustomRoutineContent(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onSelectThemeId: (Int) -> Unit = {},
    selectedThemeId: Int = 0,
    routineWriteInput: String = "",
    onRoutineValueChange: (String) -> Unit = {},
    onClickFinishBtn: () -> Unit = {},
    isAlarmActivated: Boolean = false,
    onActivateAlarm: () -> Unit = {},
    hourState: LazyListState = LazyListState(),
    minuteState: LazyListState = LazyListState(),
    timeState: LazyListState = LazyListState(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(top = 14.dp, bottom = 14.dp, start = 20.dp)
                    .size(28.dp)
            )

            FinishBtn(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .align(Alignment.CenterEnd),
                interactionSource = interactionSource,
                onClickAction = onClickFinishBtn,
                isFinish = routineWriteInput.isNotEmpty() && selectedThemeId != 0
            )
        }

        CustomRoutineWrite(
            textInput = routineWriteInput,
            onValueChange = onRoutineValueChange
        )

        CustomRoutineTheme(
            onSelectThemeId = onSelectThemeId,
            selectedThemeId = selectedThemeId
        )

        CustomRoutineAlarm(
            isAlarmActivated = isAlarmActivated,
            onActivateAlarm = onActivateAlarm,
            interactionSource = interactionSource,
            hourState = hourState,
            minuteState = minuteState,
            timeState = timeState
        )
    }
}

@Composable
fun FinishBtn(
    modifier: Modifier,
    isFinish: Boolean = false,
    interactionSource: MutableInteractionSource,
    onClickAction: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(99.dp))
            .background(if (isFinish) Gray650 else Gray200)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClickAction
            )
    ) {
        Text(
            text = FinishContent,
            style = SoftieTypo.body2,
            color = if (isFinish) Gray0 else Gray400,
            modifier = Modifier
                .padding(vertical = 6.dp, horizontal = 12.dp)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomRoutineWrite(
    textInput: String,
    onValueChange: (String) -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val imeVisible = WindowInsets.isImeVisible
    val isLengthOver by remember { mutableStateOf(textInput.length > 50) }

    LaunchedEffect(imeVisible) {
        if (!imeVisible) {
            focusManager.clearFocus()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 6.dp, start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = RoutineTitle,
            style = SoftieTypo.body2,
            color = Gray700,
            modifier = Modifier
                .align(Alignment.CenterStart)
        )

        if (isLengthOver) {
            Text(
                text = RoutineWriteLengthOver,
                style = SoftieTypo.caption1,
                color = Red200,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            )
        }
    }

    Row(
        modifier = Modifier
            .padding(top = 6.dp, bottom = 19.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Gray0)
            .border(
                1.dp,
                if (isLengthOver) Red200 else if (isFocused) Gray650 else Gray0,
                RoundedCornerShape(8.dp)
            )
    ) {
        BasicTextField(
            value = textInput,
            onValueChange = { input ->
                onValueChange(input)
            },
            modifier = Modifier
                .padding(16.dp)
                .weight(1f)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            textStyle = SoftieTypo.body2.copy(
                color = Gray700,
                textAlign = TextAlign.Start
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    if (textInput.isEmpty() && !isFocused) {
                        Text(
                            text = RoutineWriteHint,
                            style = SoftieTypo.body2,
                            color = Gray300,
                            textAlign = TextAlign.Start
                        )
                    }
                    innerTextField()
                }
            }
        )

        if (isFocused && textInput.isNotEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.ic_close_circle),
                contentDescription = "delete",
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
                    .size(20.dp)
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomRoutineTheme(
    onSelectThemeId: (Int) -> Unit,
    selectedThemeId: Int,
) {
    Text(
        text = ThemeTitle,
        style = SoftieTypo.body2,
        color = Gray700,
        modifier = Modifier
            .padding(top = 19.dp, start = 20.dp)
    )

    Column(
        modifier = Modifier
            .padding(top = 10.dp, bottom = 19.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth(),
    ) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ThemeIconType.entries.forEach { theme ->
                ThemeListItem(
                    themeName = theme.themeName,
                    themeItemIcon = theme.themeIcon,
                    onClick = { onSelectThemeId(theme.themeId) },
                    isSelectedTheme = (selectedThemeId == theme.themeId)
                )
            }
        }
    }
}

@Composable
fun CustomRoutineAlarm(
    isAlarmActivated: Boolean,
    onActivateAlarm: () -> Unit,
    interactionSource: MutableInteractionSource,
    hourState: LazyListState,
    minuteState: LazyListState,
    timeState: LazyListState,
) {
    Column(
        modifier = Modifier
            .padding(top = 19.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Gray0)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = AlarmTitle,
                color = Gray700,
                style = SoftieTypo.body2,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(vertical = 17.dp)
            )

            CustomRoutineSwitch(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(vertical = 12.dp),
                isAlarmActivated = isAlarmActivated,
                onActivateAlarm = onActivateAlarm,
                interactionSource = interactionSource
            )
        }

        if (isAlarmActivated) {
            CustomRoutineAlarmTime(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp, bottom = 13.dp, start = 69.dp, end = 68.dp)
                    .fillMaxSize(),
                hourState = hourState,
                minuteState = minuteState,
                timeState = timeState
            )
        }
    }
}

@Composable
fun CustomRoutineSwitch(
    modifier: Modifier,
    isAlarmActivated: Boolean = false,
    onActivateAlarm: () -> Unit,
    interactionSource: MutableInteractionSource,
) {
    Box(
        modifier = modifier
            .width(51.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(if (isAlarmActivated) Gray650 else Switch)
            .clickable(
                onClick = onActivateAlarm,
                indication = null,
                interactionSource = interactionSource
            )
    ) {
        Box(
            modifier = Modifier
                .align(if (isAlarmActivated) Alignment.CenterEnd else Alignment.CenterStart)
                .padding(vertical = 2.dp)
                .padding(start = if (isAlarmActivated) 0.dp else 2.dp)
                .padding(end = if (isAlarmActivated) 2.dp else 0.dp)
                .size(27.dp)
                .clip(CircleShape)
                .background(Gray0)
        )
    }
}

@Composable
fun CustomRoutineAlarmTime(
    modifier: Modifier,
    hourState: LazyListState,
    minuteState: LazyListState,
    timeState: LazyListState,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AlarmTimeItem(
            modifier = Modifier.weight(1f),
            timeList = (1..12).map { it.toString() },
            listState = hourState
        )

        AlarmTimeItem(
            modifier = Modifier.weight(1f),
            timeList = TimeMinuteType.entries.map { it.data },
            listState = minuteState
        )

        AlarmTimeItem(
            modifier = Modifier.weight(1f),
            timeList = TimeDayType.entries.map { it.data },
            listState = timeState
        )
    }
}

@Composable
fun AlarmTimeItem(
    modifier: Modifier,
    timeList: List<String>,
    listState: LazyListState,
) {
    val extendedItems = listOf(ZeroString, ZeroString) + timeList + listOf(ZeroString, ZeroString)
    val visibleItemsCount = 5
    val itemHeight = 30.dp
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)
    val fadingEdgeGradient = remember {
        Brush.verticalGradient(
            0f to Color.Transparent,
            0.5f to Color.Black,
            1f to Color.Transparent
        )
    }

    LazyColumn(
        state = listState,
        modifier = modifier
            .height(itemHeight * visibleItemsCount)
            .fadingEdge(fadingEdgeGradient),
        flingBehavior = flingBehavior
    ) {
        items(extendedItems.size) { index ->
            val item = extendedItems[index]
            val firstVisibleItemIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }
            val fontStyle = when (index) {
                firstVisibleItemIndex + 2 -> SoftieTypo.head1
                firstVisibleItemIndex + 1, firstVisibleItemIndex + 3 -> SoftieTypo.head3
                firstVisibleItemIndex, firstVisibleItemIndex + 4 -> SoftieTypo.body1
                else -> SoftieTypo.body2
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight),
                contentAlignment = Alignment.Center
            ) {
                if (item != ZeroString) {
                    Text(
                        text = item,
                        style = fontStyle,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .height(itemHeight),
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCustomRoutine() {
    CustomRoutineContent()
}