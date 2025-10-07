package com.sopetit.data.base

object EndPoints {

    object Auth {
        const val AUTH = "/api/v1/auth"
        const val REISSUE = "$AUTH/token"
        const val LOGOUT = "$AUTH/logout"
    }

    object Theme {
        const val THEME = "/api/v2/themes"
    }

    object Routine {
        const val ROUTINE = "api/v2/routines/daily"
        const val DAILYTYHEMEROUTINE = "$ROUTINE/theme/{themeId}"
        const val CHALLENGE = "/api/v2/challenges"
    }

    object Member {
        const val MEMBER = "/api/v1/members"
        const val FCM = "$MEMBER/fcm"
        const val COTTON = "$MEMBER/cotton/{cottonType}"
    }

    object MemberRoutine {
        const val MEMBERROUTINE = "/api/v1/routines/daily/member"
        const val MEMBERROUTINE2 = "/api/v2/routines/daily/member"

        const val ROUTINEACHIEVE = "$MEMBERROUTINE/routine/{routineId}"
        const val ROUTINEHISTORY = "$MEMBERROUTINE/history/{historyId}"
    }

    object MemberChallenge {
        const val MEMBERCHALLENGE = "/api/v2/members/challenges"
        const val CHALLENGEACHIEVE = "$MEMBERCHALLENGE/achievement"
        const val ROUTINEHISTORY = "$MEMBERCHALLENGE/history/{historyId}"
    }

    object Calendar {
        const val CALENDAR = "/api/v3/calendar"
    }

    object Memo {
        const val MEMOWRITE = "/api/v3/memos"
        const val ACTION = "$MEMOWRITE/{memoId}"
    }

    object Achievement {
        const val ACHIEVE = "/api/v3/achievement/themes"
        const val ACHIEVEROUTINE = "$ACHIEVE/{themeId}/routines"
    }

    object CustomRoutine {
        const val CREATE = "/api/v1/routines/custom"
        const val MODIFY = "$CREATE/{customRoutineId}"
    }
}