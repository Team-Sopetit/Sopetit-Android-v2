package com.tdd.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.R
import com.sopetit.design_system.SettingAlarm
import com.sopetit.design_system.SettingDeleteUser
import com.sopetit.design_system.SettingLogOut
import com.sopetit.design_system.SettingPersonalInfo
import com.sopetit.design_system.SettingService
import com.sopetit.design_system.SettingTitle
import com.sopetit.design_system.SettingUserFeedback
import com.sopetit.design_system.SettingVersion
import com.sopetit.design_system.SoftieTypo
import com.sopetit.ui.common.model.TwoBtnIconModel
import com.sopetit.ui.common.topbar.TopBarContent
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun SettingScreen(
    goBackPage: () -> Unit,
    goToDeleteUserScreen: () -> Unit,
    showLogOutBottomSheet: (TwoBtnIconModel) -> Unit,
    isSelectedLogOut: SharedFlow<Boolean>,
    goBackToLogInPage: () -> Unit,
) {
    val viewModel: SettingViewModel = hiltViewModel()
    val uiState: SettingPageState by viewModel.uiState.collectAsStateWithLifecycle()

    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is SettingEvent.GoBackToLogInPage -> {
                    goBackToLogInPage()
                }
            }
        }
    }

    LaunchedEffect(isSelectedLogOut) {
        isSelectedLogOut.collect {
            viewModel.postLogOut(it)
        }
    }

    SettingContent(
        interactionSource = interactionSource,
        onClickBackBtn = { goBackPage() },
        onClickDeleteUser = { goToDeleteUserScreen() },
        onClickLogOut = { showLogOutBottomSheet(uiState.logOutModel) }
    )
}

@Composable
fun SettingContent(
    interactionSource: MutableInteractionSource,
    onClickBackBtn: () -> Unit,
    onClickDeleteUser: () -> Unit,
    onClickLogOut: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray0)
    ) {
        TopBarContent(
            content = SettingTitle,
            interactionSource = interactionSource,
            onClickIcon = onClickBackBtn
        )

        SettingBarItem(
            icon = R.drawable.ic_setting_alarm,
            content = SettingAlarm,
            onClickAction = { /*TODO*/ },
            interactionSource = interactionSource
        )

        SettingBarItem(
            icon = R.drawable.ic_setting_personal,
            content = SettingPersonalInfo,
            onClickAction = { /*TODO*/ },
            interactionSource = interactionSource
        )

        Divider(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .border(1.dp, Gray50)
        )

        SettingBarItem(
            icon = R.drawable.ic_setting_service,
            content = SettingService,
            onClickAction = { /*TODO*/ },
            interactionSource = interactionSource
        )

        SettingCommonDivider()

        SettingBarItem(
            icon = R.drawable.ic_setting_feedback,
            content = SettingUserFeedback,
            onClickAction = { /*TODO*/ },
            interactionSource = interactionSource
        )

        SettingCommonDivider()

        Text(
            text = SettingVersion,
            color = Gray700,
            style = SoftieTypo.body1,
            modifier = Modifier
                .padding(vertical = 14.dp, horizontal = 20.dp)
        )

        SettingCommonDivider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = onClickLogOut,
                    indication = null,
                    interactionSource = interactionSource
                )
        ) {
            Text(
                text = SettingLogOut,
                color = Gray400,
                style = SoftieTypo.body2,
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 20.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = onClickDeleteUser,
                    indication = null,
                    interactionSource = interactionSource
                )
        ) {
            Text(
                text = SettingDeleteUser,
                color = Gray400,
                style = SoftieTypo.body2.copy(textDecoration = TextDecoration.Underline),
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 20.dp)
            )
        }
    }
}

@Composable
fun SettingCommonDivider() {
    Divider(
        modifier = Modifier
            .border(8.dp, Gray50)
    )
}

@Composable
fun SettingBarItem(
    icon: Int,
    content: String,
    onClickAction: () -> Unit,
    interactionSource: MutableInteractionSource,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClickAction,
                interactionSource = interactionSource,
                indication = null
            )
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "setting bar icon",
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(start = 20.dp, end = 3.dp)
                .align(Alignment.CenterVertically)
                .size(19.dp)
        )

        Text(
            text = content,
            color = Gray700,
            style = SoftieTypo.body1,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_arrow_next),
            contentDescription = "go setting",
            modifier = Modifier
                .padding(vertical = 14.dp, horizontal = 20.dp)
                .size(23.dp)
                .align(Alignment.CenterVertically)
        )
    }
}