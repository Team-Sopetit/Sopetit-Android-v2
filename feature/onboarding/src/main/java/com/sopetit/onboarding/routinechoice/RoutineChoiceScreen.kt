package com.sopetit.onboarding.routinechoice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.Red200
import com.sopetit.design_system.RoutineChoiceBtn
import com.sopetit.design_system.RoutineChoiceSnackBar
import com.sopetit.design_system.RoutineChoiceTopNumSpeech
import com.sopetit.design_system.RoutineChoiceTopNumSpeechAfter
import com.sopetit.design_system.RoutineChoiceTopOriginalSpeech
import com.sopetit.design_system.SoftieTypo
import com.sopetit.domain.entity.enums.DollType
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.domain.entity.response.routine.DailyRoutineListItemModel
import com.sopetit.design_system.R
import com.sopetit.onboarding.model.SelectedThemeItem
import com.sopetit.ui.common.button.BottomRectangleBtn
import com.sopetit.ui.common.content.TopBearFaceSpeech
import com.sopetit.ui.common.item.DailyRoutineListItem
import com.sopetit.ui.common.topbar.OnboardingTopBar
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun RoutineChoiceScreen(
    goBackToThemeChoicePage: () -> Unit = {},
    memberModel: SharedFlow<CreateMemberModel> = MutableSharedFlow(),
    showSnackBar: (String, Int, Int) -> Unit,
    goToHomePage: () -> Unit = {}
) {
    val viewModel: RoutineChoiceViewModel = hiltViewModel()
    val uiState: RoutineChoicePageState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(memberModel) {
        memberModel.collect {
            viewModel.getMemberModel(it)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is RoutineChoiceEvent.OnSuccessCreateMember -> {
                    goToHomePage()
                }
                is RoutineChoiceEvent.IsOverRoutineNumSize -> {
                    showSnackBar(RoutineChoiceSnackBar, 104, R.drawable.ic_snackbar_caution)
                }
            }
        }
    }

    RoutineChoiceContent(
        onClickBackBtnAction = { goBackToThemeChoicePage() },
        selectedDollType = uiState.memberModel.dollType,
        chipThemeList = uiState.chipThemeList,
        selectedRoutineNumForTheme = uiState.selectedRoutineNumForTheme,
        onSelectThemeId = { viewModel.setSelectedThemeId(it) },
        selectedThemeId = uiState.selectedThemeId,
        isAfterRoutineSelect = uiState.isAfterRoutineSelect,
        eachThemeRoutineList = uiState.eachThemeRoutineList,
        onSelectRoutine = { viewModel.setSelectedRoutineIdList(it) },
        selectedRoutineIdList = uiState.selectedRoutineIdList,
        onClickBtnAction = { viewModel.createMember() }
    )
}

@Composable
fun RoutineChoiceContent(
    onClickBackBtnAction: () -> Unit = {},
    selectedDollType: DollType = DollType.NONE,
    chipThemeList: List<SelectedThemeItem> = emptyList(),
    selectedRoutineNumForTheme: List<Int> = listOf(0, 0, 0),
    onSelectThemeId: (Int) -> Unit = {},
    selectedThemeId: Int = -1,
    isAfterRoutineSelect: Boolean = false,
    eachThemeRoutineList: List<DailyRoutineListItemModel> = emptyList(),
    onSelectRoutine: (Int) -> Unit = {},
    selectedRoutineIdList: List<Int> = emptyList(),
    onClickBtnAction: () -> Unit = {}
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            OnboardingTopBar(
                page = 4,
                enabledGoBack = true,
                goBack = { onClickBackBtnAction() }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopBearFaceSpeech(
                    dollType = selectedDollType.value,
                    speechContent = if (isAfterRoutineSelect) RoutineChoiceTopNumSpeech else RoutineChoiceTopOriginalSpeech,
                    isHighlightSpeechExist = isAfterRoutineSelect,
                    highlightSpeech = "${selectedRoutineIdList.size}/3",
                    highlightColor = Red200,
                    speechContentAfterHighlight = RoutineChoiceTopNumSpeechAfter
                )

                Spacer(modifier = Modifier.height(20.dp))

                RoutineChoiceTopTheme(
                    chipThemeList = chipThemeList,
                    selectedRoutineNumForTheme = selectedRoutineNumForTheme,
                    onSelectThemeId = onSelectThemeId,
                    selectedThemeId = selectedThemeId
                )

                RoutineChoiceForThemeContent(
                    routineList = eachThemeRoutineList,
                    onSelectRoutine = onSelectRoutine,
                    selectedRoutineIdList = selectedRoutineIdList
                )
            }

            BottomRectangleBtn(
                btnTextContent = RoutineChoiceBtn,
                isBtnActivated = (selectedRoutineIdList.size == 3),
                onClickAction = onClickBtnAction
            )
        }
    }
}

@Composable
fun RoutineChoiceTopTheme(
    chipThemeList: List<SelectedThemeItem> = emptyList(),
    selectedRoutineNumForTheme: List<Int> = listOf(0, 0, 0),
    onSelectThemeId: (Int) -> Unit = {},
    selectedThemeId: Int = -1
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Gray200)
            .wrapContentSize(Alignment.Center)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 4.dp)
        ) {
            itemsIndexed(chipThemeList, key = { _, item -> item.themeId }) { index, item ->
                RoutineChoiceTopThemeItem(
                    title = if (selectedRoutineNumForTheme[index] == 0) item.title else "${item.title} ${selectedRoutineNumForTheme[index]}",
                    themeIcon = item.themeIcon,
                    onClick = { onSelectThemeId(item.themeId) },
                    isSelectedTheme = selectedThemeId == item.themeId
                )
            }
        }
    }
}

@Composable
fun RoutineChoiceTopThemeItem(
    title: String = "",
    themeIcon: Int = -1,
    onClick: () -> Unit = {},
    isSelectedTheme: Boolean = false
) {
    Box(
        modifier = Modifier
            .width(106.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(if (isSelectedTheme) Gray0 else Gray200)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = themeIcon),
                contentDescription = "theme icon",
                modifier = Modifier
                    .size(16.dp)
            )

            Text(
                text = title,
                color = Gray700,
                style = SoftieTypo.body2,
                modifier = Modifier
                    .padding(start = 2.dp)
            )
        }
    }
}

@Composable
fun RoutineChoiceForThemeContent(
    routineList: List<DailyRoutineListItemModel> = emptyList(),
    onSelectRoutine: (Int) -> Unit = {},
    selectedRoutineIdList: List<Int> = emptyList()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, bottom = 29.dp)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        itemsIndexed(routineList, key = { index, item -> item.routineId }) { index, item ->
            DailyRoutineListItem(
                routineContent = item.content,
                onClickAction = { onSelectRoutine(item.routineId) },
                isRoutineSelected = selectedRoutineIdList.contains(item.routineId)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRoutineChoice() {
    RoutineChoiceContent()
}