package com.example.githubuser_awal.api

import com.example.githubuser_awal.BuildConfig
import com.example.githubuser_awal.data.model.DetailUserContent
import com.example.githubuser_awal.data.model.User
import com.example.githubuser_awal.data.model.UserContent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun getSearchUsers(
        @Query("q") query: String
    ):Call<UserContent>

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun getUserDetail(
        @Path("username") username: String?
    ):Call<DetailUserContent>

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun getFollowers(
        @Path("username") username : String
    ):Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    fun getFollowing(
        @Path("username") username : String
    ):Call<ArrayList<User>>


}