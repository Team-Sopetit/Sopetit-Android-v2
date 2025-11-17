package com.sopetit.achieve.stats

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.designsystem.AchieveStatEmptyTitle
import com.sopetit.designsystem.CountContent
import com.sopetit.designsystem.Dash
import com.sopetit.designsystem.Gray0
import com.sopetit.designsystem.Gray50
import com.sopetit.designsystem.Gray500
import com.sopetit.designsystem.Gray700
import com.sopetit.designsystem.Percentage
import com.sopetit.designsystem.PercentageString
import com.sopetit.design_system.R
import com.sopetit.designsystem.SoftieTypo
import com.sopetit.designsystem.StatAchieveRoutineTitle
import com.sopetit.designsystem.StatGraphEmptyTitle
import com.sopetit.designsystem.StatGraphSemiTitle
import com.sopetit.designsystem.StatGraphTitle
import com.sopetit.domain.entity.response.achieve.AchieveModel
import com.sopetit.ui.common.content.PieChart
import com.sopetit.ui.common.type.EmptyColorType
import com.sopetit.ui.common.type.ThemeIconType
import com.sopetit.ui.common.type.ThemeStatType

@Composable
fun StatScreen(
    goToAchieveRoutinePage: (Int) -> Unit,
) {
    val viewModel: StatViewModel = hiltViewModel()
    val uiState: StatPageState by viewModel.uiState.collectAsStateWithLifecycle()

    val interactionSource = remember { MutableInteractionSource() }

    StatContent(
        achieveModel = uiState.achieveModel,
        onClickAchieveRoutine = { goToAchieveRoutinePage(it) },
        interactionSource = interactionSource,
    )
}

@Composable
fun StatContent(
    achieveModel: AchieveModel = AchieveModel(),
    onClickAchieveRoutine: (Int) -> Unit = {},
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Gray50)
                .verticalScroll(rememberScrollState()),
    ) {
        StatDetailBox(
            achieveModel = achieveModel,
            onClickAchieveRoutine = onClickAchieveRoutine,
            interactionSource = interactionSource,
            isStatEmpty = (achieveModel.achievedCount == 0),
        )
    }
}

@Composable
fun StatDetailBox(
    achieveModel: AchieveModel,
    onClickAchieveRoutine: (Int) -> Unit,
    interactionSource: MutableInteractionSource,
    isStatEmpty: Boolean = true,
) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(260.dp),
    ) {
        Image(
            painter =
                painterResource(
                    id =
                        if (isStatEmpty) {
                            R.drawable.ic_stat_background_empty
                        } else {
                            ThemeStatType.getStatBackground(achieveModel.themes[0].id)
                        },
                ),
            contentDescription = "stat",
            modifier =
                Modifier
                    .fillMaxSize(),
        )

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 26.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (!isStatEmpty) {
                Text(
                    text = ThemeStatType.getStatTitle(achieveModel.themes[0].id),
                    style = SoftieTypo.head2,
                    color = Gray700,
                )
            }

            Text(
                text =
                    if (isStatEmpty) {
                        AchieveStatEmptyTitle
                    } else {
                        ThemeStatType.getStatContent(
                            achieveModel.themes[0].id,
                        )
                    },
                style = SoftieTypo.body2,
                color = Gray500,
            )
        }
    }

    if (isStatEmpty) {
        StatEmptyGraphBox()
    } else {
        StatGraphBox(
            achieveModel = achieveModel,
        )
    }

    StatRoutinesBox(
        achieveModel = achieveModel,
        onClickAchieveRoutine = onClickAchieveRoutine,
        interactionSource = interactionSource,
    )
}

