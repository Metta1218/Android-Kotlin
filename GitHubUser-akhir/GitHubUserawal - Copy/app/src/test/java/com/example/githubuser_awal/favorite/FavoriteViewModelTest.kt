package com.example.githubuser_awal.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.githubuser_awal.data.model.FavoriteEntity
import com.example.githubuser_awal.repo.FavoriteRepository
import com.example.githubuser_awal.ui.favorite.FavoriteViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var repository: FavoriteRepository

    @Before
    fun setup() {
        repository = mock()
        viewModel = FavoriteViewModel(repository)
    }

    @Test
    fun `check Favorites Should Fire getFavorites in Repository`() {
        // Start getFavorites
        viewModel.getFavorites()

        // Verify that the getFavorites() method is called in the repository
        verify(repository).getFavorites()
    }
}