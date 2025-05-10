package com.sopetit.addroutine.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.R
import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.ui.common.type.ThemeIconType
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun AddRoutineDetailScreen(
    selectedThemeId: SharedFlow<ThemeListItemModel>
) {
    val viewModel: AddRoutineDetailViewModel = hiltViewModel()
    val uiState: AddRoutineDetailPageState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(selectedThemeId) {
        selectedThemeId.collect {
            viewModel.setSelectedTheme(it)
        }
    }

    AddRoutineDetailContent(
        theme = uiState.selectedTheme
    )
}

@Composable
fun AddRoutineDetailContent(
    theme: ThemeListItemModel = ThemeListItemModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
    ) {
        Box(
            modifier = Modifier
        ) {
            Image(
                painter = painterResource(id = ThemeIconType.getThemeDetailBackground(themeId = theme.themeId)),
                contentDescription = "theme background",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "arrow back",
                modifier = Modifier
                    .padding(top = 14.dp, start = 20.dp)
                    .size(28.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAddRoutineDetail() {
    AddRoutineDetailContent()
}