@Composable
fun StatEmptyGraphBox() {
    Column(
        modifier =
            Modifier
                .padding(top = 4.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .background(Color.White)
                .clip(RoundedCornerShape(10.dp)),
    ) {
        Text(
            text = StatGraphTitle,
            style = SoftieTypo.head3,
            color = Color.Black,
            modifier =
                Modifier
                    .padding(top = 12.dp, start = 11.dp),
        )

        Text(
            text = StatGraphEmptyTitle,
            style = SoftieTypo.body2,
            color = Gray500,
            modifier =
                Modifier
                    .padding(top = 4.dp, start = 11.dp),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_graph_empty),
                contentDescription = "empty graph",
                modifier =
                    Modifier
                        .padding(top = 24.dp, bottom = 27.dp, start = 28.dp)
                        .size(143.dp),
            )

            Column(
                modifier =
                    Modifier
                        .padding(start = 24.dp, end = 29.dp)
                        .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                EmptyColorType.entries.forEach { color ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier =
                                Modifier
                                    .size(12.dp)
                                    .clip(RoundedCornerShape(2.dp))
                                    .background(color.color),
                        )

                        Text(
                            text = Dash,
                            style = SoftieTypo.caption1,
                            color = Gray500,
                            modifier =
                                Modifier
                                    .padding(start = 4.dp)
                                    .weight(1f),
                        )

                        Text(
                            text = String.format(PercentageString, Dash),
                            style = SoftieTypo.body2,
                            color = Gray700,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatGraphBox(
    achieveModel: AchieveModel,
) {
    Column(
        modifier =
            Modifier
                .padding(top = 4.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .background(Color.White)
                .clip(RoundedCornerShape(10.dp)),
    ) {
        Text(
            text = StatGraphTitle,
            style = SoftieTypo.head3,
            color = Color.Black,
            modifier =
                Modifier
                    .padding(top = 12.dp, start = 11.dp),
        )

        Text(
            text = String.format(StatGraphSemiTitle, achieveModel.themes[0].name),
            style = SoftieTypo.body2,
            color = Gray500,
            modifier =
                Modifier
                    .padding(top = 4.dp, start = 11.dp),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PieChart(
                proportions = setPieChartPortions(achieveModel),
                colors = setPieChartColor(achieveModel),
                modifier =
                    Modifier
                        .padding(top = 24.dp, bottom = 27.dp, start = 28.dp)
                        .size(143.dp),
            )

            Column(
                modifier =
                    Modifier
                        .padding(start = 24.dp, end = 29.dp)
                        .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                achieveModel.themes.forEach { theme ->
                    val percentage =
                        (theme.achievedCount.toFloat() / achieveModel.achievedCount) * 100

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier =
                                Modifier
                                    .size(12.dp)
                                    .clip(RoundedCornerShape(2.dp))
                                    .background(ThemeIconType.getThemeGraphColor(theme.id)),
                        )

                        Text(
                            text = ThemeIconType.getThemeName(theme.id),
                            style = SoftieTypo.caption1,
                            color = Gray500,
                            modifier =
                                Modifier
                                    .padding(start = 4.dp)
                                    .weight(1f),
                        )

                        Text(
                            text = String.format(Percentage, percentage.toInt()),
                            style = SoftieTypo.body2,
                            color = Gray700,
                        )
                    }
                }
            }
        }
    }
}

fun setPieChartColor(
    achieveModel: AchieveModel,
): List<Color> {
    return achieveModel.themes.map { theme ->
        ThemeIconType.getThemeGraphColor(theme.id)
    }
}

fun setPieChartPortions(
    achieveModel: AchieveModel,
): List<Float> {
    return achieveModel.themes.map { theme ->
        theme.achievedCount.toFloat() / achieveModel.achievedCount
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun StatRoutinesBox(
    achieveModel: AchieveModel,
    onClickAchieveRoutine: (Int) -> Unit,
    interactionSource: MutableInteractionSource,
) {
    Text(
        text = StatAchieveRoutineTitle,
        style = SoftieTypo.head3,
        color = Gray700,
        modifier =
            Modifier
                .padding(top = 20.dp, start = 17.dp),
    )

    Column(
        modifier =
            Modifier
                .padding(top = 8.dp, bottom = 31.dp, start = 17.dp, end = 17.dp)
                .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        ThemeIconType.entries.chunked(2).forEach { routines ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(11.dp),
            ) {
                routines.forEach { theme ->
                    BoxWithConstraints(
                        modifier = Modifier.weight(1f),
                    ) {
                        StatRoutineBoxItem(
                            theme = theme,
                            routineNum =
                                achieveModel.themes.firstOrNull { it.id == theme.themeId }?.achievedCount
                                    ?: 0,
                            onClickAction = { onClickAchieveRoutine(theme.themeId) },
                            interactionSource = interactionSource,
                        )
                    }
                }

                if (routines.size < 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun StatRoutineBoxItem(
    theme: ThemeIconType,
    routineNum: Int,
    onClickAction: () -> Unit,
    interactionSource: MutableInteractionSource,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Gray0)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClickAction,
                ),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier =
                    Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Gray50),
            ) {
                Image(
                    painter = painterResource(id = theme.themeIcon),
                    contentDescription = "theme",
                    modifier =
                        Modifier
                            .size(22.dp)
                            .align(Alignment.Center),
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = String.format(CountContent, routineNum),
                style = SoftieTypo.head2,
                color = Gray700,
            )
        }

        Text(
            text = theme.themeName,
            style = SoftieTypo.body2,
            color = Gray500,
            modifier =
                Modifier
                    .padding(start = 12.dp, bottom = 11.dp, top = 8.dp),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewStat() {
    StatContent()
}
