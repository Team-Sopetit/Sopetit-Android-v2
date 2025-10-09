package com.sopetit.feature.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.Gray650
import com.sopetit.design_system.SoftieTypo
import com.sopetit.feature.BottomNavType

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource,
    type: BottomNavType = BottomNavType.HOME,
    onClick: (String) -> Unit = {},
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .wrapContentHeight()
            .background(Gray0),
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        BottomNavItem(
            navIcon = BottomNavType.getBottomNavIcon(
                BottomNavType.PROGRESS,
                (type == BottomNavType.PROGRESS)
            ),
            isSelected = (type == BottomNavType.PROGRESS),
            type = BottomNavType.PROGRESS,
            onClick = onClick,
            interactionSource = interactionSource
        )

        BottomNavItem(
            navIcon = BottomNavType.getBottomNavIcon(
                BottomNavType.HOME,
                (type == BottomNavType.HOME)
            ),
            isSelected = (type == BottomNavType.HOME),
            type = BottomNavType.HOME,
            onClick = onClick,
            interactionSource = interactionSource
        )

        BottomNavItem(
            navIcon = BottomNavType.getBottomNavIcon(
                BottomNavType.ACHIEVE,
                (type == BottomNavType.ACHIEVE)
            ),
            isSelected = (type == BottomNavType.ACHIEVE),
            type = BottomNavType.ACHIEVE,
            onClick = onClick,
            interactionSource = interactionSource
        )
    }
}

@Composable
fun BottomNavItem(
    navIcon: Int = -1,
    isSelected: Boolean = false,
    type: BottomNavType = BottomNavType.DEFAULT,
    onClick: (String) -> Unit = {},
    interactionSource: MutableInteractionSource,
) {
    Column(
        modifier = Modifier
            .size(width = 52.dp, height = 50.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onClick(BottomNavType.getDestination(type)) }
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = navIcon),
            contentDescription = "nav icon",
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = type.navName,
            style = SoftieTypo.caption2,
            color = if (isSelected) Gray650 else Gray400
        )
    }
}