package com.gdg.zealicon2k25.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.gdg.zealicon2k25.utils.Constants.INIT_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PrefDatastoreImpl @Inject constructor(private val datastore: DataStore<Preferences>):
    PrefDatastore {
    override fun getToken(): Flow<String> {
        return datastore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[stringPreferencesKey(INIT_TOKEN)]?:"Default_init"
        }
    }

    override suspend fun saveToken(token: String) {
        datastore.edit {
            it[stringPreferencesKey(INIT_TOKEN)] = token
        }
    }

}