package com.sopetit.domain.entity.enums

enum class DollType(val value: String) {
    BROWN("BROWN"),
    GRAY("GRAY"),
    WHITE("WHITE"),
    RED("RED"),
    NONE("NONE"),
    ;

    companion object {
        fun stringToEnum(value: String): DollType =
            when (value) {
                "BROWN" -> BROWN
                "GRAY" -> GRAY
                "WHITE" -> WHITE
                "RED" -> RED
                "NONE" -> NONE
                else -> NONE
            }
    }
}
