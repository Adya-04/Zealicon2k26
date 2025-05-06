package com.gdg.zealicon2k25.pref

import kotlinx.coroutines.flow.Flow

interface PrefDatastore{
    fun getToken():Flow<String>

    suspend fun saveToken(token: String)
}