package com.sopetit.data.entity.response.member

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GetMemberResponseDto(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("dollType")
    val dollType: String = "",
    @SerializedName("dailyCottonCount")
    val dailyCottonCount: Int = -1,
    @SerializedName("happinessCottonCount")
    val happinessCottonCount: Int = -1,
    @SerializedName("conversations")
    val conversations: List<String> = emptyList(),
    @SerializedName("frameImageUrl")
    val frameImageUrl: String = "",
)
