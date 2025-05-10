package com.sopetit.addroutine.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun AddRoutineDetailScreen(
    selectedThemeId: SharedFlow<Int>
) {
    val viewModel: AddRoutineDetailViewModel = hiltViewModel()
    val uiState: AddRoutineDetailPageState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(selectedThemeId) {
        selectedThemeId.collect {
            viewModel.setSelectedThemeId(it)
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