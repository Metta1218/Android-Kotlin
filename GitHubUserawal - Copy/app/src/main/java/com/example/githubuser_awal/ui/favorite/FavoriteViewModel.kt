package com.example.githubuser_awal.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.githubuser_awal.repo.FavoriteRepository

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {
    fun getFavorites() = favoriteRepository.getFavorites()
}