package com.example.githubuser_awal.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser_awal.api.RetrofitClient
import com.example.githubuser_awal.data.model.DetailUserContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {
    val user = MutableLiveData<DetailUserContent>()

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
}