package com.sopetit.ui.common.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.R
import com.sopetit.design_system.SoftieTypo

@Composable
fun TopBarContent(
    content: String,
    onClickIcon: () -> Unit,
    interactionSource: MutableInteractionSource
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "back",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(vertical = 14.dp, horizontal = 20.dp)
                .size(28.dp)
                .clickable(
                    onClick = onClickIcon,
                    interactionSource = interactionSource,
                    indication = null
                )
        )

        Text(
            text = content,
            color = Gray700,
            style = SoftieTypo.head3,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}