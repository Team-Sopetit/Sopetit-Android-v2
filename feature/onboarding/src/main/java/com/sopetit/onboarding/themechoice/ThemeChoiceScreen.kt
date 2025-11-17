package com.sopetit.onboarding.themechoice

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.designsystem.Gray1000
import com.sopetit.designsystem.Gray50
import com.sopetit.designsystem.ThemeChoiceBtn
import com.sopetit.designsystem.ThemeChoiceSpeechHighLight
import com.sopetit.designsystem.ThemeChoiceTopOriginalSpeech
import com.sopetit.designsystem.ThemeChoiceTopOriginalSpeechAfter
import com.sopetit.designsystem.ThemeChoiceTopSpeech
import com.sopetit.domain.entity.enums.DollType
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.ui.common.button.BottomRectangleBtn
import com.sopetit.ui.common.content.TopBearFaceSpeech
import com.sopetit.ui.common.item.ThemeListItem
import com.sopetit.ui.common.topbar.OnboardingTopBar
import com.sopetit.ui.common.type.ThemeIconType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun ThemeChoiceScreen(
    memberModel: SharedFlow<CreateMemberModel> = MutableSharedFlow(),
    goBackToDollNamingPage: () -> Unit = {},
    goToRoutineChoicePage: (CreateMemberModel) -> Unit = {},
) {
    val viewModel: ThemeChoiceViewModel = hiltViewModel()
    val uiState: ThemeChoicePageState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(memberModel) {
        memberModel.collect {
            viewModel.getMemberModel(it)
        }
    }

    ThemeChoiceContent(
        dollName = uiState.memberModel.dollName,
        isFirstChoicePage = uiState.isFirstChoicePage,
        onClickFirstPage = { viewModel.clickFirstPage() },
        onClickBackBtnAction = { goBackToDollNamingPage() },
        selectedDollType = uiState.memberModel.dollType,
        themeList = uiState.themeList,
        onSelectThemeId = { newId -> viewModel.setSelectedThemeIdList(newId) },
        selectedThemeIdList = uiState.selectedThemeIdList,
        onClickBtnAction = {
            goToRoutineChoicePage(viewModel.updateMemberModel())
        }
    )
}

@Composable
fun ThemeChoiceContent(
    dollName: String = "",
    isFirstChoicePage: Boolean = true,
    onClickFirstPage: () -> Unit = {},
    onClickBackBtnAction: () -> Unit = {},
    selectedDollType: DollType = DollType.NONE,
    themeList: List<ThemeListItemModel> = emptyList(),
    onSelectThemeId: (Int) -> Unit = {},
    selectedThemeIdList: List<Int> = emptyList(),
    onClickBtnAction: () -> Unit = {},
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
                page = 3,
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
                    speechContent = ThemeChoiceTopSpeech
                )

                ThemeChoiceList(
                    themeList = themeList,
                    onSelectThemeId = onSelectThemeId,
                    selectedThemeIdList = selectedThemeIdList
                )
            }

            BottomRectangleBtn(
                btnTextContent = ThemeChoiceBtn,
                isBtnActivated = (selectedThemeIdList.size >= 3),
                onClickAction = onClickBtnAction
            )
        }

        if (isFirstChoicePage) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray1000)
                    .clickable { onClickFirstPage() }
            ) {
                Box(modifier = Modifier.padding(top = 65.dp)) {
                    TopBearFaceSpeech(
                        dollType = selectedDollType.value,
                        speechContent = ThemeChoiceTopOriginalSpeech,
                        isHighlightSpeechExist = isFirstChoicePage,
                        highlightSpeech = dollName,
                        highlightColor = ThemeChoiceSpeechHighLight,
                        speechContentAfterHighlight = ThemeChoiceTopOriginalSpeechAfter
                    )
                }
            }
        }
    }
}

@Composable
fun ThemeChoiceList(
    themeList: List<ThemeListItemModel> = emptyList(),
    onSelectThemeId: (Int) -> Unit = {},
    selectedThemeIdList: List<Int> = emptyList(),
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(themeList, key = { _, item -> item.themeId }) { _, item ->
            ThemeListItem(
                themeName = item.title,
                themeItemIcon = ThemeIconType.getThemeIcon(item.themeId),
                onClick = { onSelectThemeId(item.themeId) },
                isSelectedTheme = selectedThemeIdList.contains(item.themeId)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewThemeChoice() {
    ThemeChoiceContent()
}