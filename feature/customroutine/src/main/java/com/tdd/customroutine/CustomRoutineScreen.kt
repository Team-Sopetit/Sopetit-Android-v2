package com.tdd.customroutine

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.design_system.FinishContent
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray300
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.R
import com.sopetit.design_system.RoutineTitle
import com.sopetit.design_system.RoutineWriteHint
import com.sopetit.design_system.SoftieTypo
import com.sopetit.design_system.ThemeTitle
import com.sopetit.ui.common.item.ThemeListItem
import com.sopetit.ui.common.type.ThemeIconType

@Composable
fun CustomRoutineScreen() {

    val viewModel: CustomRoutineViewModel = hiltViewModel()
    val uiState: CustomRoutinePageState by viewModel.uiState.collectAsStateWithLifecycle()

    CustomRoutineContent(
        onSelectThemeId = { viewModel.setSelectedTheme(it) },
        selectedThemeId = uiState.selectedThemeId,
        routineWriteInput = uiState.routineWriteInput,
        onRoutineValueChange = { viewModel.onRoutineValueChange(it) }
    )
}

@Composable
fun CustomRoutineContent(
    onSelectThemeId: (Int) -> Unit = {},
    selectedThemeId: Int = 0,
    routineWriteInput: String = "",
    onRoutineValueChange: (String) -> Unit = {},
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
                    .align(Alignment.CenterEnd)
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

        CustomRoutineAlarm()
    }
}

@Composable
fun FinishBtn(
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(99.dp))
            .background(Gray200)
    ) {
        Text(
            text = FinishContent,
            style = SoftieTypo.body2,
            color = Gray400,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 12.dp)
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

    LaunchedEffect(imeVisible) {
        if (!imeVisible) {
            focusManager.clearFocus()
        }
    }

    Text(
        text = RoutineTitle,
        style = SoftieTypo.body2,
        color = Gray700,
        modifier = Modifier
            .padding(top = 4.dp, start = 20.dp, bottom = 6.dp)
    )

    Row(
        modifier = Modifier
            .padding(top = 6.dp, bottom = 19.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Gray0)
            .border(1.dp, if (isFocused) Gray650 else Gray0, RoundedCornerShape(8.dp))
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
fun CustomRoutineAlarm() {
    //
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCustomRoutine() {
    CustomRoutineContent()
}