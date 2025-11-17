package com.sopetit.data.dataStore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

private val Context.dataStore by preferencesDataStore(name = "softie_prefs")

class LocalDataStore(context: Context) {
    private val dataStore = context.dataStore

    val accessToken: Flow<String?> =
        dataStore.data
            .map { preferences ->
                preferences[ACCESS_TOKEN_KEY]
            }

    val refreshToken: Flow<String?> =
        dataStore.data
            .map { preferences ->
                preferences[REFRESH_TOKEN_KEY]
            }

    val isMemberDollExist: Flow<Boolean?> =
        dataStore.data
            .map { preferences ->
                preferences[IS_MEMBER_DOLL_EXIST]
            }

    val fcmToken: Flow<String?> =
        dataStore.data
            .map { preferences ->
                preferences[FCM_TOKEN_KEY]
            }

    suspend fun saveAccessToken(token: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = token
            Timber.d("[datastore] access token: $token")
        }
    }

    suspend fun saveRefreshToken(token: String) {
        dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN_KEY] = token
            Timber.d("[datastore] refresh token: $token")
        }
    }

    suspend fun saveIsMemberDollExist(isDollExist: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_MEMBER_DOLL_EXIST] = isDollExist
        }
    }

    suspend fun saveFcmToken(token: String) {
        dataStore.edit { preferences ->
            preferences[FCM_TOKEN_KEY] = token
            Timber.d("[datastore] fcm token: $token")
        }
    }

    suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN_KEY)
            preferences.remove(REFRESH_TOKEN_KEY)
            preferences.remove(FCM_TOKEN_KEY)
        }
    }

    companion object {
        val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
        val IS_MEMBER_DOLL_EXIST = booleanPreferencesKey("is_member_doll_exist")
        val FCM_TOKEN_KEY = stringPreferencesKey("fcm_token")
    }
}
