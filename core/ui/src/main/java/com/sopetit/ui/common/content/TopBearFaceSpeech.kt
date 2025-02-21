package com.sopetit.ui.common.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.R
import com.sopetit.design_system.Red200
import com.sopetit.design_system.SoftieTypo
import com.sopetit.ui.common.type.BearType

@Composable
fun TopBearFaceSpeech(
    dollType: String,
    speechContent: String,
    isHighlightSpeechExist: Boolean = false,
    highlightSpeech: String = "",
    speechContentAfterHighlight: String = ""
) {

    TopBarFaceSpeechContent(
        dollType = dollType,
        speechContent = speechContent,
        isHighlightSpeechExist = isHighlightSpeechExist,
        highlightSpeech = highlightSpeech,
        speechContentAfterHighlight = speechContentAfterHighlight
    )
}

@Composable
fun TopBarFaceSpeechContent(
    dollType: String = "",
    speechContent: String = "",
    isHighlightSpeechExist: Boolean = false,
    highlightSpeech: String = "",
    speechContentAfterHighlight: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(top = 24.dp)
            .wrapContentHeight()
    ) {

        Image(
            painter = painterResource(id = BearType.getDollFace(dollType)),
            contentDescription = "bear face",
            modifier = Modifier
                .padding(top = 5.dp)
                .size(width = 53.dp, height = 50.dp)
        )

        Spacer(modifier = Modifier.padding(start = 14.dp))

        Text(
            text = buildAnnotatedString {
                if (isHighlightSpeechExist) {
                    append(speechContent)
                    withStyle(style = SpanStyle(color = Red200)) { append(highlightSpeech) }
                    append(speechContentAfterHighlight)
                } else append(speechContent)
            },
            textAlign = TextAlign.Center,
            color = Gray700,
            style = SoftieTypo.bubble2,
            modifier = Modifier
                .paint(painterResource(id = R.drawable.ic_speech_long))
                .padding(start = 47.dp, end = 38.dp)
                .padding(vertical = 14.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTopBarFaceSpeech() {
    TopBarFaceSpeechContent()
}