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
import com.sopetit.design_system.SoftieTypo
import com.sopetit.design_system.ThemeChoiceBtn
import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.ui.common.item.BottomRectangleBtn
import com.sopetit.ui.common.topbar.OnboardingTopBar

@Composable
fun ThemeChoiceScreen(
    goBackToDollNamingPage: () -> Unit = {}
) {
    val viewModel: ThemeChoiceViewModel = hiltViewModel()
    val uiState: ThemeChoicePageState by viewModel.uiState.collectAsStateWithLifecycle()

    ThemeChoiceContent(
        onClickBackBtnAction = { goBackToDollNamingPage() },
        themeList = uiState.themeList,
        themeIconList = uiState.themeIconList,
        onSelectThemeId = { newId -> viewModel.setSelectedThemeIdList(newId)},
        selectedThemeIdList = uiState.selectedThemeIdList
    )
}

@Composable
fun ThemeChoiceContent(
    onClickBackBtnAction: () -> Unit = {},
    themeList: List<ThemeListItemModel> = emptyList(),
    themeIconList: List<Int> = emptyList(),
    onSelectThemeId: (Int) -> Unit = {},
    selectedThemeIdList: List<Int> = emptyList()
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
                ThemeChoiceList(
                    themeList = themeList,
                    themeIconList = themeIconList,
                    onSelectThemeId = onSelectThemeId
                )
            }

            BottomRectangleBtn(
                btnTextContent = ThemeChoiceBtn,
                isBtnActivated = (selectedThemeIdList.size >= 3)
            )
        }
    }
}

@Composable
fun ThemeChoiceList(
    themeList: List<ThemeListItemModel> = emptyList(),
    themeIconList: List<Int> = emptyList(),
    onSelectThemeId: (Int) -> Unit = {}
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
                themeItemIcon = themeIconList[item.themeId - 1],
                onClick = { onSelectThemeId(item.themeId) }
            )
        }
    }
}

@Composable
fun ThemeChoiceListItem(
    themeItem: ThemeListItemModel = ThemeListItemModel(),
    themeItemIcon: Int = -1,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .clip(RoundedCornerShape(99.dp))
            .border(1.dp, Gray200, RoundedCornerShape(99.dp))
            .background(Gray0)
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