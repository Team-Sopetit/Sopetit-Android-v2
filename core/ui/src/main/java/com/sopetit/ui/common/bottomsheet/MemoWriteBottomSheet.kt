package com.sopetit.ui.common.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.CompleteShort
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.MemoHintContent
import com.sopetit.design_system.MemoTitle
import com.sopetit.design_system.MemoWriteNumber
import com.sopetit.design_system.Num
import com.sopetit.design_system.Red200
import com.sopetit.design_system.SoftieTypo
import com.sopetit.ui.common.button.BottomRectangleBtn

@Composable
fun MemoWriteBottomSheet(
    onClickConfirmBtn: (String) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    var memoInput by remember { mutableStateOf("") }

    MemoWriteContent(
        interactionSource = interactionSource,
        memoInput = memoInput,
        onValueChange = { memoInput = it },
        onClickCompleteBtn = { onClickConfirmBtn(memoInput) }
    )
}

@Composable
fun MemoWriteContent(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    memoInput: String = "",
    onValueChange: (String) -> Unit = {},
    onClickCompleteBtn: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray0),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = MemoTitle,
                color = Gray700,
                style = SoftieTypo.head4,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .align(Alignment.Center)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Gray700)) { append(memoInput.length.toString()) }
                    append(MemoWriteNumber)
                },
                color = Gray400,
                style = SoftieTypo.body2,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .align(Alignment.CenterEnd)
            )
        }

        MemoWriteBox(
            textInput = memoInput,
            onValueChange = onValueChange
        )

        BottomRectangleBtn(
            btnTextContent = CompleteShort,
            isBtnActivated = memoInput.isNotEmpty(),
            onClickAction = onClickCompleteBtn
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MemoWriteBox(
    textInput: String,
    onValueChange: (String) -> Unit,
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
            .padding(top = 16.dp, bottom = 32.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .height(132.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Gray200)
    ) {
        BasicTextField(
            value = textInput,
            onValueChange = { input ->
                onValueChange(input)
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            textStyle = SoftieTypo.body2.copy(
                color = Gray700,
                textAlign = TextAlign.Start
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
                            text = MemoHintContent,
                            style = SoftieTypo.body2,
                            color = Gray400
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}