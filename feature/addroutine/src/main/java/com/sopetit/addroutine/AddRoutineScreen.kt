package com.sopetit.addroutine

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.designsystem.AddRoutineCustomSemiTitle
import com.sopetit.designsystem.AddRoutineCustomTitle
import com.sopetit.designsystem.AddRoutineTitle
import com.sopetit.designsystem.Gray0
import com.sopetit.designsystem.Gray200
import com.sopetit.designsystem.Gray300
import com.sopetit.designsystem.Gray400
import com.sopetit.designsystem.Gray50
import com.sopetit.designsystem.Gray500
import com.sopetit.designsystem.Gray650
import com.sopetit.designsystem.Gray700
import com.sopetit.designsystem.RoutineAllTitle
import com.sopetit.designsystem.SoftieTypo
import com.sopetit.domain.entity.response.theme.ThemeListItemModel
import com.sopetit.ui.common.topbar.TopBarContent
import com.sopetit.ui.common.type.ThemeIconType

@Composable
fun AddRoutineScreen(
    goToDetailPage: (ThemeListItemModel) -> Unit,
    goToCustomRoutinePage: () -> Unit,
    goBackToProgressPage: () -> Unit,
) {
    val viewModel: AddRoutineViewModel = hiltViewModel()
    val uiState: AddRoutinePageState by viewModel.uiState.collectAsStateWithLifecycle()

    val interactionSource = remember { MutableInteractionSource() }

    AddRoutineContent(
        routineThemeList = uiState.routineThemeList,
        onClickTheme = { goToDetailPage(it) },
        interactionSource = interactionSource,
        onClickCustomRoutine = { goToCustomRoutinePage() },
        onClickBackBtn = { goBackToProgressPage() },
    )
}

@Composable
fun AddRoutineContent(
    routineThemeList: List<ThemeListItemModel> = emptyList(),
    onClickTheme: (ThemeListItemModel) -> Unit = {},
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClickCustomRoutine: () -> Unit = {},
    onClickBackBtn: () -> Unit = {},
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Gray50),
    ) {
        TopBarContent(
            content = AddRoutineTitle,
            onClickIcon = onClickBackBtn,
            interactionSource = interactionSource,
        )

        RoutineCustomBox(
            interactionSource = interactionSource,
            onClickAction = onClickCustomRoutine,
        )

        Text(
            text = RoutineAllTitle,
            color = Gray700,
            style = SoftieTypo.head3,
            modifier =
                Modifier
                    .padding(top = 12.dp, start = 20.dp),
        )

        RoutineThemeList(
            routineThemeList = routineThemeList,
            onClickTheme = onClickTheme,
        )
    }
}

@Composable
fun RoutineCustomBox(
    onClickAction: () -> Unit,
    interactionSource: MutableInteractionSource,
) {
    Row(
        modifier =
            Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Gray650)
                .clickable(
                    onClick = onClickAction,
                    interactionSource = interactionSource,
                    indication = null,
                ),
    ) {
        Column(
            modifier =
                Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(start = 20.dp),
        ) {
            Text(
                text = AddRoutineCustomTitle,
                color = Gray300,
                style = SoftieTypo.caption1,
                modifier =
                    Modifier
                        .padding(bottom = 2.dp),
            )

            Text(
                text = AddRoutineCustomSemiTitle,
                color = Gray0,
                style = SoftieTypo.head3,
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_routine_custom),
            contentDescription = "write custom",
            modifier =
                Modifier
                    .align(Alignment.Bottom)
                    .height(76.dp),
        )

        Image(
            painter = painterResource(id = R.drawable.ic_arrow_next),
            contentDescription = "next",
            colorFilter = ColorFilter.tint(Gray400),
            modifier =
                Modifier
                    .align(Alignment.Top)
                    .padding(start = 4.dp, top = 16.dp, end = 12.dp)
                    .size(24.dp),
        )
    }
}

@Composable
fun RoutineThemeList(
    routineThemeList: List<ThemeListItemModel>,
    onClickTheme: (ThemeListItemModel) -> Unit,
) {
    LazyColumn(
        modifier =
            Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(routineThemeList) { theme ->
            RoutineThemeListItem(
                themeId = theme.themeId,
                themeTitle = theme.title,
                themeSubTitle = theme.subTitle,
                onClickAction = { onClickTheme(theme) },
            )
        }
    }
}

@Composable
fun RoutineThemeListItem(
    themeId: Int,
    themeTitle: String,
    themeSubTitle: String,
    onClickAction: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Gray0)
                .border(1.dp, color = Gray200, shape = RoundedCornerShape(10.dp))
                .clickable(
                    onClick = onClickAction,
                ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = ThemeIconType.getThemeIcon(themeId)),
            contentDescription = "theme icon",
            modifier =
                Modifier
                    .padding(start = 20.dp)
                    .size(40.dp),
        )

        Column(
            modifier =
                Modifier
                    .padding(start = 12.dp)
                    .padding(vertical = 20.dp)
                    .weight(1f),
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
                modifier =
                    Modifier
                        .padding(top = 2.dp),
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_arrow_next),
            contentDescription = "arrow next",
            modifier =
                Modifier
                    .padding(end = 12.dp)
                    .size(24.dp),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAddRoutine() {
    AddRoutineContent()
}
