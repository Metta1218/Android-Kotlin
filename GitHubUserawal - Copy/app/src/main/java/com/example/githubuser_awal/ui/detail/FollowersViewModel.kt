package com.example.githubuser_awal.ui.detail

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser_awal.api.RetrofitClient
import com.example.githubuser_awal.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {
    private val listFollowers = MutableLiveData<ArrayList<User>>()

    fun setListFollowers(context: Context, username: String) {
        RetrofitClient.apiInstance
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<User>> {
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful) {
                        listFollowers.postValue(response.body())
                    } else {
                        Toast.makeText(
                            context,
                            "Gagal, mohon coba lagi nanti.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "Gagal. Mohon coba lagi nanti.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    fun getListFollowers(): LiveData<ArrayList<User>> {
        return listFollowers
    }
}
