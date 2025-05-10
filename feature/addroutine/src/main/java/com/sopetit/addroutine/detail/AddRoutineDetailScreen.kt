package com.sopetit.addroutine.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.SharedFlow
import timber.log.Timber

@Composable
fun AddRoutineDetailScreen(
    selectedThemeId: SharedFlow<Int>
) {
    LaunchedEffect(selectedThemeId) {
        selectedThemeId.collect {
            Timber.d("[테스트] -> $it")
        }
    }

    AddRoutineDetailContent()
}

@Composable
fun AddRoutineDetailContent() {
    //
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAddRoutineDetail() {
    AddRoutineDetailContent()
}