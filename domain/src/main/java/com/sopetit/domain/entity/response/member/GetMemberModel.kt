package com.sopetit.domain.entity.response.member

data class GetMemberModel(
    val name: String = "",
    val dollType: String = "",
    val dailyCottonCount: Int = -1,
    val happinessCottonCount: Int = -1,
    val conversations: List<String> = emptyList(),
    val frameImageUrl: String = "",
)