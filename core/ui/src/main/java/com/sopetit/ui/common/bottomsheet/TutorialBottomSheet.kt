package com.sopetit.ui.common.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sopetit.design_system.Gray0
import com.sopetit.design_system.Gray200
import com.sopetit.design_system.Gray500
import com.sopetit.design_system.Gray700
import com.sopetit.design_system.SoftieTypo
import com.sopetit.design_system.TutorialNextBtn
import com.sopetit.domain.entity.response.screen.TutorialModel
import com.sopetit.ui.common.button.BottomRectangleBtn

@Composable
fun TutorialBottomSheet(
    tutorials: List<TutorialModel>,
) {
    val pagerState = rememberPagerState(pageCount = { tutorials.size })

    TutorialBottomSheetContent(
        tutorials = tutorials,
        pagerState = pagerState
    )
}

@Composable
fun TutorialBottomSheetContent(
    tutorials: List<TutorialModel> = emptyList(),
    pagerState: PagerState,
) {
    val currentPage = pagerState.currentPage

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray0),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(top = 24.dp)
                .wrapContentSize()
                .clip(RoundedCornerShape(99.dp))
                .background(Gray200)
        ) {
            Text(
                text = tutorials[currentPage].bottomSheetTitle,
                color = Gray700,
                style = SoftieTypo.head3,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }

        Text(
            text = tutorials[currentPage].title,
            style = SoftieTypo.head3,
            color = Gray700,
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            text = tutorials[currentPage].semiTitle,
            style = SoftieTypo.body2,
            color = Gray500,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 10.dp)
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 30.dp),
            key = { index ->
                tutorials[index].id
            }
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = tutorials[currentPage].tutorialImg),
                    contentDescription = "tutorial",
                    modifier = Modifier.size(width = 320.dp, height = 240.dp)
                )
            }
        }

        BottomRectangleBtn(
            btnTextContent = TutorialNextBtn,
            isBtnActivated = true
        )
    }
}