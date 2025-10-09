package com.sopetit.ui.common.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray400
import com.sopetit.design_system.SoftieTypo

@Composable
fun CommonSnackBar(
    hostState: SnackbarHostState,
    paddingBottom: Int,
    iconResource: Int,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = paddingBottom.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        CommonSnackBarContent(
            hostState = hostState,
            iconResource = iconResource
        )
    }
}

@Composable
fun CommonSnackBarContent(
    hostState: SnackbarHostState = SnackbarHostState(),
    iconResource: Int,
) {
    SnackbarHost(
        hostState = hostState,
        snackbar = { snackBarData ->
            Surface(
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
                    .clip(RoundedCornerShape(99.dp)),
                color = Gray400
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = iconResource),
                        contentDescription = "snackbar icon",
                        modifier = Modifier
                            .size(18.dp)
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = snackBarData.visuals.message,
                        style = SoftieTypo.body2,
                        color = Gray0,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                    )
                }
            }
        }
    )
}
