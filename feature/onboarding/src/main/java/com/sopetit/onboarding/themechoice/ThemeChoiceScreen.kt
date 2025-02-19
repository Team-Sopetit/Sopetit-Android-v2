package com.sopetit.onboarding.themechoice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
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
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.SoftieTypo
import com.sopetit.design_system.ThemeChoiceBtn
import com.sopetit.domain.entity.enums.DollType
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.ui.common.button.BottomRectangleBtn
import com.sopetit.ui.common.content.TopBearFaceSpeech
import com.sopetit.ui.common.topbar.OnboardingTopBar
import com.sopetit.ui.common.type.ThemeIconType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

@Composable
fun ThemeChoiceScreen(
//    selectedDollType: SharedFlow<DollType> = MutableSharedFlow(),
    memberModel: SharedFlow<CreateMemberModel> = MutableSharedFlow(),
//    selectedDollType: DollType = DollType.NONE,
    goBackToDollNamingPage: () -> Unit = {},
    goToRoutineChoicePage: (CreateMemberModel) -> Unit = {}
) {
    val viewModel: ThemeChoiceViewModel = hiltViewModel()
    val uiState: ThemeChoicePageState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(memberModel) {
        memberModel.collect {
//            viewModel.getSelectedDollType(it)
            viewModel.getMemberModel(it)
        }
    }

    ThemeChoiceContent(
        onClickBackBtnAction = { goBackToDollNamingPage() },
        selectedDollType = uiState.memberModel.dollType,
//        selectedDollType = selectedDollType,
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
    onClickBackBtnAction: () -> Unit = {},
    selectedDollType: DollType = DollType.NONE,
    themeList: List<ThemeListItemModel> = emptyList(),
    onSelectThemeId: (Int) -> Unit = {},
    selectedThemeIdList: List<Int> = emptyList(),
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
                    dollType = selectedDollType.value
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
    }
}

@Composable
fun ThemeChoiceList(
    themeList: List<ThemeListItemModel> = emptyList(),
    onSelectThemeId: (Int) -> Unit = {},
    selectedThemeIdList: List<Int> = emptyList()
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(themeList, key = { _, item -> item.themeId }) { _, item ->
            ThemeChoiceListItem(
                themeItem = item,
                themeItemIcon = ThemeIconType.getThemeIcon(item.themeId),
                onClick = { onSelectThemeId(item.themeId) },
                isSelectedTheme = selectedThemeIdList.contains(item.themeId)
            )
        }
    }
}

@Composable
fun ThemeChoiceListItem(
    themeItem: ThemeListItemModel = ThemeListItemModel(),
    themeItemIcon: Int = -1,
    onClick: () -> Unit = {},
    isSelectedTheme: Boolean = false
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .clip(RoundedCornerShape(99.dp))
            .border(1.dp, if (isSelectedTheme) Gray650 else Gray200, RoundedCornerShape(99.dp))
            .background(if (isSelectedTheme) Gray200 else Gray0)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = themeItemIcon),
            contentDescription = "theme icon",
            modifier = Modifier
                .padding(start = 20.dp)
                .size(18.dp)
                .align(Alignment.CenterVertically)
        )

        Text(
            text = themeItem.title,
            color = Gray700,
            style = SoftieTypo.body1,
            modifier = Modifier
                .padding(vertical = 15.dp)
                .padding(start = 6.dp, end = 20.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewThemeChoice() {
    ThemeChoiceContent()
}