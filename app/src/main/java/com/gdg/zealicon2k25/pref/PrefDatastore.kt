package com.gdg.zealicon2k25.pref

import kotlinx.coroutines.flow.Flow

interface PrefDatastore{
    fun getToken():Flow<String>

    suspend fun saveToken(token: String)

    fun getAccessToken(): Flow<String>

    suspend fun saveAccessToken(token: String)

    fun getRefreshToken(): Flow<String>

    suspend fun saveRefreshToken(token: String)

    fun getZealId(): Flow<String>

    suspend fun saveZealId(zealId: String)
}