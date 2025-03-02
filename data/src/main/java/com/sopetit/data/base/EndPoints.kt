package com.sopetit.data.base

object EndPoints {

    object Auth {
        private const val AUTH = "/api/v1/auth"
        const val LOGIN = AUTH
    }

    object Theme {
        const val THEME = "/api/v2/themes"
    }

    object Routine {
        const val ROUTINE = "api/v2/routines/daily"
    }

    object Member {
        const val MEMBER = "/api/v1/members"
    }

    object MemberRoutine {
        const val MEMBERROUTINE = "/api/v2/routines/daily/member"
    }

    object MemberChallenge {
        const val MEMBERCHALLENGE = "/api/v2/members/challenges"
    }
}