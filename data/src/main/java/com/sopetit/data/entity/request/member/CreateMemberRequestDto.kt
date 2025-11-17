package com.sopetit.data.entity.request.member

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CreateMemberRequestDto(
    @SerializedName("dollType")
    val dollType: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("routines")
    val routines: List<Int> = emptyList(),
)
