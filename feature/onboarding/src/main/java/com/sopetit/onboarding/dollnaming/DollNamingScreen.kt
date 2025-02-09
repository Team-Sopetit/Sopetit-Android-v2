package com.sopetit.onboarding.dollnaming

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sopetit.core.enums.DollType
import com.sopetit.design_system.DollNamingBtn
import com.sopetit.design_system.DollNamingSemiTitle
import com.sopetit.design_system.DollNamingTitle
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray300
import com.sopetit.design_system.Gray50
import com.sopetit.design_system.Gray500
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.R
import com.sopetit.design_system.Softie
import com.sopetit.design_system.SoftieTypo
import com.sopetit.ui.common.item.BottomRectangleBtn
import com.sopetit.ui.common.topbar.OnboardingTopBar
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun DollNamingScreen(
    selectedDollType: SharedFlow<DollType> = MutableSharedFlow(),
    goToThemeChoicePage: () -> Unit = {},
    goBackToDollTypePage: () -> Unit = {}
) {
    val viewModel: DollNamingViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(selectedDollType) {
        selectedDollType.collect {
            viewModel.getSelectedDollType(it)
        }
    }

    DollNamingContent(
        dollHelloResource = uiState.dollHelloResource,
        dollInputName = uiState.dollInputName,
        onValueChange = { newValue -> viewModel.onValueChange(newValue) },
        onClickBtnAction = { goToThemeChoicePage() },
        onClickBackBtnAction = { goBackToDollTypePage() }
    )
}

@Composable
fun DollNamingContent(
    dollHelloResource: LottieCompositionSpec = LottieCompositionSpec.RawRes(R.raw.brown_hello),
    dollInputName: String = "",
    onValueChange: (String) -> Unit = {},
    onClickBtnAction: () -> Unit = {},
    onClickBackBtnAction: () -> Unit = {}
) {
    val composition by rememberLottieComposition(spec = dollHelloResource)
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

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
                page = 2,
                enabledGoBack = true,
                goBack = { onClickBackBtnAction() }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = DollNamingTitle,
                    style = SoftieTypo.head1,
                    color = Gray700,
                    modifier = Modifier
                        .padding(top = 28.dp),
                    textAlign = TextAlign.Center,
                    lineHeight = 25.sp
                )

                Text(
                    text = DollNamingSemiTitle,
                    style = SoftieTypo.body2,
                    color = Gray500,
                    modifier = Modifier
                        .padding(top = 4.dp)
                )
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier
                        .size(250.dp)
                        .padding(top = 4.dp)
                )

                DollNamingTextField(
                    textInput = dollInputName,
                    onValueChange = onValueChange
                )
            }

            BottomRectangleBtn(
                btnTextContent = DollNamingBtn,
                isBtnActivated = dollInputName.isNotEmpty(),
                onClickAction = onClickBtnAction
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DollNamingTextField(
    textInput: String = "",
    onValueChange: (String) -> Unit = {}
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val imeVisible = WindowInsets.isImeVisible

    LaunchedEffect(imeVisible) {
        if (!imeVisible) {
            focusManager.clearFocus()
        }
    }

    Box(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .padding(horizontal = 108.dp)
            .clip(RoundedCornerShape(99.dp))
            .border(1.dp, Gray300, RoundedCornerShape(99.dp))
            .background(Gray0)
    ) {
        BasicTextField(
            value = textInput,
            onValueChange = { input ->
                onValueChange(input)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 12.dp)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            textStyle = SoftieTypo.body2.copy(
                color = Gray700,
                textAlign = TextAlign.Center
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    if (textInput.isEmpty() && !isFocused) {
                        Text(
                            text = Softie,
                            style = SoftieTypo.body2,
                            color = Gray300,
                            textAlign = TextAlign.Center
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDollNaming() {
    DollNamingContent()
}