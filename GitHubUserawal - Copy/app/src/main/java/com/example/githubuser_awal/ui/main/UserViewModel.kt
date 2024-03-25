package com.example.githubuser_awal.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.githubuser_awal.api.RetrofitClient
import com.example.githubuser_awal.data.model.SettingPreferences
import com.example.githubuser_awal.data.model.User
import com.example.githubuser_awal.data.model.UserContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(private val preferences: SettingPreferences) : ViewModel() {

    val listUser = MutableLiveData<ArrayList<User>>()
    fun getTheme() = preferences.getThemeSetting().asLiveData()

    fun setSearchUsers(query: String) {
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserContent> {
                override fun onResponse(
                    call: Call<UserContent>,
                    response: Response<UserContent>
                ) {
                    if (response.isSuccessful) {
                        listUser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserContent>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })

    }

    fun getSearchUsers(): LiveData<ArrayList<User>> {
        return listUser
    }

    class Factory(private val preferences: SettingPreferences) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            UserViewModel(preferences) as T
    }
}


