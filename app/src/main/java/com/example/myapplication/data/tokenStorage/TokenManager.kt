package com.example.myapplication.data.tokenStorage

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class TokenManager (application: Application) {
    private val Context.dataStore by preferencesDataStore(name = TOKEN_DATA_STORE)
    private val dataStore = application.dataStore

    fun getToken(): String = runBlocking {
        dataStore.data.first()
    }[stringPreferencesKey(USER_TOKEN_KEY)] ?: ""

    suspend fun saveToken(token: String) {
        dataStore.edit { pref ->
            pref[stringPreferencesKey(USER_TOKEN_KEY)] = token
        }
    }

    suspend fun deleteToken() {
        dataStore.edit { pref ->
            pref.remove(stringPreferencesKey(USER_TOKEN_KEY))
        }
    }

    private companion object {
        private const val TOKEN_DATA_STORE = "TOKEN_DATA_STORE"
        private const val USER_TOKEN_KEY = "USER_TOKEN_KEY"
    }
}