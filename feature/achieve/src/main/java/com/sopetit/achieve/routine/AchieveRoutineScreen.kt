package com.sopetit.achieve.routine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun AchieveRoutineScreen(
    achieveThemeId: SharedFlow<Int>,
) {
    val viewModel: AchieveRoutineViewModel = hiltViewModel()
    val uiState: AchieveRoutinePageState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(achieveThemeId) {
        achieveThemeId.collect {
            viewModel.getAchieveThemeRoutine(it)
        }
    }
    AchieveRoutineContent()
}

@Composable
fun AchieveRoutineContent() {
    //
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAchieveRoutine() {
    AchieveRoutineContent()
}