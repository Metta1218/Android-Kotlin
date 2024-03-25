package com.example.githubuser_awal.data.model

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.prefDataStore by preferencesDataStore("settings")

class SettingPreferences(context: Context){


    private val settingDataStore = context.prefDataStore
    object SettingPreferencesKeys{
        val THEME_KEY = booleanPreferencesKey("theme_setting")

    }

    private val themeKEY = SettingPreferencesKeys.THEME_KEY

    fun getThemeSetting(): Flow<Boolean> =
        settingDataStore.data.map {preferences ->
            preferences[themeKEY] ?: false
        }
    suspend fun saveThemeSetting(isDarkModeActive:Boolean){
        settingDataStore.edit { preferences ->
            preferences[themeKEY] = isDarkModeActive
        }
    }
}