package com.sopetit.addroutine.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.design_system.ChallengeRoutine
import com.sopetit.design_system.DailyRoutine
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray500
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.R
import com.sopetit.design_system.RoutineAddBtn
import com.sopetit.design_system.SoftieTypo
import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.ui.common.button.BottomRectangleBtn
import com.sopetit.ui.common.type.ThemeIconType
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun AddRoutineDetailScreen(
    selectedThemeId: SharedFlow<ThemeListItemModel>,
) {
    val viewModel: AddRoutineDetailViewModel = hiltViewModel()
    val uiState: AddRoutineDetailPageState by viewModel.uiState.collectAsStateWithLifecycle()

    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(selectedThemeId) {
        selectedThemeId.collect {
            viewModel.setSelectedTheme(it)
        }
    }

    AddRoutineDetailContent(
        interactionSource = interactionSource,
        theme = uiState.selectedTheme,
        selectedRoutine = uiState.selectedRoutineTab,
        onSelectRoutine = { viewModel.setSelectedRoutineTab(it) }
    )
}

@Composable
fun AddRoutineDetailContent(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    theme: ThemeListItemModel = ThemeListItemModel(),
    selectedRoutine: String = DailyRoutine,
    onSelectRoutine: (String) -> Unit = {},
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

        Text(
            text = theme.description,
            color = Gray500,
            style = SoftieTypo.body2,
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 20.dp)
        )

        RoutineDetailList(
            selectedRoutine = selectedRoutine,
            onSelectRoutine = onSelectRoutine,
            interactionSource = interactionSource
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            item {
                SelectedRoutineContent()
            }
        }

        BottomRectangleBtn(
            btnTextContent = String.format(RoutineAddBtn, 0)
        )
    }
}

@Composable
fun RoutineDetailList(
    selectedRoutine: String = DailyRoutine,
    onSelectRoutine: (String) -> Unit,
    interactionSource: MutableInteractionSource
) {
    RoutineDetailTab(
        selectedRoutine = selectedRoutine,
        onSelectRoutine = onSelectRoutine,
        interactionSource = interactionSource
    )
}

@Composable
fun RoutineDetailTab(
    selectedRoutine: String = DailyRoutine,
    onSelectRoutine: (String) -> Unit,
    interactionSource: MutableInteractionSource
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            RoutineDetailTabItem(
                modifier = Modifier
                    .weight(1f),
                tabTitle = DailyRoutine,
                isSelected = (selectedRoutine == DailyRoutine),
                onSelect = { onSelectRoutine(DailyRoutine) },
                interactionSource = interactionSource
            )
            RoutineDetailTabItem(
                modifier = Modifier
                    .weight(1f),
                tabTitle = ChallengeRoutine,
                isSelected = (selectedRoutine == ChallengeRoutine),
                onSelect = { onSelectRoutine(ChallengeRoutine) },
                interactionSource = interactionSource
            )
        }

        Divider(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .border(1.dp, color = Gray200)
                .fillMaxWidth()
        )
    }
}

@Composable
fun RoutineDetailTabItem(
    modifier: Modifier,
    tabTitle: String,
    isSelected: Boolean = false,
    onSelect: () -> Unit,
    interactionSource: MutableInteractionSource
) {
    Column(
        modifier = modifier
            .clickable(
                onClick = onSelect,
                interactionSource = interactionSource,
                indication = null
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = tabTitle,
            color = if (isSelected) Gray700 else Gray400,
            style = if (isSelected) SoftieTypo.head4 else SoftieTypo.body2,
            modifier = Modifier
                .padding(top = 6.dp, bottom = 10.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .clip(RoundedCornerShape(9.dp))
                .background(if (isSelected) Gray650 else Gray200)
        )
    }
}

@Composable
fun SelectedRoutineContent() {
    //
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAddRoutineDetail() {
    AddRoutineDetailContent()
}