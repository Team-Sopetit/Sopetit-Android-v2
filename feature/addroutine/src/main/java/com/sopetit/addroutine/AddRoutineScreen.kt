package com.sopetit.addroutine

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.sopetit.design_system.AddRoutineTitle
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray500
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.R
import com.sopetit.design_system.RoutineAllTitle
import com.sopetit.design_system.SoftieTypo
import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.ui.common.type.ThemeIconType

@Composable
fun AddRoutineScreen(
    goToDetailPage: (ThemeListItemModel) -> Unit
) {

    val viewModel: AddRoutineViewModel = hiltViewModel()
    val uiState: AddRoutinePageState by viewModel.uiState.collectAsStateWithLifecycle()

    AddRoutineContent(
        routineThemeList = uiState.routineThemeList,
        onClickTheme = { goToDetailPage(it) }
    )
}

@Composable
fun AddRoutineContent(
    routineThemeList: List<ThemeListItemModel> = emptyList(),
    onClickTheme: (ThemeListItemModel) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray50)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "back",
                modifier = Modifier
                    .padding(vertical = 14.dp)
                    .padding(start = 20.dp)
                    .align(Alignment.CenterStart)
                    .size(28.dp)
            )

            Text(
                text = AddRoutineTitle,
                color = Gray700,
                style = SoftieTypo.head3,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        Text(
            text = RoutineAllTitle,
            color = Gray700,
            style = SoftieTypo.head3,
            modifier = Modifier
                .padding(top = 12.dp, start = 20.dp)
        )

        RoutineThemeList(
            routineThemeList = routineThemeList,
            onClickTheme = onClickTheme
        )
    }
}

@Composable
fun RoutineThemeList(
    routineThemeList: List<ThemeListItemModel>,
    onClickTheme: (ThemeListItemModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(routineThemeList) { theme ->
            RoutineThemeListItem(
                themeId = theme.themeId,
                themeTitle = theme.title,
                themeSubTitle = theme.subTitle,
                onClickAction = { onClickTheme(theme) }
            )
        }
    }
}

@Composable
fun RoutineThemeListItem(
    themeId: Int,
    themeTitle: String,
    themeSubTitle: String,
    onClickAction: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Gray0)
            .border(1.dp, color = Gray200, shape = RoundedCornerShape(10.dp))
            .clickable(
                onClick = onClickAction
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = ThemeIconType.getThemeIcon(themeId)),
            contentDescription = "theme icon",
            modifier = Modifier
                .padding(start = 20.dp)
                .size(40.dp)
        )

        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .padding(vertical = 20.dp)
                .weight(1f)
        ) {
            Text(
                text = themeSubTitle,
                color = Gray500,
                style = SoftieTypo.caption2,
            )

            Text(
                text = themeTitle,
                color = Gray700,
                style = SoftieTypo.head3,
                modifier = Modifier
                    .padding(top = 2.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_arrow_next),
            contentDescription = "arrow next",
            modifier = Modifier
                .padding(end = 12.dp)
                .size(24.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAddRoutine() {
    AddRoutineContent()
}