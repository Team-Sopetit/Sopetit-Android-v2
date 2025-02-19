package com.sopetit.onboarding.routinechoice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.sopetit.design_system.RoutineChoiceBtn
import com.sopetit.design_system.RoutineChoiceTopOriginalSpeech
import com.sopetit.design_system.SoftieTypo
import com.sopetit.domain.entity.enums.DollType
import com.sopetit.domain.entity.request.CreateMemberModel
import com.sopetit.onboarding.model.SelectedThemeItem
import com.sopetit.ui.common.button.BottomRectangleBtn
import com.sopetit.ui.common.content.TopBearFaceSpeech
import com.sopetit.ui.common.topbar.OnboardingTopBar
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun RoutineChoiceScreen(
    goBackToThemeChoicePage: () -> Unit = {},
    memberModel: SharedFlow<CreateMemberModel> = MutableSharedFlow(),
) {
    val viewModel: RoutineChoiceViewModel = hiltViewModel()
    val uiState: RoutineChoicePageState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(memberModel) {
        memberModel.collect {
            viewModel.getMemberModel(it)
        }
    }

    RoutineChoiceContent(
        onClickBackBtnAction = { goBackToThemeChoicePage() },
        selectedDollType = uiState.memberModel.dollType,
        selectedThemeList = uiState.selectedThemeList
    )
}

@Composable
fun RoutineChoiceContent(
    onClickBackBtnAction: () -> Unit = {},
    selectedDollType: DollType = DollType.NONE,
    selectedThemeList: List<SelectedThemeItem> = emptyList()
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
                    speechContent = RoutineChoiceTopOriginalSpeech
                )

                Spacer(modifier = Modifier.height(20.dp))

                RoutineChoiceTopTheme(
                    selectedThemeList = selectedThemeList
                )
            }

            BottomRectangleBtn(
                btnTextContent = RoutineChoiceBtn,
            )
        }
    }
}

@Composable
fun RoutineChoiceTopTheme(
    selectedThemeList: List<SelectedThemeItem> = emptyList()
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
            itemsIndexed(selectedThemeList, key = { _, item -> item.themeId }) { _, item ->
                RoutineChoiceTopThemeItem(
                    title = item.title,
                    themeIcon = item.themeIcon
                )
            }
        }
    }
}

@Composable
fun RoutineChoiceTopThemeItem(
    title: String = "",
    themeIcon: Int = -1
) {
    Box(
        modifier = Modifier
            .width(106.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Gray0)
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRoutineChoice() {
    RoutineChoiceContent()
}