package com.sopetit.domain.entity.enums

enum class RoutineType(val typeName: String) {
    Daily("데일리"),
    Challenge("챌린지"),
    Custom("커스텀");

    companion object {
        fun getType(isChallenge: Boolean): RoutineType =
            if (isChallenge) Challenge else Daily
    }
}