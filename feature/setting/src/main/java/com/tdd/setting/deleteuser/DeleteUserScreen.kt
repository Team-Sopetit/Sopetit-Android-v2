package com.tdd.setting.deleteuser

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray100
import com.sopetit.design_system.Gray300
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.R
import com.sopetit.design_system.Red200
import com.sopetit.design_system.SettingDeleteSpeechText
import com.sopetit.design_system.SettingDeleteUserBtn
import com.sopetit.design_system.SettingDeleteUserSemiTitle
import com.sopetit.design_system.SettingDeleteUserTitleAfterWord
import com.sopetit.design_system.SettingDeleteUserTitleBeforeWord
import com.sopetit.design_system.SettingDeleteUserTitleWord
import com.sopetit.design_system.SettingNotDeleteUserBtn
import com.sopetit.design_system.SoftieTypo
import com.sopetit.ui.common.button.BottomTwoBtn
import com.sopetit.ui.common.topbar.LeftTopBarContent
import com.sopetit.ui.common.type.BearType

@Composable
fun DeleteUserScreen(
    goBackPage: () -> Unit,
    goBackToLogInPage: () -> Unit,
) {
    val viewModel: DeleteUserViewModel = hiltViewModel()
    val uiState: DeleteUserPageState by viewModel.uiState.collectAsStateWithLifecycle()

    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is DeleteUserEvent.GoBackToLogInPage -> {
                    goBackToLogInPage()
                }
            }
        }
    }

    DeleteUserContent(
        interactionSource = interactionSource,
        onClickBackBtn = { goBackPage() },
        onClickDeleteUser = { viewModel.deleteUser() },
        dollType = uiState.dollType
    )
}

@Composable
fun DeleteUserContent(
    interactionSource: MutableInteractionSource,
    onClickBackBtn: () -> Unit,
    onClickDeleteUser: () -> Unit,
    dollType: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray0)
    ) {
        LeftTopBarContent(
            interactionSource = interactionSource,
            onClickIcon = onClickBackBtn
        )

        Text(
            text = buildAnnotatedString {
                append(SettingDeleteUserTitleBeforeWord)
                withStyle(style = SpanStyle(color = Red200)) { append(SettingDeleteUserTitleWord) }
                append(SettingDeleteUserTitleAfterWord)
            },
            color = Gray700,
            style = SoftieTypo.head1,
            modifier = Modifier
                .padding(top = 60.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = SettingDeleteUserSemiTitle,
            color = Gray400,
            style = SoftieTypo.body2,
            modifier = Modifier
                .padding(top = 12.dp, bottom = 74.dp)
                .align(Alignment.CenterHorizontally)
        )

        DeleteUserImageBox(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            dollType = dollType
        )

        Spacer(modifier = Modifier.weight(1f))

        BottomTwoBtn(
            onClickLeftBtn = onClickBackBtn,
            onClickRightBtn = onClickDeleteUser,
            leftColor = Gray100,
            rightColor = Red200,
            leftContent = SettingNotDeleteUserBtn,
            rightContent = SettingDeleteUserBtn,
            leftBtnTextColor = Gray300,
            rightBtnTextColor = Gray0,
            iconVisible = false
        )

        Spacer(modifier = Modifier.padding(bottom = 35.dp))
    }
}

@Composable
fun DeleteUserImageBox(
    modifier: Modifier,
    dollType: String,
) {
    Box(
        modifier = modifier
            .wrapContentSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_home_speech),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Text(
            text = SettingDeleteSpeechText,
            color = Gray700,
            style = SoftieTypo.bubble1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 12.dp, bottom = 20.dp, start = 43.dp, end = 43.dp)
        )
    }

    Image(
        painter = painterResource(id = BearType.getDollCrying(dollType)),
        contentDescription = "crying doll",
        modifier = modifier
            .padding(top = 30.dp)
            .size(208.dp)
    )
}