package com.example.githubuser_awal.repo

import android.app.Application
import com.example.githubuser_awal.data.model.FavoriteEntity
import com.example.githubuser_awal.db.FavoriteRoomDatabase
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val favoriteDao = FavoriteRoomDatabase.getInstance(application).favDao()
    private val executorService = Executors.newSingleThreadExecutor()

    fun insertFavorite(favorite: FavoriteEntity) {
        executorService.execute { favoriteDao.insertFavorite(favorite) }
    }

    fun deleteFavorite(login: String) {
        executorService.execute { favoriteDao.deleteFavorite(login) }
    }

    fun getFavorites() = favoriteDao.getAllFavorites()

    fun checkFavorite(login: String) = favoriteDao.checkFavorite(login)
}