package com.example.githubuser_awal.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.githubuser_awal.api.RetrofitClient
import com.example.githubuser_awal.data.model.DetailUserContent
import com.example.githubuser_awal.data.model.FavoriteEntity
import com.example.githubuser_awal.repo.FavoriteRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(
    private val username: String,
    private val avatarUrl: String,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    val user = MutableLiveData<DetailUserContent>()
    val isFavorited = MutableLiveData<Boolean>()

    init {
        checkFavorite()
    }

    fun setUserDetail(username: String?){
        RetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<DetailUserContent>{
                override fun onResponse(
                    call: Call<DetailUserContent>,
                    response: Response<DetailUserContent>
                ) {
                    if(response.isSuccessful){
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserContent>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }
    fun getUserDetail(): LiveData<DetailUserContent>{
        return user
    }

    fun checkFavorite() {
        viewModelScope.launch {
            favoriteRepository.checkFavorite(username).asFlow().collect {
                isFavorited.postValue(it.isNotEmpty())
            }
        }
    }

    fun insertFavorite() {
        favoriteRepository.insertFavorite(FavoriteEntity(login = username, avatarUrl = avatarUrl))
    }

    fun deleteFavorite() {
        favoriteRepository.deleteFavorite(username)
    }
}