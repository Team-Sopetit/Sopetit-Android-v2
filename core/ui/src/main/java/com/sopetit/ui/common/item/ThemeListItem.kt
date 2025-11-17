package com.sopetit.ui.common.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.SoftieTypo

@Composable
fun ThemeListItem(
    themeName: String = "",
    themeItemIcon: Int = -1,
    onClick: () -> Unit = {},
    isSelectedTheme: Boolean = false,
    isClickEnabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }

    ThemeListItemContent(
        interactionSource = interactionSource,
        themeName = themeName,
        themeItemIcon = themeItemIcon,
        onClick = onClick,
        isSelectedTheme = isSelectedTheme,
        isClickEnabled = isClickEnabled,
    )
}

@Composable
fun ThemeListItemContent(
    themeName: String,
    themeItemIcon: Int,
    onClick: () -> Unit,
    isSelectedTheme: Boolean,
    interactionSource: MutableInteractionSource,
    isClickEnabled: Boolean,
) {
    Row(
        modifier =
            Modifier
                .wrapContentHeight()
                .clip(RoundedCornerShape(99.dp))
                .border(1.dp, if (isSelectedTheme) Gray650 else Gray200, RoundedCornerShape(99.dp))
                .background(if (isSelectedTheme) Gray200 else Gray0)
                .clickable(
                    onClick = onClick,
                    interactionSource = interactionSource,
                    indication = null,
                    enabled = isClickEnabled,
                ),
    ) {
        Image(
            painter = painterResource(id = themeItemIcon),
            contentDescription = "theme icon",
            modifier =
                Modifier
                    .padding(start = 20.dp)
                    .size(18.dp)
                    .align(Alignment.CenterVertically),
        )

        Text(
            text = themeName,
            color = if (isClickEnabled) Gray700 else Gray400,
            style = SoftieTypo.body1,
            modifier =
                Modifier
                    .padding(vertical = 15.dp)
                    .padding(start = 6.dp, end = 20.dp),
        )
    }
}
