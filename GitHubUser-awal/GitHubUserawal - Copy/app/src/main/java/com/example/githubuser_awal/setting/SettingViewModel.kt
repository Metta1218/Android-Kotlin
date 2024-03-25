package com.example.githubuser_awal.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubuser_awal.data.model.SettingPreferences
import kotlinx.coroutines.launch

class SettingViewModel(private val pref: SettingPreferences) : ViewModel() {
    fun getTheme() = pref.getThemeSetting().asLiveData()
    fun saveTheme(isDark:Boolean){
        viewModelScope.launch {
            pref.saveThemeSetting(isDark)
        }
    }
    class Factory(private val pref: SettingPreferences) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SettingViewModel(pref) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
