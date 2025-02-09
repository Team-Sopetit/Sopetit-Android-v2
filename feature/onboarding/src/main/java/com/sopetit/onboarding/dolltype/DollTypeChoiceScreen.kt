package com.sopetit.onboarding.dolltype

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.design_system.DollTypeChoiceBtn
import com.sopetit.design_system.DollTypeChoiceSemiTitle
import com.sopetit.design_system.DollTypeChoiceTitle
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray500
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.SoftieTypo
import com.sopetit.domain.entity.enums.DollType
import com.sopetit.onboarding.model.DollTypeModel
import com.sopetit.ui.common.item.BottomRectangleBtn
import com.sopetit.ui.common.topbar.OnboardingTopBar

@Composable
fun DollTypeChoiceScreen(
    goToDollNamingPage: (DollType) -> Unit = {}
) {

    val viewModel: DollTypeChoiceViewModel = hiltViewModel()
    val uiState: DollTypeChoicePageState by viewModel.uiState.collectAsStateWithLifecycle()

    DollTypeChoiceContent(
        dollTypeList = uiState.dollTypeList,
        selectedDollType = uiState.selectedDollType,
        onSelectDollType = { dollType ->
            viewModel.setSelectedDollType(dollType)
        },
        onClickBtnAction = {
            goToDollNamingPage(uiState.selectedDollType)
        }
    )
}

@Composable
fun DollTypeChoiceContent(
    dollTypeList: List<DollTypeModel> = emptyList(),
    onSelectDollType: (DollType) -> Unit = {},
    selectedDollType: DollType = DollType.NONE,
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
                page = 1,
                enabledGoBack = false
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = DollTypeChoiceTitle,
                    style = SoftieTypo.head1,
                    color = Gray700,
                    modifier = Modifier
                        .padding(top = 28.dp)
                )

                Text(
                    text = DollTypeChoiceSemiTitle,
                    style = SoftieTypo.body2,
                    color = Gray500,
                    modifier = Modifier
                        .padding(top = 4.dp)
                )

                DollTypeChoiceItem(
                    dollTypeList = dollTypeList,
                    selectedDollType = selectedDollType,
                    onSelectDollType = onSelectDollType
                )
            }

            BottomRectangleBtn(
                btnTextContent = DollTypeChoiceBtn,
                isBtnActivated = (selectedDollType != DollType.NONE),
                onClickAction = onClickBtnAction
            )
        }
    }
}

@Composable
fun DollTypeChoiceItem(
    dollTypeList: List<DollTypeModel> = emptyList(),
    onSelectDollType: (DollType) -> Unit = {},
    selectedDollType: DollType = DollType.NONE
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 97.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            itemsIndexed(dollTypeList, key = { _, item -> item.id }) { _, item ->
                Image(
                    painter = painterResource(id = if (selectedDollType == item.dollType) item.dollUpBox else item.dollInBox),
                    contentDescription = "bear type",
                    modifier = Modifier
                        .size(160.dp)
                        .align(Alignment.Center)
                        .clickable { onSelectDollType(item.dollType) }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDollTypeChoice() {
    DollTypeChoiceContent()
}