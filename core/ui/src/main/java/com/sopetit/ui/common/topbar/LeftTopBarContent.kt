package com.sopetit.ui.common.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sopetit.designsystem.Gray700
import com.sopetit.design_system.R
import com.sopetit.designsystem.SettingDeleteUser
import com.sopetit.designsystem.SoftieTypo

@Composable
fun LeftTopBarContent(
    interactionSource: MutableInteractionSource,
    onClickIcon: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "back",
            modifier =
                Modifier
                    .padding(vertical = 16.dp)
                    .padding(start = 18.dp, end = 12.dp)
                    .align(Alignment.CenterVertically)
                    .size(24.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onClickIcon,
                    ),
        )

        Text(
            text = SettingDeleteUser,
            color = Gray700,
            style = SoftieTypo.body1,
            modifier =
                Modifier
                    .align(Alignment.CenterVertically),
        )
    }
}
