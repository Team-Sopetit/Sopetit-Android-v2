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
import com.sopetit.core.enums.DollType
import com.sopetit.design_system.DollTypeChoiceBtn
import com.sopetit.design_system.DollTypeChoiceSemiTitle
import com.sopetit.design_system.DollTypeChoiceTitle
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray500
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.R
import com.sopetit.design_system.SoftieTypo
import com.sopetit.ui.common.item.BottomRectangleBtn
import com.sopetit.ui.common.topbar.OnboardingTopBar

@Composable
fun DollTypeChoiceScreen(
    goToDollNamingPage: () -> Unit = {}
) {

    val viewModel: DollTypeChoiceViewModel = hiltViewModel()
    val uiState: DollTypeChoicePageState by viewModel.uiState.collectAsStateWithLifecycle()

    DollTypeChoiceContent(
        selectedDollType = uiState.selectedDollType,
        onSelectDollType = { dollType ->
            viewModel.setSelectedDollType(dollType)
        },
        onClickBtnAction = { goToDollNamingPage() }
    )
}

@Composable
fun DollTypeChoiceContent(
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
            OnboardingTopBar(page = 2)

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
    onSelectDollType: (DollType) -> Unit = {},
    selectedDollType: DollType = DollType.NONE
) {

    val dollInBoxImgList: List<Int> = listOf(
        R.drawable.ic_doll_brown_box_in,
        R.drawable.ic_doll_gray_box_in,
        R.drawable.ic_doll_white_box_in,
        R.drawable.ic_doll_red_box_in
    )
    val dollUpBoxImgList: List<Int> = listOf(
        R.drawable.ic_doll_brown_box_up,
        R.drawable.ic_doll_gray_box_up,
        R.drawable.ic_doll_white_box_up,
        R.drawable.ic_doll_red_box_up
    )
    val dollTypeList: List<DollType> =
        listOf(DollType.BROWN, DollType.GRAY, DollType.WHITE, DollType.RED)

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
            itemsIndexed(dollTypeList, key = { _, item -> item }) { index, item ->
                Image(
                    painter = painterResource(id = if (selectedDollType == item) dollUpBoxImgList[index] else dollInBoxImgList[index]),
                    contentDescription = "bear type",
                    modifier = Modifier
                        .size(160.dp)
                        .align(Alignment.Center)
                        .clickable { onSelectDollType(item) }
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