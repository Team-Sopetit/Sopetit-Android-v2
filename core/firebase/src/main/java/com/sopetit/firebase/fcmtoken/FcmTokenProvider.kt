package com.sopetit.firebase.fcmtoken

interface FcmTokenProvider {
    suspend fun getFcmToken(): String
}
