package com.sopetit.achieve

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.achieve.calendar.CalendarScreen
import com.sopetit.achieve.stats.StatScreen
import com.sopetit.design_system.Achieve
import com.sopetit.design_system.AchieveTabCalendar
import com.sopetit.design_system.AchieveTabStat
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.SoftieTypo

@Composable
fun AchieveScreen() {

    val viewModel: AchieveViewModel = hiltViewModel()
    val uiState: AchievePageState by viewModel.uiState.collectAsStateWithLifecycle()

    AchieveContent(
        onSelectTab = { tab -> viewModel.setSelectedTab(tab) },
        selectedTab = uiState.selectedTab
    )
}

@Composable
fun AchieveContent(
    onSelectTab: (AchieveTabType) -> Unit = {},
    selectedTab: AchieveTabType = AchieveTabType.TabStat,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
    ) {
        Text(
            text = Achieve,
            color = Gray700,
            style = SoftieTypo.head3,
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 20.dp)
        )

        AchieveTab(
            onSelectTab = onSelectTab,
            selectedTab = selectedTab
        )

        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            if (selectedTab == AchieveTabType.TabStat) {
                StatScreen()
            } else {
                CalendarScreen()
            }
        }
    }
}

@Composable
fun AchieveTab(
    onSelectTab: (AchieveTabType) -> Unit = {},
    selectedTab: AchieveTabType,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        AchieveTabItem(
            modifier = Modifier.weight(1f),
            content = AchieveTabStat,
            isSelectedTab = (selectedTab == AchieveTabType.TabStat),
            onSelect = { onSelectTab(AchieveTabType.TabStat) }
        )

        AchieveTabItem(
            modifier = Modifier.weight(1f),
            content = AchieveTabCalendar,
            isSelectedTab = (selectedTab == AchieveTabType.TabCalendar),
            onSelect = { onSelectTab(AchieveTabType.TabCalendar) }
        )
    }

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = Gray200)
    )
}

@Composable
fun AchieveTabItem(
    modifier: Modifier,
    content: String,
    isSelectedTab: Boolean,
    onSelect: () -> Unit,
) {
    Column(
        modifier = modifier
            .clickable(
                onClick = onSelect
            )
    ) {
        Text(
            text = content,
            color = if (isSelectedTab) Gray700 else Gray400,
            style = if (isSelectedTab) SoftieTypo.head4 else SoftieTypo.body2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 6.dp, bottom = 10.dp)
                .align(Alignment.CenterHorizontally)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .clip(RoundedCornerShape(9.dp))
                .background(if (isSelectedTab) Gray650 else Gray200)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAchieve() {
    AchieveContent()
}