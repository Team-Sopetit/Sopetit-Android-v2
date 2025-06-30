package com.sopetit.domain.entity.response.memo

import com.sopetit.domain.entity.enums.BottomSheetActionType

data class MemoActionModel (
    val type: BottomSheetActionType = BottomSheetActionType.Delete,
    val memoId: Int = 0,
    val content: String = ""
)