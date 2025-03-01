package com.sopetit.data.entity.response.memberroutine

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GetMemberRoutineResponseDto(
    @SerializedName("routines")
    val routines: List<MemberRoutineListDto>,
) {
    @Serializable
    data class MemberRoutineListDto(
        @SerializedName("themeId")
        val themeId: Int = -1,
        @SerializedName("themeName")
        val themeName: String = "",
        @SerializedName("routines")
        val routines: List<MemberRoutineListItemDto>,
    ) {
        @Serializable
        data class MemberRoutineListItemDto(
            @SerializedName("routineId")
            val routineId: Int = -1,
            @SerializedName("content")
            val content: String = "",
            @SerializedName("achieveCount")
            val achieveCount: Int = -1,
            @SerializedName("isAchieve")
            val isAchieve: Boolean = false,
        )
    }
}